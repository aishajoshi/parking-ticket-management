package com.pascal.ptm.repo;
/*
 * Created by Ashok Kumar Pant
 * Email: asokpant@gmail.com
 * Created on 27/02/2024.
 */

import com.pascal.ptm.entities.Ticket;
import com.pascal.ptm.utils.Datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TicketRepo {
    private final Datasource datasource;

    public TicketRepo(Datasource datasource) {
        this.datasource = datasource;
    }

    public boolean createTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO ticket (vehicle_number, entry_time, exit_time, total_time, total_amount, created_by, updated_by, phone, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = this.datasource.getConnection().prepareStatement(sql)) {
            statement.setString(1, ticket.getVehicleNumber());
            statement.setTimestamp(2, Timestamp.valueOf(ticket.getEntryTime()));
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getExitTime()));
            statement.setLong(4, ticket.getTotalTime());
            statement.setDouble(5, ticket.getTotalAmount());
            statement.setInt(6, ticket.getCreatedBy());
            statement.setInt(7, ticket.getUpdatedBy());
            statement.setString(8, ticket.getPhone());
            statement.setString(9, ticket.getNote());

            statement.executeUpdate();
            return true;
        }
    }
}
