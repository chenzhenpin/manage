package test;

import static org.junit.Assert.*;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.fkit.controller.RedisController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class) 

//@WebAppConfiguration(value = "src/main/webapp")   
//@ContextConfiguration({"classpath*:/spring-*.xml,classpath*:/springmvc-config.xml" })
@ContextConfiguration({"classpath:/spring-*.xml","classpath:/springmvc-config.xml"}) 
@WebAppConfiguration
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否    
@TransactionConfiguration(defaultRollback = true)    
@Transactional  
public class TestWebContent { 
	
	  // 模拟request,response    
//    private MockHttpServletRequest request;    
//    private MockHttpServletResponse response;    
	
	@SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;  

//    @Autowired  
//    ServletContext context;  
//    @Autowired
//    RedisController  redisController;
      
    @Before  
    public void setUp() {  
//    	request = new MockHttpServletRequest();        
//        request.setCharacterEncoding("UTF-8");        
//        response = new MockHttpServletResponse(); 
    	mockMvc = MockMvcBuilders.webAppContextSetup( this.wac ).build();  
        //mockMvc = MockMvcBuilders.standaloneSetup( redisController).build() ;  
    }  
   
    @Test  
    public void test() throws Exception {  
        assertNotNull(mockMvc);  
        mockMvc.perform( MockMvcRequestBuilders.get("/hello") )  
        .andDo(print()).andExpect(status().isOk()); ;    
   
    }  
      
    
    @Test
    public void testC() {
    	System.out.println("单元测试");
    }
} 
