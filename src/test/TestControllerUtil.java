package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.fkit.controller.RedisController;
import org.fkit.controller.TestController;
import org.fkit.service.PersonService;
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.mock.web.MockHttpServletRequest;  
import org.springframework.mock.web.MockHttpServletResponse;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  

  
/** 
 * @author zhzh 
 * 2015-4-7 
 */ 
//直接Controller调用方法
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration({"classpath:/spring-*.xml","classpath:/springmvc-config.xml"}) 
//@ContextConfiguration("file:WebContent/WEB-INF/springmvc-config.xml,file:WebContent/WEB-INF/spring-*.xml")
//@ContextConfiguration("file:WebContent/WEB-INF/springmvc-config.xml,file:WebContent/WEB-INF/spring-*.xml")
public class TestControllerUtil {  
  
    // 模拟request,response    
    private MockHttpServletRequest request;    
    private MockHttpServletResponse response;     
        
    // 注入loginController    
//    @Autowired    
//    TestController testController ; 
    private TestController testController =new TestController();
    @Autowired  
    PersonService personService;
    // 执行测试方法之前初始化模拟request,response    
    @Before      
    public void setUp(){      
        request = new MockHttpServletRequest();        
        request.setCharacterEncoding("UTF-8");        
        response = new MockHttpServletResponse();        
    }           
    /**  
     *   
     * @Title：testLogin  
     * @Description: 测试用户登录    
     */    
    @Test    
    public void testLogin() {     
        try {   
        	System.out.println("testLogin测试");
            request.setParameter("userName", "admin");  
            request.setParameter("password", "2");  
            assertEquals("login",testController.loginTest(request,response)) ;    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }   
    
    @Test    
    public void savePerson() {     
        try {   
        	personService.savePerson(); 
        	System.out.println("savePerson测试");
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    } 
}  