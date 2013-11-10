package com.angularspringtest.resources;

import com.angularspringtest.model.Course;
import com.angularspringtest.model.CourseJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yosef
 * Date: 11/10/13
 * Time: 1:57 PM
 */

@Component
@Path("/Test")
public class TestView {
    @Autowired
    CourseJDBCTemplate jdbcTemplate;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Course> getAll(@QueryParam("sort") String sort, @QueryParam("desc") Boolean desc) {
        return jdbcTemplate.listCourses(null, sort, desc);
    }
}
