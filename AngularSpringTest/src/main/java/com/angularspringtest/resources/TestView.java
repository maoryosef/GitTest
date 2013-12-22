package com.angularspringtest.resources;

import com.angularspringtest.model.Ticket;
import com.angularspringtest.model.TicketIDsBean;
import com.angularspringtest.model.TicketJDBCTemplate;
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
    TicketJDBCTemplate jdbcTemplate;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Ticket> getAll(@QueryParam("q") String query, @QueryParam("sort") String sort,
                               @QueryParam("desc") Boolean desc, @QueryParam("limit") Integer limit,
                               @QueryParam("offset") Integer offset) {
        return jdbcTemplate.listTickets(query, sort, desc != null ? desc : false, limit, offset);
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("SetRef/{ID}/{RefID}")
    public Ticket setRefId(@PathParam("ID") Integer id, @PathParam("RefID") String ref_id){
        Ticket ticket = jdbcTemplate.getTicket("id", id);
        ticket.setRefID(ref_id);
        jdbcTemplate.update(id, ticket);

        return ticket;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(Ticket ticket){
        System.out.println(ticket.toString());
        Integer id = jdbcTemplate.create(ticket);

        return "{\"id\": \"" + id + "\"}";
    }

    @GET
    @Path("/{ID}")
    public Ticket getTicket(@PathParam("ID") Integer id) {
        return jdbcTemplate.getTicket("id", id);
    }

    @PUT
    @Path("/{ID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("ID") Integer id, Ticket ticket){
        System.out.println(ticket.toString());
        jdbcTemplate.update(id, ticket);
    }

    @DELETE
    @Path("/{ID}")
    public void deleteTicket(@PathParam("ID") Integer id) {
        jdbcTemplate.delete(id);
    }

    @POST
    @Path("/DeleteMulti")
    public void deleteMulti(TicketIDsBean idList) {
        jdbcTemplate.deleteMulti(idList.getIds());
    }
}
