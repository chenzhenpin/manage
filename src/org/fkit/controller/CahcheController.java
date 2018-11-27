package org.fkit.controller;

import org.fkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CahcheController {
	@Autowired 
	 private UserService userService;
	
	@RequestMapping(value="testCahce")
	 public String testCahce(){
		 userService.setCahche("缓存");
		 userService.setCahche("缓存");
		 userService.setCahche("缓存1");
		 userService.removeCahche("缓存");
		 userService.setCahche("缓存");
		 userService.setCahche("缓存1");
		 userService.removeAllCahche("清除所有缓存");
		 userService.setCahche("缓存");
		 userService.setCahche("缓存1");
		 return "forward:/register";
	 }
}
