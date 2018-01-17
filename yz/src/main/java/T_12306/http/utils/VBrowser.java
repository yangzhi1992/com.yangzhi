package T_12306.http.utils;

import T_12306.http.client.VHttpClient;
import T_12306.http.client.VHttpResponse;
import T_12306.http.client.methods.VHttpMethods;
import T_12306.http.impl.VHttpClientImpl;

/**
 * 浏览器类
 * @author Administrator
 *
 */
public class VBrowser {
	
	private static VHttpClient client = new VHttpClientImpl();
	
	private static VBrowser browser = null;
	
	private VBrowser(){}
	
	public static VBrowser getInstance(){
		if (browser==null) {
			return new VBrowser();
		}
		return browser;
	}

	public static VHttpResponse execute(VHttpMethods methods){
		return client.execute(methods);
	}
}
