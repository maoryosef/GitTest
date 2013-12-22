package com.angularspringtest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class TicketJDBCTemplate implements TicketDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private SimpleJdbcInsert jdbcInsertor;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.jdbcInsertor = new SimpleJdbcInsert(dataSource).withTableName("Ticket").usingGeneratedKeyColumns("id");
    }

    @Override
    public Integer create(Ticket ticket) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("NAME", ticket.getName());
        parameters.put("OPEN_DATE", ticket.getOpenDate());
        parameters.put("DESCRIPTION", ticket.getDescription());
        parameters.put("REF_ID", ticket.getRefID());
        Number newId = jdbcInsertor.executeAndReturnKey(parameters);

        System.out.println("Created Record = " + ticket.toString());
        System.out.println("Created RetVal = " + newId);

        return newId.intValue();
    }

    @Override
    public Ticket getTicket(String field, Object value) {
        String SQL = "select * from Ticket where " + field + " = ?";
        Ticket ticket = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{value}, new TicketMapper());

        return ticket;
    }

    @Override
    public List<Ticket> listTickets(String query, String sort, boolean desc, Integer limit, Integer offset) {
        String SQL = "SELECT * FROM Ticket WHERE 1=1";

        if (query != null) {
            SQL += " AND name LIKE '%" + query + "%'";
        }

        if (sort != null) {
            SQL += " ORDER BY " + sort + " " + ((desc) ? "DESC" : "ASC");
        }

        if (offset != null && offset > 0) {
            SQL += " OFFSET " + offset + " ROWS";
        }

        if (limit != null) {
            SQL += " FETCH NEXT " + limit + " ROWS ONLY";
        }

        List<Ticket> tickets = jdbcTemplateObject.query(SQL,
                new TicketMapper());

        return tickets;
    }

    public void delete(Integer id) {
        String SQL = "delete from Ticket where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    @Override
    public void deleteMulti(List<Integer> ids) {
        String SQL = String.format("delete from Ticket where id IN (%s)", ids.toString().replace('[', ' ').replace(']', ' '));
        jdbcTemplateObject.update(SQL);
        System.out.println("Deleted Records with ID = " + ids);
    }

    @Override
    public void update(Integer id, Ticket ticket) {
        String SQL = "update Ticket set name = ?, open_date = ?, description = ?, ref_id = ? where id = ?";
        jdbcTemplateObject.update(SQL, ticket.getName(), ticket.getOpenDate(), ticket.getDescription(), ticket.getRefID(), id);
        System.out.println("Updated Record with ID = " + id);
    }

    @Override
    public void execute(String sql) {
        jdbcTemplateObject.execute(sql);
    }
}