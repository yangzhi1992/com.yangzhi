package com.yz.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.yz.utils.FileUtil;

public class HttpClientProxy {  
  
    /** 
     * @param args 
     * @throws InterruptedException 
     */  
    public static void main(String[] args) throws InterruptedException {
    	List<String> list = getUrlForList();
    	final HttpClientProxy hcp = new HttpClientProxy();
    	for(;;){
    		Thread.sleep(2000);
    		Thread t = new Thread(new Runnable(){
				public void run() {
					hcp.post("",0);
				}
    		});
    		t.start();
    		
    	}
    }  
    
    public void post(String ip,Integer port){
    	List list = getUrlForList();
    	
    	// 创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        // HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        // 依次是目标请求地址，端口号,协议类型  
        HttpHost target = new HttpHost("localhost", 8080,  "http");  
        // 依次是代理地址，代理端口号，协议类型  
        HttpHost proxy = new HttpHost("218.104.148.157", 8080, "http");  
        
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
  
        // 请求地址  
        HttpPost httpPost = new HttpPost("https://f2f.pingan.com.cn/product/queryFundHistory");  
        httpPost.setConfig(config);  
        // 创建参数队列  
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        
		formparams.add(new BasicNameValuePair("departure", "苏州"));  
		formparams.add(new BasicNameValuePair("destination", "上海"));  
        formparams.add(new BasicNameValuePair("departureDate", "2017-07-05"));  
        
//      formparams.add(new BasicNameValuePair("productNum", "000662"));  
//		formparams.add(new BasicNameValuePair("queryRange", "month"));
//		formparams.add(new BasicNameValuePair("queryType", "1"));
  
        UrlEncodedFormEntity entity;  
        try {  
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httpPost.setEntity(entity);  
            CloseableHttpResponse response = closeableHttpClient.execute(target, httpPost);  
            // getEntity()  
            HttpEntity httpEntity = response.getEntity();  
            if (httpEntity != null) {  
                // 打印响应内容  
                System.out.println("response:"  + EntityUtils.toString(httpEntity, "UTF-8"));  
            }  
            // 释放资源  
            closeableHttpClient.close();  
        } catch (Exception e) {  
        	System.out.println(ip+":"+port);
        }   
    }
    
    public static List getUrlForList(){
    	List<String> list = FileUtil.readToList("D:"+File.separator+"ip.txt", "UTF-8");
    	return list;
    }
}  