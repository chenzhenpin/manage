package org.fkit.mail;

import java.io.File;  
import java.util.Properties;

import javax.activation.DataHandler;  
import javax.activation.DataSource;  
import javax.activation.FileDataSource; 

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


@Service
public class Mail {
	public Mail() {
		
	}
	public  void sendEmail(String toAddress,String htmlText){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port","587");
		try {
			PopupAuthenticator auth = new PopupAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			Address addressFrom = new InternetAddress(
					PopupAuthenticator.mailuser, "海南大学外事管理信息系统");
			Address addressTo = new InternetAddress(toAddress, "LUO");
			MimeMultipart mp = new MimeMultipart();
			MimeBodyPart mbp1= new MimeBodyPart(); 
//			String htmlText = "<b> 海南大学外事管理</b>";
			mbp1.setContent(htmlText,"text/html;charset=UTF-8");
			mp.addBodyPart(mbp1);
			message.setContent(mp);
			//message.setText("哈哈！qq发送邮件测试成功！！！！！！！");
			message.setSubject("海南大学外事管理信息系统");
			message.setFrom(addressFrom);
			message.addRecipient(Message.RecipientType.TO, addressTo);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.qq.com", PopupAuthenticator.mailuser,
					PopupAuthenticator.password);
			transport.send(message);
			transport.close();
			System.out.println("sent suc");
		} catch (Exception e) {
//			System.out.println(e.toString());
//			System.out.println("sent fail");
		}
	}
	public  void sendEmail(String toAddress,String htmlText,String filePath,String fileName){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port","587");
		try {
			PopupAuthenticator auth = new PopupAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			Address addressFrom = new InternetAddress(
					PopupAuthenticator.mailuser, "海南大学外事管理信息系统");
			Address addressTo = new InternetAddress(toAddress, "LUO");
			MimeMultipart mp = new MimeMultipart();
			//正文
			MimeBodyPart bodyhmtl= new MimeBodyPart(); 
			//附件
			MimeBodyPart bodyfile= new MimeBodyPart(); 
			//里面封装了各种与附件有关的方法，包括获取附件的InputStream
			
	         
//			String htmlText = "<b> 海南大学外事管理</b>";
			bodyhmtl.setContent(htmlText,"text/html;charset=UTF-8");
			
			
			//附件
			File file=new File(filePath);
			if(file.exists()){
				DataSource dataSource = new FileDataSource(file);  
		        //DataHandler是对DataSource的封装，可以把Stream转换为String，  
		        DataHandler dataHandler = new DataHandler(dataSource);  
		        bodyfile.setDataHandler(dataHandler);  
		        //附件的名称，默认为content，如果文件名不加扩展名在进行附件下载的时候系统会不知道该附件是什么类型的附件，  
		        //这样在不清楚文件类型的情况下，文件有可能是打不开的  
		        //bodyfile.setFileName(fileName);
		        bodyfile.setFileName(MimeUtility.encodeText(fileName));
		        System.out.println("file exists");
			}else{
				System.out.println("file not exists");
			}
			mp.addBodyPart(bodyhmtl);
			mp.addBodyPart(bodyfile);
			message.setContent(mp);
			//message.setText("哈哈！qq发送邮件测试成功！！！！！！！");
			message.setSubject("海南大学外事管理信息系统");
			message.setFrom(addressFrom);
			message.addRecipient(Message.RecipientType.TO, addressTo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.qq.com", PopupAuthenticator.mailuser,
					PopupAuthenticator.password);
			transport.send(message);
			transport.close();
			System.out.println("sent suc");
		} catch (Exception e) {
//			System.out.println(e.toString());
//			System.out.println("sent fail");
		}
	}
}

class PopupAuthenticator extends Authenticator {
//	public static final String mailuser = "hdwsmanagement@163.com";
//	public static final String password = "management163";
	//wnhhtfpgxjttbgjc
	//flwgabdkgssebjjd
	//public static final String mailuser = "421138060@qq.com";
	//public static final String password = "gkzyjznndeyzbjfd";
	//public static final String mailuser = "531866450@qq.com";
	//public static final String password = "sarvhwuzhnzqbgfh";
	public static final String mailuser = "1595347682@qq.com";
	public static final String password = "mbtzngnnzkzpjjhg";
	//授权码：sarvhwuzhnzqbgfh
	//授权码：slcmmelhgwhacahh

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(mailuser, password);
	}
	
	
	
}