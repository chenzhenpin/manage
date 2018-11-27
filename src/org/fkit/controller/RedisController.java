package org.fkit.controller;

import javax.annotation.Resource;

import org.fkit.redisUtil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping(value="/redis")  
public class RedisController {  

    
    @Autowired   
    private RedisUtil redisUtil;
    @RequestMapping("/add")  
    @ResponseBody  
    public String add(){  
//    	Member member=new Member();
//    	member.setId("213");
//    	member.setNickname("chen");
//        memberService.add(member);  
    	redisUtil.hset("student", "name", "chenzhen");
        return "add ok!";  
    }  
    @RequestMapping("/get")  
    @ResponseBody  
    public Object query(){  
    	String name=redisUtil.hget("student", "name");
        return name;  
    }  
    @RequestMapping("/delete")  
    @ResponseBody  
    public String delete(){  
    	redisUtil.hdel("student","name");  
        return "delete ok!";  
    } 
    @RequestMapping("/size")  
    @ResponseBody  
    public String size(){  
    	Long size=redisUtil.hsize("student"); 
    	
        return size.toString();  
    }
    
}  