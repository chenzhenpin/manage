package org.fkit.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

public class DataFormatter implements Formatter<Date>{
	private String dateParttern;
	private SimpleDateFormat dataFormat;
	
	public DataFormatter(String dateParttern){
		this.dateParttern=dateParttern;
		this.dataFormat=new SimpleDateFormat(dateParttern);
		
	}
	
	
	@Override
	public String print(Date date, Locale arg1) {
		// TODO Auto-generated method stub
		return dataFormat.format(date);
	}


	@Override
	public Date parse(String s, Locale arg1) throws ParseException {
		// TODO Auto-generated method stub
		try{
			System.out.println("DataFormatter:"+s);
			return dataFormat.parse(s);
		}catch(ParseException e){
			
			throw new IllegalArgumentException();
		}
	}

	

//	@Override
//	public Date parse(String datePattern, Locale arg1) throws ParseException {
//		// TODO Auto-generated method stub
//		try{
//			System.out.println("DataFormatter:"+datePattern);
//			return dataFormat.parse(datePattern);
//		}catch(Exception e){
//			throw new IllegalArgumentException();
//		}
//	}

}
