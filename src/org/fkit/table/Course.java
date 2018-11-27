package org.fkit.table;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("course")
public class Course {
	private Integer id;
	private String name;
	private List<Clazz> clazzs;
	private List<Student> students;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Clazz> getClazzs() {
		return clazzs;
	}
	public void setClazzs(List<Clazz> clazzs) {
		this.clazzs = clazzs;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public String toString(){
	 return this.name;
	 
	}
	

}
