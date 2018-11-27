package org.fkit.converter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringtoDateConverter implements Converter<String,Date> {
	private String datePattern;
	public StringtoDateConverter(String datePattern){
		this.datePattern=datePattern;
		
	}
	public Date convert(String s){
		try{
			System.out.println("pattern:"+s);
			SimpleDateFormat dateFormat=new SimpleDateFormat(datePattern);
			dateFormat.setLenient(false);
			System.out.println("pattern:"+s);
			return dateFormat.parse(s);
		}catch(ParseException e){
			System.out.println("invalid pattern");
			throw new IllegalArgumentException("invalid pattern:"+datePattern);
			
		}
		
	}
}
