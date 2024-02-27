package com.pascal.ptm.service;
/*
 * Created by Ashok Kumar Pant
 * Email: asokpant@gmail.com
 * Created on 27/02/2024.
 */

import com.pascal.ptm.entities.Ticket;
import com.pascal.ptm.repo.TicketRepo;

import java.sql.SQLException;

public class TicketService {
    TicketRepo ticketRepo;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Ticket createTicket(Ticket ticket) {
        try {
            if (ticket.getEntryTime() == null) {
                System.out.println("Entry time is required");
                return null;
            }
            ticketRepo.createTicket(ticket);
            //TODO get saved ticket
            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
