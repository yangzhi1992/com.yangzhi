package com.yz.multithread.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat 是非线程安全的
 * @author hy
 *
 */
public class SdfThread implements Runnable{
	private SimpleDateFormat sdf;
	private String oldDate;
	private String threadName;
	
	public SdfThread(SimpleDateFormat sdf, String oldDate,String threadName) {
		this.sdf = sdf;
		//this.sdf = new SimpleDateFormat("yyyy-MM-dd"); 解决办法是没错new一个
		this.oldDate = oldDate;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		Date datetmp = null;
		try {
			datetmp = sdf.parse(oldDate);
		} catch (ParseException e) {
			
		}
		System.out.println(threadName+"=="+oldDate+"=="+datetmp);
	}
}
