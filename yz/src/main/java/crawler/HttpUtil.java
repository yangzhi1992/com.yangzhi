package crawler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.LoggerFactory;

public class HttpUtil {
    private static final org.slf4j.Logger            logger          = LoggerFactory.getLogger(HttpUtil.class);
    static PoolingHttpClientConnectionManager        cm              = null;
    static PoolingHttpClientConnectionManager        cmLeft          = null;
    public static PoolingHttpClientConnectionManager cmCheck         = null;
    public static PoolingHttpClientConnectionManager cmProxy         = null;
    private static CloseableHttpClient               checkHttpClient = null;
    private static volatile BasicCookieStore         cookie12306Store     = null;
    private static volatile BasicCookieStore         cookieMobileStore    = null;
    private static volatile long                     lastUpdateTime       = System.currentTimeMillis();
    private static volatile long                     lastMobileUpdateTime = System.currentTimeMillis();
    private static BasicCookieStore                  cookieTongchengStore = new BasicCookieStore();

    public static String get(String url) throws Exception{
    	RequestConfig config = null;
    	
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(url);
        
        config = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(10000).build();
        
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("User-Agent", Constant.getRandomUserAgent());
        httpGet.setConfig(config);
        
        httpClient = HttpUtil.createSSLClientDefault(null);
        
        String res = null;
        try {
            response = httpClient.execute(httpGet);
            InputStream in = response.getEntity().getContent();
            res = IOUtils.toString(in);
            in.close();
        } catch (Exception e) {
        	throw e;
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
        
        return res;
    }
    
    public static CloseableHttpClient createSSLClientDefault(BasicCookieStore cookies) {
        SSLConnectionSocketFactory sslsf = null;
        if (checkHttpClient != null) {
            return checkHttpClient;
        }
        try {
            //获取证书
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

            trustStore.load(null, null);

            SSLContext sslcontext = createIgnoreVerifySSL();
            sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            LayeredConnectionSocketFactory sslsftemp = null;
            sslsftemp = new SSLConnectionSocketFactory(sslcontext);
            if (cmCheck == null) {
                //创建连接池
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslsftemp).register("http", new PlainConnectionSocketFactory()).build();
                cmCheck = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                cmCheck.setMaxTotal(500);
                // 将目标主机的最大连接数增加
                cmCheck.setDefaultMaxPerRoute(500);
            }
            // 请求重试处理
            HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
                public boolean retryRequest(IOException exception, int executionCount,HttpContext context) {
                    if (executionCount >= 1) {// 如果已经重试了2次，就放弃
                        return false;
                    }
                    if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                        return false;
                    }
                    if (exception instanceof InterruptedIOException) {// 超时
                        return false;
                    }
                    if (exception instanceof UnknownHostException) {// 目标服务器不可达
                        return false;
                    }
                    if (exception instanceof SSLException) {// SSL握手异常
                        return false;
                    }

                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    // 如果请求是幂等的，就再次尝试
                    if (!(request instanceof HttpEntityEnclosingRequest)) {
                        return true;
                    }
                    return false;
                }
            };
            //构建返回httpclient
            if (cookies == null) {
                checkHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cmCheck)
                		                              .setConnectionManagerShared(true).setRetryHandler(httpRequestRetryHandler).build();
            } else {
                checkHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultCookieStore(cookies)
                		                              .setConnectionManager(cmCheck).setConnectionManagerShared(true)
                		                              .setRetryHandler(httpRequestRetryHandler).build();
            }

            return checkHttpClient;
        } catch (Exception e) {
            logger.error("创建https导入证书错误", e);
        }
        return null;
    }
    
    /** 
     * 绕过验证 
     * @return 
     * @throws NoSuchAlgorithmException  
     * @throws KeyManagementException  
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException,KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法  
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
}