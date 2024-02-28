package com.pascal.ptm.repo;
/*
 * Created by Ashok Kumar Pant
 * Email: asokpant@gmail.com
 * Created on 27/02/2024.
 */

import com.pascal.ptm.entities.Ticket;
import com.pascal.ptm.utils.Datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TicketRepo {
    private final Datasource datasource;

    public TicketRepo(Datasource datasource) {
        this.datasource = datasource;
    }

    public boolean createTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO ticket (vehicle_number, ticket_number,entry_time, created_by, phone, note) VALUES (?, ?, ?, ?, ?,?)";

        try (PreparedStatement statement = this.datasource.getConnection().prepareStatement(sql)) {
            statement.setString(1, ticket.getVehicleNumber());
            statement.setString(2, ticket.getTicketNumber());
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getEntryTime()));
            statement.setInt(4, ticket.getCreatedBy());
            statement.setString(5, ticket.getPhone());
            statement.setString(6, ticket.getNote());

            statement.executeUpdate();
            return true;
        }
    }

    public Ticket getTicketByTicketNumber(String ticketNumber) throws SQLException {
        String sql = "select ticket_id, vehicle_number, ticket_number, entry_time,  exit_time, total_time, total_amount, created_by, phone, note from ticket where ticket_number = ?";

        PreparedStatement statement = this.datasource.getConnection().prepareStatement(sql);
        statement.setString(1, ticketNumber);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Ticket ticket = new Ticket();
            ticket.setTicketId(resultSet.getInt("ticket_id"));
            ticket.setVehicleNumber(resultSet.getString("vehicle_number"));
            ticket.setTicketNumber(resultSet.getString("ticket_number"));

            ticket.setEntryTime(resultSet.getTimestamp("entry_time").toLocalDateTime());
            if (resultSet.getTimestamp("exit_time") != null) {
                ticket.setExitTime(resultSet.getTimestamp("exit_time").toLocalDateTime());
            }
            ticket.setTotalTime(resultSet.getLong("total_time"));
            ticket.setTotalAmount(resultSet.getFloat("total_amount"));
            ticket.setCreatedBy(resultSet.getInt("created_by"));
            ticket.setPhone(resultSet.getString("phone"));
            ticket.setNote(resultSet.getString("note"));
            return ticket;
        }
        return null;
    }
}
