package com.angularspringtest.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CourseMapper implements RowMapper<Course> {
   public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
      Course course = new Course();
      course.setId(rs.getInt("ID"));
      course.setName(rs.getString("NAME"));
      course.setStartDate(rs.getTimestamp("STARTDATE"));

      return course;
   }
}