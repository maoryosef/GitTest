package com.angularspringtest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Course")
public class Course {
	String name;
	Integer id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Course [name=" + name + ", id=" + id + "]";
	}
}
