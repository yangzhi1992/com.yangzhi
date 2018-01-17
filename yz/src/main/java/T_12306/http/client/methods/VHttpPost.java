package T_12306.http.client.methods;

import T_12306.http.client.parames.VParames;
import T_12306.http.header.VHeader;
import T_12306.http.utils.VConstant;

/**
 * 
 * @author 默非默
 *
 */
public class VHttpPost implements VHttpMethods{

	private final String type = VConstant.HTTPPOST;

	private String url;
	
	private VHeader header;
	
	private VParames parames;
	
	/**
	 * 
	 */
	public VHttpPost(){};
	
	/**
	 * 
	 * @param url
	 */
	public VHttpPost(String url){
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public VHeader getHeader() {
		return header;
	}

	public void setHeader(VHeader header) {
		this.header = header;
	}

	public VParames getParames() {
		return parames;
	}

	public void setParames(VParames parames) {
		this.parames = parames;
	}
	
}
