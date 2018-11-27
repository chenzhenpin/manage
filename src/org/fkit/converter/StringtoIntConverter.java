package org.fkit.converter;


import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.springframework.core.convert.converter.Converter;


public class StringtoIntConverter implements Converter<String,Integer> {

	
	public Integer convert(String s){
		try{
			System.out.println("字符转整型");
			System.out.println(Integer.parseInt(s));
			return Integer.parseInt(s);
			
		}catch(NumberFormatException e){
			System.out.println("输入类型错误");
			throw new NumberFormatException("输入类型错误");
			
		}
		
	}
}