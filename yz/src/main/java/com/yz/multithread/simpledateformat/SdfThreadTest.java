package com.yz.multithread.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SdfThreadTest{
	public static void main(String[] args) throws InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String[] date = new String[]{"2017-01-01","2017-02-02","2017-03-03","2017-04-04","2017-05-05",
				"2017-06-06","2017-07-07","2017-08-08","2017-09-09","2017-10-10","2017-11-11","2017-12-12"};
		
		Thread[] t = new Thread[12];
		for(int i=0;i<12;i++){
			Runnable r = new SdfThread(sdf,date[i],String.valueOf(i));
			t[i] = new Thread(r);
		}
		
		for(int i=0;i<12;i++){
			t[i].start();
		}
	}
}
