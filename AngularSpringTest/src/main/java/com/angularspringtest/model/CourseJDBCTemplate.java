package com.angularspringtest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class CourseJDBCTemplate implements CourseDAO {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   private SimpleJdbcInsert jdbcInsertor;


   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
      this.jdbcInsertor = new SimpleJdbcInsert(dataSource).withTableName("Course").usingGeneratedKeyColumns("id");
   }

    @Override
    public Integer create(Course course) {
      Map<String, Object> parameters = new HashMap<String, Object>(2);
      parameters.put("name", course.getName());
      parameters.put("startDate", course.getStartDate());
      Number newId = jdbcInsertor.executeAndReturnKey(parameters);

      System.out.println("Created Record = " + course.toString());
      System.out.println("Created RetVal = " + newId);

      return newId.intValue();
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
      String SQL = "update Course set name = ?, startDate = ? where id = ?";
      jdbcTemplateObject.update(SQL, course.getName(), course.getStartDate(), id);
      System.out.println("Updated Record with ID = " + id );
   }

   @Override
	public void execute(String sql) {
		jdbcTemplateObject.execute(sql);
	}
}