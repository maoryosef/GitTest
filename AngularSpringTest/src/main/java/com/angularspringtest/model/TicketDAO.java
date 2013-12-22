package com.angularspringtest.model;

import java.util.List;

import javax.sql.DataSource;

public interface TicketDAO {
    public void setDataSource(DataSource ds);

    public Integer create(Ticket ticket);

    public Ticket getTicket(String field, Object value);

    public List<Ticket> listTickets(String query, String sort, boolean desc, Integer limit, Integer offset);

    public void delete(Integer id);

    public void deleteMulti(List<Integer> ids);

    public void update(Integer id, Ticket ticket);

    public void execute(String sql);
}
