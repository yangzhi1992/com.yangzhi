package T_12306.http.client.methods;

import T_12306.http.client.parames.VParames;
import T_12306.http.header.VHeader;

/**
 * 
 * @author 默非默
 *
 */
public interface VHttpMethods {
	
	public abstract String getUrl();
	
	public abstract VHeader getHeader();
	
	public abstract VParames getParames();
	
	public abstract String getType();

}
