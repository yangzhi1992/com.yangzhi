package com.yz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public synchronized static String formatDate(Date date) {
	    return sdf.format(date);
	}

	private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
	    @Override
	    protected SimpleDateFormat initialValue()
	    {
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    }
	};

	public String formatIt(Date date)
	{
	    return formatter.get().format(date);
	}
}
