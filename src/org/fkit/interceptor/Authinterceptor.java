package org.fkit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Authinterceptor implements HandlerInterceptor {
	//要拦截的路由
	private static final String[] FACTH_URI={"/upload","/uploadForm"};
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception exception )
		throws Exception{
		System.out.println("在视图渲染后");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("在视图渲染前，controller后调用");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//获取请求的地址
		String ServletPath=request.getServletPath();
		for (String s : FACTH_URI ){
			if(ServletPath.equals(s)){
				String username=(String)request.getSession().getAttribute("username");
				String password=(String)request.getSession().getAttribute("password");
				System.out.println(username.equals("chen"));
				System.out.println( password.equals("11111"));
				if(username.equals("chen")&& password.equals("11")){
					System.out.println(ServletPath);
					System.out.println("认证通过");
					return true;
					
				}else{
					System.out.println(ServletPath);
					System.out.println("拦截");
					return false;
				}
				
			}
		}
		System.out.println(ServletPath);
		System.out.println("不拦截");
		return true;
	}

}
