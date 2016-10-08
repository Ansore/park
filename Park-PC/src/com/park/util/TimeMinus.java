package com.park.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TimeMinus {

	public long minus(Timestamp tms){
		
	long minutes=0;
	Timestamp ts=new Timestamp(System.currentTimeMillis());
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try
	 
	{
	 
	  Date d1 = df.parse(TimestampToString(ts));
	 
	  Date d2 =  df.parse(TimestampToString(tms));
	  long diff = d1.getTime() - d2.getTime();
	  minutes = diff / (1000*60);

	  return minutes;
	 
	}
	catch (Exception e)
	{
	}return minutes;
}
	
	public String TimestampToString(Timestamp tms){
    	
        Timestamp ts = tms;  
        String tsStr = "";    
        try {  

            tsStr = ts.toString();  
            return tsStr;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  return null;
	}
	
}