package com.angularspringtest.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TicketMapper implements RowMapper<Ticket> {
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();

        ticket.setId(rs.getInt("ID"));
        ticket.setName(rs.getString("NAME"));
        ticket.setOpenDate(rs.getTimestamp("OPEN_DATE"));
        ticket.setDescription(rs.getString("DESCRIPTION"));
        ticket.setRefID(rs.getString("REF_ID"));

        return ticket;
    }
}