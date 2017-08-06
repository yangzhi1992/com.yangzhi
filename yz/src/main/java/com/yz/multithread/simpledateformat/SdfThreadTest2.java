package com.yz.multithread.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SdfThreadTest2{
	public static void main(String[] args) throws InterruptedException {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String[] date = new String[]{"2017-01-01","2017-02-02","2017-03-03","2017-04-04","2017-05-05",
				"2017-06-06","2017-07-07","2017-08-08","2017-09-09","2017-10-10","2017-11-11","2017-12-12"};
		final ExecutorService es2 = Executors.newFixedThreadPool(12);
		for(int i=0;i<12;i++){
			final int j = i;
			es2.execute(new Runnable(){

				@Override
				public void run() {
					try {
						synchronized(sdf){
							System.out.println(j+ "->" + sdf.parse((date[j])));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
}
