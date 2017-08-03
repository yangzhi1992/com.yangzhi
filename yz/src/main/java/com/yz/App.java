package com.yz;

import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("s"+StringUtils.EMPTY+"d");
       String str = "1   2    3";
       char[] cs = str.toCharArray();
       for(char c : cs){
    	   System.out.println("a"+c+"b");
    	   if(c == ' '){
    		   System.out.println("dd");
    	   }
       }
    }
}
