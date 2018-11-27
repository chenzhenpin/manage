package org.fkit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	@RequestMapping(value = "/loginTest", method = RequestMethod.POST)  
    public String loginTest(HttpServletRequest request,HttpServletResponse response){  
        String account = request.getParameter("userName");  
        String password = request.getParameter("password");  
        if (account.equals("admin")&&password.equals("1")){  
            return "index";  
        }else{  
            return "login";  
        }  
    }  
}
