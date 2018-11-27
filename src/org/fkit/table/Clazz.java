package org.fkit.table;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("clazz")
public class Clazz {
	private Integer id;
	private String code;
	private List<Student> students;
	private List<Course> courses;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public String toString(){
		return "Class["+this.code+"]";
	}
}