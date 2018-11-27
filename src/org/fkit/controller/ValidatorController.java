package org.fkit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.fkit.exception.CustomException;
import org.fkit.table.User;
import org.fkit.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ValidatorController {
	private static final Log logger =LogFactory.getLog(ValidatorController.class);
	@RequestMapping(value="login")
	public String login(Model model,
			HttpServletRequest request,
			@ModelAttribute User user,
			BindingResult bindingResult
		/*	@RequestParam("name") String name,
			@RequestParam("sex") String sex,
			@RequestParam("age") int age,
			@RequestParam("username") String username,
			@RequestParam("password") String password*/
			) throws IOException{
			if(request.getMethod()=="GET"){
				return "loginForm";
			}
		
		/*	User user=new User();
			user.setName(name);
			user.setSex(sex);
			user.setAge(age);*/
			request.getSession().setAttribute("username",user.getUsername());
			request.getSession().setAttribute("password",user.getPassword());
		 	System.out.print(user.getName());
		 	System.out.print("ok");
			//调用userValidator进行验证
		 	model.addAttribute("user",user);
		 	UserValidator userValidator=new UserValidator();
			userValidator.validate(user, bindingResult);
			if(bindingResult.hasErrors()){
				return "loginForm";
			}

//			model.addAttribute("user",user);
			return "uploadForm";
	}
	
	
	
	
	//JSR303 方式 验证
	@RequestMapping(value="register" ,method=RequestMethod.POST )
	public String register(Model model,
			@Valid @ModelAttribute User user,
			Errors errors
		/*	@RequestParam("name") String name,
			@RequestParam("sex") String sex,
			@RequestParam("age") int age,
			@RequestParam("username") String username,
			@RequestParam("password") String password*/
			) throws IOException{
		
		/*	User user=new User();
			user.setName(name);
			user.setSex(sex);
			user.setAge(age);*/
		   System.out.println("/register1");
			logger.info(user);
			if(errors.hasErrors()){
				System.out.println(errors.getFieldError());
				return "registerForm";
			}
			System.out.println("/register2");
			//model.addAttribute("user",user);
			return "loginForm";
	}
}
