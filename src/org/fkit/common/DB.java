package org.fkit.common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DB {
	
	//sqlSessionFactory 使用单列模式创建
	//SqlSession实例不能被共享，也不是线程安全
	private static SqlSessionFactory sqlSessionFactory=null;
	
	static{
		try {
			InputStream inputStream=Resources.getResourceAsStream("org/fkit/MapperXml/mybaits-config.xml");//
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
	}
	public static SqlSession getSqlSession() {
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	public static SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}
	public static Boolean change(Class clazz,String methodName,Object params) {
		SqlSession session=null;
		Boolean bool=false;
		try {
			System.out.println(clazz.getName());
			Class paramsType=params.getClass();
			session=getSqlSession();
			Object obj = session.getMapper(clazz);
			Method method=obj.getClass().getMethod(methodName, paramsType);
			method.invoke(obj, params);
			session.commit();
			bool=true;
			}catch (Exception e) {
				session.rollback();
				e.printStackTrace();
			}
			finally {
				session.close();
			}
		return bool;          
	}
	

	public static List<Object> selectList(Class clazz,String methodName,Object params) {
		SqlSession session=null;
		List<Object> result=null;
		try {
			session=getSqlSession();
			Class paramsType=params.getClass();
			Object obj = session.getMapper(clazz);
			Method method=obj.getClass().getMethod(methodName, paramsType);
			result =(List)method.invoke(obj, params);
			session.commit();
			}catch (Exception e) {
				session.rollback();
				e.printStackTrace();
			}
			finally {
				session.close();	
			}
		return result;
	}
	
	public static Object selectOne(Class clazz,String methodName,Object params) {
		SqlSession session=null;
		Object result=null;
		try {
			session=getSqlSession();
			Class paramsType=params.getClass();
			Object obj = session.getMapper(clazz);
			Method method=obj.getClass().getMethod(methodName, paramsType);
			result =method.invoke(obj, params);
			session.commit();
			}catch (Exception e) {
				session.rollback();
				e.printStackTrace();
			}
			finally {
				session.close();	
			}
		return result;
	}
	public static List<Object> selectList(Class clazz,String methodName) {
		SqlSession session=null;
		List<Object> result=null;
		try {
			session=getSqlSession();
			Object obj = session.getMapper(clazz);
			Method method=obj.getClass().getMethod(methodName,null);
			result =(List)method.invoke(obj);
			session.commit();
			}catch (Exception e) {
				session.rollback();
				e.printStackTrace();
			}
			finally {
				session.close();	
			}
		return result;
	}
	
	public static List<Object> selectPage(Class clazz,String methodName,Object params,RowBounds rowBounds) {
		SqlSession session=null;
		List<Object> result=null;
		try {
			session=getSqlSession();
			String clazzName=clazz.getName();
			String statement=clazzName+methodName;
			result=session.selectList(statement, params,rowBounds);
			session.commit();
			}catch (Exception e) {
				session.rollback();
				e.printStackTrace();
			}
			finally {
				session.close();	
			}
		return result;
	}
	
	
	

}
