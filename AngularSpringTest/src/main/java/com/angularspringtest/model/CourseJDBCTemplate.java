package com.angularspringtest.model;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class CourseJDBCTemplate implements CourseDAO {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }

   public void create(String name) {
      String SQL = "insert into Course (name) values (?)";
      
      jdbcTemplateObject.update( SQL, name);
      System.out.println("Created Record Name = " + name);
      return;
   }

   public Course getCourse(Integer id) {
      String SQL = "select * from Course where id = ?";
      Course course = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{id}, new CourseMapper());
      return course;
   }

    @Override
    public List<Course> listCourses(String query, String sort, boolean desc) {
        String SQL = "SELECT * FROM Course WHERE 1=1";

        if (query != null) {
            SQL += " AND name LIKE '%" + query + "%'";
        }

        if (sort != null) {
            SQL += " ORDER BY " + sort + " " + ((desc)?"DESC":"ASC");
        }



        List <Course> courses = jdbcTemplateObject.query(SQL,
                new CourseMapper());
        return courses;
    }

   public void delete(Integer id){
      String SQL = "delete from Course where id = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
      return;
   }

   public void update(Integer id, String name){
      String SQL = "update Student set name = ? where id = ?";
      jdbcTemplateObject.update(SQL, name, id);
      System.out.println("Updated Record with ID = " + id );
      return;
   }

   @Override
	public void execute(String sql) {
		jdbcTemplateObject.execute(sql);
	}
}