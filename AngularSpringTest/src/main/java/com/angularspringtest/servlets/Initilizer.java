package com.angularspringtest.servlets;

import com.angularspringtest.model.Ticket;
import com.angularspringtest.model.TicketJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yosef
 * Date: 11/10/13
 * Time: 2:59 PM
 */
public class Initilizer extends HttpServlet {

    @Autowired
    TicketJDBCTemplate jdbcTemplate;

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
            String createTableSQL = "CREATE TABLE Ticket(\n";
            createTableSQL += "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),\n";
            createTableSQL += "NAME VARCHAR(120) NOT NULL,\n";
            createTableSQL += "DESCRIPTION CLOB NOT NULL,\n";
            createTableSQL += "REF_ID VARCHAR(20),\n";
            createTableSQL += "OPEN_DATE TIMESTAMP ,\n";
            createTableSQL += "PRIMARY KEY (ID))";

            try {
                jdbcTemplate.execute(createTableSQL);
                jdbcTemplate.create(new Ticket("ticket1", null, "ticket desc1", new Date(System.currentTimeMillis() + 50000)));
                jdbcTemplate.create(new Ticket("ticket2", null, "ticket desc2", new Date(System.currentTimeMillis() + 60000)));
                jdbcTemplate.create(new Ticket("ticket3", null, "ticket desc3", new Date(System.currentTimeMillis() + 70000)));
                jdbcTemplate.create(new Ticket("ticket4", null, "ticket desc4", new Date(System.currentTimeMillis() + 80000)));
                jdbcTemplate.create(new Ticket("ticket5", null, "ticket desc5", new Date(System.currentTimeMillis() + 90000)));
                jdbcTemplate.create(new Ticket("ticket6", null, "ticket desc6", new Date(System.currentTimeMillis() + 100000)));
                jdbcTemplate.create(new Ticket("ticket7", null, "ticket desc7", new Date(System.currentTimeMillis() + 110000)));
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
