package com.angularspringtest.model;

import java.util.List;

import javax.sql.DataSource;

public interface CourseDAO {
	   public void setDataSource(DataSource ds);

	   public void create(String name);
	   
	   public Course getCourse(Integer id);
	   
	   public List<Course> listCourses(String query, String sort, boolean desc);
	   
	   public void delete(Integer id);
	   
	   public void update(Integer id, String name);
	   
	   public void execute(String sql);
}
