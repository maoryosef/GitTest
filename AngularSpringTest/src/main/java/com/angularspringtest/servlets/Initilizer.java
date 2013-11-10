package com.angularspringtest.servlets;

import com.angularspringtest.model.Course;
import com.angularspringtest.model.CourseJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yosef
 * Date: 11/10/13
 * Time: 2:59 PM
 */
public class Initilizer extends HttpServlet {

    @Autowired
    CourseJDBCTemplate jdbcTemplate;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);

        try {
            /*ServletContext servletContext = this.getServletContext();
            Resource res = new ServletContextResource(servletContext,  "/WEB-INF/applicationContext.xml");
            //BeanFactory bf = new FileSystemXmlApplicationContext("WEB-INF/applicationContext.xml");
            BeanFactory bf = new XmlBeanFactory(res);

            CourseJDBCTemplate jdbcTemplate = bf.getBean("courseJDBCTemplate", CourseJDBCTemplate.class);
            */
            String createTableSQL = "CREATE TABLE Course(\n";
            createTableSQL += "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),\n";
            createTableSQL += "NAME VARCHAR(20) NOT NULL,\n";
            createTableSQL += "PRIMARY KEY (ID))";

            try {
                jdbcTemplate.execute(createTableSQL);
                jdbcTemplate.create("course1");
                jdbcTemplate.create("course2");
                jdbcTemplate.create("course3");
                jdbcTemplate.create("course4");
                jdbcTemplate.create("course5");
                jdbcTemplate.create("course6");

            } catch (DataIntegrityViolationException e) {
                System.out.println("Table already exists");
            }

            /*System.out.println("------Listing Multiple Records--------" );
            List<Course> courses = jdbcTemplate.listCourses();
            for (Course record : courses) {
                System.out.println(record.toString());
            } */
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
