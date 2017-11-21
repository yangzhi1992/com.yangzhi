package com.yz.crawler;
import java.util.LinkedList;

import org.jsoup.Jsoup;

import com.yz.utils.FileUtil;
  
/** 
 *  
 * ProxyUtil 
 * @Description:TODO 代理IP工具 
 * @author:千界云 http://qianjieyun.com 
 * @email:fhg1225@126.com 
 * @date 2016年9月14日 
 */  
public class ProxyUtil {  
	static FileUtil fu = new FileUtil();
	final static LinkedList list = new LinkedList();
    //默认:分割  
    public static String getProxy(){  
    	
        return (String) list.pop();
    }  
      
    public static void putProxy(String ip, Integer port){  
    	String value = ip+":"+port;
    	list.push(value);
    	fu.WriteFile(value, "D:\\", "ip.txt", "UTF-8");
    }  
      
    public static boolean checkProxy(String ip, Integer port){  
        try {  
            //http://1212.ip138.com/ic.asp 可以换成任何比较快的网页  
            Jsoup.connect("http://1212.ip138.com/ic.asp")  
                    .timeout(3*1000)  
                    .proxy(ip, port)  
                    .get(); 
            System.out.println(ip+":"+port);  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }  
}  