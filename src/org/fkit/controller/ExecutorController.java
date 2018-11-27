package org.fkit.controller;

import org.fkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExecutorController {
	 @Autowired 
	 private UserService userService;
	 @Autowired  
	 private TaskExecutor executor; 
	 
	 //多线程测试
	 @RequestMapping(value="executor")
	 public String executor(){
		 for(int i=0;i<11;i++){
		 executor.execute(new Runnable() {  
	            @Override  
	            public void run() {  
	                synchronized (userService) { 
	                	System.out.println("------");
	                	try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	userService.setCahche("多线程缓存");
	                    System.out.println(Thread.currentThread().getName());  
	                }  
	            }  
	        }); 
		 }
		 return "forward:/register";
		 
	 }
	 
}
