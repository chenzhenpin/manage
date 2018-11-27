package org.fkit.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.fkit.entity.shiro.ShiroUser;
import org.fkit.exception.CustomException;
import org.fkit.service.PersonService;
import org.fkit.service.ShiroUserService;
import org.fkit.shiro.MD5toHash;
import org.fkit.table.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {
	@Autowired
	private ShiroUserService shiroUserService;
	
	
	@RequestMapping("/shirologinform")
	public String shirologinform(@ModelAttribute User user){
		
				
		return "ShiroLoginForm";
		
	}
	@RequestMapping(value="shirologin")
	public String shiroLogin(Model model,
			HttpServletRequest request
		
			) throws Exception{
			if(request.getMethod().equals("GET")){
				return "ShiroLoginForm";
			}
		
			//如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
			String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
			System.out.println("--------------------");
			System.out.println(exceptionClassName);
			//根据shiro返回的异常类路径判断，抛出指定异常信息
			if(exceptionClassName!=null){
				if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
					//最终会抛给异常处理器
					System.out.println("账号不存在");
					throw new CustomException("账号不存在");
				} else if (IncorrectCredentialsException.class.getName().equals(
						exceptionClassName)) {
					System.out.println("用户名/密码错误");
					throw new CustomException("用户名/密码错误");
				} else if("randomCodeError".equals(exceptionClassName)){
					throw new CustomException("验证码错误 ");
				}else {
					throw new Exception();//最终在异常处理器生成未知错误
				}
			}
			//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
			//登陆失败还到login页面
			

//			model.addAttribute("user",user);
			return "login";
	}
	
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")  
	@ResponseBody  
	public String ajaxLogin(
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="password") String password
			)throws IOException{  
		System.out.println(username);
		System.out.println(password);
	    JSONObject jsonObject = new JSONObject();  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(username,password);  
	    try {  
	        subject.login(token);  
	        jsonObject.put("token", subject.getSession().getId());  
	        jsonObject.put("msg", "登录成功");  
	    } catch (IncorrectCredentialsException e) {  
	        jsonObject.put("msg", "密码错误");  
	    } catch (LockedAccountException e) {  
	        jsonObject.put("msg", "登录失败，该用户已被冻结");  
	    } catch (AuthenticationException e) {  
	        jsonObject.put("msg", "该用户不存在");  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return jsonObject.toString();  
	} 
	@RequestMapping(value = "/unauth")  
    @ResponseBody  
    public Object unauth() {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("code", "1000000");  
        map.put("msg", "未登录");  
        return map;  
    }  
	@RequestMapping("/shirologout")
	public String shirologout(HttpSession session)throws Exception{
		
		//session失效
		session.invalidate();
		//重定向到商品查询页面
		return "redirect:/shirologinform";
		
	}
	
	
	
	@RequestMapping("/shiroregister")
	public String shiroRegister(
			HttpServletRequest request,
			@Valid @ModelAttribute User user,
			Errors errors)throws Exception{
		
		
	/*{
	
	/*	User user=new User();
		user.setName(name);
		user.setSex(sex);
		user.setAge(age);*/
		System.out.println(request.getMethod());
		if(request.getMethod().equals("GET")){
			return "ShiroRegisterForm";
		}
		
		if(errors.hasErrors()){
			System.out.println(errors.getFieldError());
			return "ShiroRegisterForm";
		}
		String username =user.getUsername();
		ShiroUser shiroUser=null;
		
		try {
			shiroUser = shiroUserService.findUserByUserName(username);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		if(shiroUser !=null) {
			throw new CustomException("用户已存在");
		}
		String salt=username+"123";
		String password =user.getPassword();
		String md5pwd=new MD5toHash(password,salt).toMD5Hash().toString();
		shiroUserService.registerUser(username, md5pwd, salt);
		System.out.println("/register2");
		//model.addAttribute("user",user);
		return "ShiroLoginForm";
		
	}
	
	@RequestMapping(value="/islogin" , produces = "application/json; charset=utf-8")
	@ResponseBody
	public String islogin()throws Exception{
		
		//
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		System.out.println(session.getId());
		System.out.println(session.getHost());
		if(subject.isAuthenticated()) {
			System.out.println("该用户已登陆");
			return "该用户已登陆";
		}else {
			System.out.println("该用户未登陆");
			return "该用户未登陆";
			
		}
		//重定向到商品查询页面
	}
	@RequestMapping(value="/logout")
	@ResponseBody
	public String logout() {
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		return "退出成功";
	}
	
	@RequestMapping("/validateCode")
	@ResponseBody
	public void  validateCode(HttpServletResponse  response,HttpSession session){
		
		int width = 60;
		int height = 32;
		//create the image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// set the background color
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// draw the border
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// create a random instance to generate the codes
		Random rdm = new Random();
		String hash1 = Integer.toHexString(rdm.nextInt());
		System.out.print(hash1);
		// make some confusion
		for (int i = 0; i < 50; i++) {
			int x = rdm.nextInt(width);
			int y = rdm.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		// generate a random code
		String capstr = hash1.substring(0, 4);
		//将生成的验证码存入session
		session.setAttribute("validateCode", capstr);
		g.setColor(new Color(0, 100, 0));
		g.setFont(new Font("Candara", Font.BOLD, 24));
		g.drawString(capstr, 8, 24);
		g.dispose();
		//输出图片
		response.setContentType("image/jpeg");
	
		OutputStream strm;
		try {
			strm = response.getOutputStream();
			ImageIO.write(image, "jpeg", strm);
			strm.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	@RequestMapping(value="/shiroquery" , produces = "application/json; charset=utf-8")
	@RequiresPermissions("item:query")
	@ResponseBody
	public String shiroquery(HttpSession session)throws Exception{
		
		//
		Subject subject=SecurityUtils.getSubject();
		
		
		if(subject.isPermitted("user:write")) {
			System.out.println("该用户有写权限");
		}else {
			System.out.println("该用户没有写权限"); 
		}
		
		if(subject.isPermitted("user:create")) {
			return "该用户有创建权限";
		}else {
			return "该用户没有有创建权限";
		}
		
		//重定向到商品查询页面
	
	}
}
