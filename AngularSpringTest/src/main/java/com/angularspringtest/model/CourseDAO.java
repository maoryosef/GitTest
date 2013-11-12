package com.angularspringtest.model;

import java.util.List;

import javax.sql.DataSource;

public interface CourseDAO {
	   public void setDataSource(DataSource ds);

	   public Integer create(Course course);
	   
	   public Course getCourse(Integer id);
	   
	   public List<Course> listCourses(String query, String sort, boolean desc, Integer limit, Integer offset);
	   
	   public void delete(Integer id);
	   
	   public void update(Integer id, Course course);
	   
	   public void execute(String sql);
}
