package T_12306.http.client;

import T_12306.http.client.methods.VHttpMethods;

/**
 * 
 * @author 默非默
 *
 */
public interface VHttpClient {

	public abstract VHttpResponse execute(VHttpMethods methods);
}
