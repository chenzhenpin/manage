package org.fkit.controller;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.fkit.Mapper.TestMapper;
import org.fkit.common.DB;
import org.fkit.flieManage.Download;
import org.fkit.flieManage.FileInfo;
import org.fkit.flieManage.UploadFile;
import org.fkit.table.Clazz;
import org.fkit.table.Course;
import org.fkit.table.Student;
import org.fkit.table.User;
import org.fkit.validator.UserValidator;
import org.junit.runner.Request;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.fkit.quartz.QuartzManager;
import org.fkit.service.UserService;
import  org.fkit.Mapper.TestMapper;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


@Controller
public class MybaitsController{
	private static final Log logger =LogFactory.getLog(MybaitsController.class);
	 @Autowired 
	 private UserService userService;


	@RequestMapping(value="hello")
	public ModelAndView hello() throws IOException{
		logger.info("handlerRequest被调用");
		ModelAndView mv =new ModelAndView();
		mv.addObject("message", "Hello Word");
		mv.setViewName("welcome");
//		System.out.println(DB.getSqlSession());
//		SqlSession session=DB.getSqlSession();
//		User user=new User("chen","nan",22);
//		session.insert("org.fkit.MapperXml.UserMapper.insertUser",user);
//		session.commit();
//		session.close();
		User user=new User(76,"ch1","nan1",22);
		Boolean insert=DB.change(TestMapper.class, "saveUser", user);
		System.out.println("insert:"+insert);
		
		user.setAge(100);
		
		Boolean update=DB.change(TestMapper.class, "modifyUser", user);
		System.out.println("update:"+update);
		User userOne=(User)DB.selectOne(TestMapper.class, "selectById", 36);
		//List<User> userOne=(List)DB.selectList(TestMapper.class, "selectUser", user);
		//SqlSession session=DB.getSqlSession();
		//List<User> userOne=session.selectList("org.fkit.Mapper.TestMapper.selectById",76);
		System.out.println("userOne:"+userOne);
		List<User> userList=(List)DB.selectList(TestMapper.class, "selectAllUser");
		
		System.out.println("userList:"+userList.get(0).getName());
		//Boolean delete=DB.change(TestMapper.class, "removeUser", user);
		Boolean delete=DB.change(TestMapper.class, "removeUser", user);
		System.out.println("delete:"+delete);
		System.out.println("ok");
		
		return mv;
	}
	
	
	@RequestMapping(value="hello1")
	public ModelAndView hello1() throws IOException{
		logger.info("handlerRequest被调用");
		ModelAndView mv =new ModelAndView();
		mv.addObject("message", "Hello Word1");
		mv.setViewName("welcome");
		System.out.println(DB.getSqlSession());
		SqlSession session=DB.getSqlSession();
		User user=new User("chen","男",24);
		TestMapper pm=session.getMapper(TestMapper.class);
		pm.insertUser(user);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(Model model
			) throws IOException{
		    //return "forward:/register";转发
			return "redirect:/register";
	}
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String registerFrom(Model model
			) throws IOException{
			
			User user=new User();
			model.addAttribute("user",user);
			return "registerForm";
	}
	
		
	
	
	
	
	
	@RequestMapping(value="selectStudent")
	public String selectStudent()throws IOException{
		SqlSession session=DB.getSqlSession();
		List <Student> student_list=session.selectList("org.fkit.MapperXml.UserMapper.selectStudent");
		session.commit();
		session.close();
		for(Student stu: student_list){
			System.out.println(stu.getClazz().getCode()+stu.getCard().getCard());
		}
		return "success";
	}
	
	@RequestMapping(value="selectclass")
	public String selectClass()throws IOException{
		SqlSession session=DB.getSqlSession();
		List <Clazz> clazz_list=session.selectList("org.fkit.MapperXml.UserMapper.selectCalzz");
		session.commit();
		session.close();
		for(Clazz cla: clazz_list){
			System.out.println(cla);
			List<Student> students =cla.getStudents();
			for(Student stu :students){
				System.out.println(stu.getName()+stu.getSex()+stu.getAge()+cla.getCode());
			}
		}
		return "success";
	}
	
	@RequestMapping(value="selectStudentInfo")
	public String selectStudentInfo()throws IOException{
		SqlSession session=DB.getSqlSession();
		List <Student> students=session.selectList("org.fkit.MapperXml.UserMapper.studentInfo");
		session.commit();
		session.close();
		for(Student stu :students){
				System.out.println(stu.getCard().getCard());
		}
		
		return "success";
	}
	@RequestMapping(value="selectCourse")
	public String selectCourse()throws IOException{
		SqlSession session=DB.getSqlSession();
		List <Course> courses=session.selectList("org.fkit.MapperXml.UserMapper.selectCourse");
		session.commit();
		session.close();
		for(Course cour :courses){
			System.out.println(cour.getName()+"------课程--------");
			List<Clazz> clazzs=cour.getClazzs();
			for(Clazz clazz:clazzs){
				System.out.println(clazz.getCode()+"----班级---");
			}
			List<Student> students=cour.getStudents();
			for(Student student:students){
				System.out.println(student.getName()+"---学生-------");
				
			}
		}

		return "success";
	}
	
	@RequestMapping(value="testuser")
	public String testUser()throws IOException{
		SqlSession session=DB.getSqlSession();
		User user=new User(11,"chen","男",24);
		TestMapper pm=session.getMapper(TestMapper.class);
		pm.saveUser(user);
		user.setName("li");
		pm.modifyUser(user);
		User oneUser=pm.selectById(11);
		System.out.println(oneUser.getName());
		pm.removeById(11);
		List<User> allUser=pm.selectAllUser();
		for(User u:allUser){
			System.out.println(u.getName());
		}
		session.commit();
		session.close();
		return "success";
	}
	
	@RequestMapping(value="selectStudentById")
	public String selectStudentById()throws IOException{
		SqlSession session=DB.getSqlSession();
		TestMapper pm=session.getMapper(TestMapper.class);
		Student student=pm.selectStudentById(1);
		System.out.println(student.getName());
		session.commit();
		session.close();
		return "success";
	}
	
	@RequestMapping(value="selectClazzById")
	public String selectClazzById()throws IOException{
		SqlSession session=DB.getSqlSession();
		TestMapper pm=session.getMapper(TestMapper.class);
		Clazz clazz=pm.selectClazzById(1);
		List<Student> students=clazz.getStudents();
		for(Student student:students){
			System.out.println(student.getName());
		}
		session.commit();
		session.close();
		return "success";
	}
	
	@RequestMapping(value="selectCourseById")
	public String selectCourseById()throws IOException{
		SqlSession session=DB.getSqlSession();
		TestMapper pm=session.getMapper(TestMapper.class);
		Course course=pm.selectCourseById(1);
		List<Student> students=course.getStudents();
		for(Student student:students){
			System.out.println(student.getName());
		}
		session.commit();
		session.close();
		return "success";
	}
	
	
	
	@RequestMapping(value="rdata")
	@ResponseBody()
	public Object ReturnData(HttpServletRequest request,
			@RequestParam(value="param1",required=false) String param1,
			@RequestParam(value="param2") String param2
			) throws IOException{
			System.out.println(param1);
			System.out.println(param2);
			List<User> list =new ArrayList<User> (); 
			list.add(new User(12,"chen","男",24));
			list.add(new User(13,"chenp","男",25));
			//ShardedJedis shardJedis = shardedJedisPool.getResource();
	        //存入键值对
	        //shardJedis.set("key1","hello jedis");
	        //String result = shardJedis.get("key1");
	        //System.out.println(result);
	        //回收ShardedJedis实例
	        //shardJedis.close();
			return list;
	
	}
}


