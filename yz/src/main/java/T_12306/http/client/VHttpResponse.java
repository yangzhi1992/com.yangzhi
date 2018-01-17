package T_12306.http.client;

import java.io.InputStream;
import java.net.HttpCookie;
import java.util.List;

import T_12306.http.client.entiry.VHttpEntity;
import T_12306.http.header.VHeader;

/**
 * 
 * @author 默非默
 *
 */
public interface VHttpResponse {
	
	public abstract List<HttpCookie> getCookies();
	
	public abstract String getHeader(String key);
	
	public abstract VHeader getAllHeader();
	
	public abstract InputStream getBody();
	
	public abstract String getLocation();
	
	public abstract VHttpEntity getEntity();
	
	public abstract void setEntity(VHttpEntity entity);
}
