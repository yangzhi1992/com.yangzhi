package T_12306.http.utils;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import T_12306.http.client.VHttpResponse;

/**
 * 
 * @author 默非默
 *
 */
public class VHttpUtils {
	
	/**
	 * 将inputStream转为byte[]
	 * @param in
	 * @return
	 */
	public static byte[] InputStreamToByte(InputStream in){
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		byte[] data = null;
		try {
			while ((len = in.read(buffer)) != -1) {
				// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 0, len);
			}
			in.close();
			data = outStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 将网页返回的结果封装成字符串
	 * @param in
	 * @return
	 */
	public static String outHtml(InputStream in){
		String result = "";
		try  {
			StringBuffer sb = new StringBuffer() ;
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new  BufferedReader(inr);  
	        String line ;  
	        while ( (line =br.readLine()) !=  null  ){
	        	sb.append(line);
	        }  
	        br.close();  
	        result = new String(sb.toString().getBytes(),VConstant.ENCODE);
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将网页返回的结果封装成字符串
	 * @param in
	 * @return
	 */
	public static String outHtmlByLine(InputStream in){
		String result = "";
		try  {
			StringBuffer sb = new StringBuffer() ;
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new  BufferedReader(inr);  
	        String line ;  
	        while ( (line =br.readLine()) !=  null  ){
	        	sb.append(line+"/r/n");
	        }  
	        br.close();  
	        result = new String(sb.toString().getBytes(),VConstant.ENCODE);
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 打印网页响应内容并返回字符串结果
	 * @param in
	 * @return
	 */
	public static String outHtmlAndPrint(InputStream in){
		String result = "";
		try  {
			StringBuffer sb = new StringBuffer() ;
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new  BufferedReader(inr);  
	        String line ;  
	        while ( (line =br.readLine()) !=  null  ){
	        	sb.append(line);
	        }  
	        br.close();  
	        result = new String(sb.toString().getBytes(),VConstant.ENCODE);
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 打印网页内容
	 * @param in
	 */
	public static void PrintHtml(InputStream in){
		try  {
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new  BufferedReader(inr);  
	        String line ;  
	        while ( (line =br.readLine()) !=  null  ){
	        	System.out.println(line);
	        }  
	        br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印网页内容（分行）
	 * @param in
	 */
	public static void PrintHtmlByLine(InputStream in){
		try  {
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new  BufferedReader(inr);  
	        String line ;  
	        while ( (line =br.readLine()) !=  null  ){
	        	System.out.println(line+"/r/n");
	        }  
	        br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 针对普通验证码方式
	 * @param in	验证码的流
	 * @param x		展示验证码的窗口的长
	 * @param y		展示验证码的窗口的宽
	 * @return		验证码
	 * @wbp.parser.entryPoint
	 */
	public static String outCode(InputStream in, int x,int y) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		byte[] data = null;
		try {
			while ((len = in.read(buffer)) != -1) {
				// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 0, len);
			}
			in.close();
			data = outStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(false);
		frame.setBounds(x==0?307:x, y==0?266:y,x==0?307:x, y==0?266:y);
		frame.getContentPane().setLayout(new FlowLayout());

		final StringBuffer incode = new StringBuffer();
		ImageIcon icon = new ImageIcon(data);
		frame.getContentPane().add(new JLabel(icon));
		frame.setVisible(true);
		

		System.out.print("输入你看到的验证码：");
		Scanner scr = new Scanner(System.in);

		String code = scr.nextLine();
		System.out.println("验证码 : " + code);
		return code;
	}

	/**
	 * 打印Cookie
	 * @param res
	 */
	public static void PrintCookies(VHttpResponse res){
		List<HttpCookie> cookies = res.getCookies();
		System.out.println("==================Cookies打印开始=========================");
		for (HttpCookie cookie : cookies){
			System.out.println(cookie.getName()+"==="+cookie.getValue());
		}
		System.out.println("==================Cookies打印结束=========================");
	}
}
