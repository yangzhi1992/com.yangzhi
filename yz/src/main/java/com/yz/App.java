package com.yz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	ThreadLocal tl = new ThreadLocal<>();
    	List list = new ArrayList();
    	List list2 = Collections.synchronizedList(list);
    	list2.add("dd");
    	
    	AtomicLong al = new AtomicLong();
    	al.addAndGet(1);
    	al.set(0);
    	
    	AtomicInteger ai = new AtomicInteger();
    	ai.set(0);
    	
    	Executors.newFixedThreadPool(2);
    	Executors.newCachedThreadPool();
    	Executors.newSingleThreadExecutor();
    	Executors.newScheduledThreadPool(100);
    	
    }
}
