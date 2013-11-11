package com.angularspringtest.resources;

import com.angularspringtest.model.Course;
import com.angularspringtest.model.CourseJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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
    public List<Course> getAll(@QueryParam("q") String query, @QueryParam("sort") String sort,
                               @QueryParam("desc") Boolean desc, @QueryParam("limit") Integer limit,
                               @QueryParam("offset") Integer offset) {
        return jdbcTemplate.listCourses(query, sort, desc != null ? desc : false, limit, offset);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Course course){
        System.out.println(course.toString());
        jdbcTemplate.create(course);
    }

    @GET
    @Path("/{ID}")
    public Course getCourse(@PathParam("ID") Integer id) {
        return jdbcTemplate.getCourse(id);
    }

    @PUT
    @Path("/{ID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("ID") Integer id, Course course){
        System.out.println(course.toString());
        jdbcTemplate.update(id, course);
    }

    @DELETE
    @Path("/{ID}")
    public void deleteCourse(@PathParam("ID") Integer id) {
        jdbcTemplate.delete(id);
    }
}
