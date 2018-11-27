package org.fkit.controller;


import javax.servlet.http.HttpServletRequest;

import org.fkit.mail.Mail;
import org.fkit.table.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class MailController {
	private static final Logger logger = LoggerFactory.getLogger("MailController.class");  
	
	@Autowired
	private Mail mail;
	@Autowired  
	private TaskExecutor executor; 
	
	@ResponseBody
	@RequestMapping(value="/sendmail",produces = "application/json; charset=utf-8")
	public String sendmail(HttpServletRequest request){
		
		System.out.println(request.getContextPath());
		System.out.println(request.getServletPath());
		System.out.println(request.getServletContext().getRealPath("/"));
		logger.info("记录邮件发送");
		 executor.execute(new Runnable() { 
			public void run() {
			System.out.println("邮件发送");
			mail.sendEmail("1940497838@qq.com", "你好");	
			
			}
		 });
		return "邮件发送成功";
		
	}
}
