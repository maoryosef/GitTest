package com.angularspringtest.resources;

import com.angularspringtest.model.Ticket;
import com.angularspringtest.model.TicketJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yosef
 * Date: 11/10/13
 * Time: 1:57 PM
 */

@Component
@Path("/Admin")
public class AdminView {
    @Autowired
    TicketJDBCTemplate jdbcTemplate;

    @GET
    @Path("/Seed")
    public String reseed() {
        String message = "ok";

        try{
            System.out.println("Deleting db");
            jdbcTemplate.execute("DROP TABLE Ticket");

            String createTableSQL = "CREATE TABLE Ticket(\n";
            createTableSQL += "ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),\n";
            createTableSQL += "NAME VARCHAR(120) NOT NULL,\n";
            createTableSQL += "DESCRIPTION CLOB NOT NULL,\n";
            createTableSQL += "REF_ID VARCHAR(20),\n";
            createTableSQL += "OPEN_DATE TIMESTAMP ,\n";
            createTableSQL += "PRIMARY KEY (ID))";

            System.out.println("Creating db...");
            jdbcTemplate.execute(createTableSQL);

            int numOfSeeds = (int)getRandomNum(200, 1000);

            System.out.println("Seeding " + numOfSeeds + " items");

            for (int i = 0; i < numOfSeeds; i++) {
                long openDate = System.currentTimeMillis() + getRandomNum(10000, 800000000);
                String ticketName = "Ticket #" + (int)(getRandomNum(100, numOfSeeds));
                jdbcTemplate.create(new Ticket(ticketName, null, ticketName,  new Date(openDate)));
            }

            message += " " + numOfSeeds;
        } catch(Exception e) {
            e.printStackTrace();
            message = "fail";
        } finally {
            return message;
        }
    }

    private long getRandomNum(long min, long max) {
        return (long)(Math.random() * (max - min) + min);
    }
}
