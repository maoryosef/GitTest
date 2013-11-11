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

    @Override
    public void create(Course course) {
      String SQL = "insert into Course (name, startDate) values (?,?)";

      jdbcTemplateObject.update( SQL, course.getName(), course.getStartDate());
      System.out.println("Created Record = " + course.toString());
   }

   public Course getCourse(Integer id) {
      String SQL = "select * from Course where id = ?";
      Course course = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{id}, new CourseMapper());
      return course;
   }

    @Override
    public List<Course> listCourses(String query, String sort, boolean desc, Integer limit, Integer offset) {
        String SQL = "SELECT * FROM Course WHERE 1=1";

        if (query != null) {
            SQL += " AND name LIKE '%" + query + "%'";
        }

        if (sort != null) {
            SQL += " ORDER BY " + sort + " " + ((desc)?"DESC":"ASC");
        }

        if (offset != null && offset > 0) {
            SQL += " OFFSET " + offset + " ROWS";
        }

        if (limit != null) {
            SQL += " FETCH NEXT " + limit + " ROWS ONLY";
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

    @Override
    public void update(Integer id, Course course){
      String SQL = "update Student set name = ?, startDate = ? where id = ?";
      jdbcTemplateObject.update(SQL, course.getName(), course.getStartDate(), id);
      System.out.println("Updated Record with ID = " + id );
   }

   @Override
	public void execute(String sql) {
		jdbcTemplateObject.execute(sql);
	}
}