package com.pascal.ptm.examples;
/*
 * Created by Ashok Kumar Pant
 * Email: asokpant@gmail.com
 * Created on 27/02/2024.
 */

import com.pascal.ptm.entities.Ticket;
import com.pascal.ptm.repo.TicketRepo;
import com.pascal.ptm.service.TicketService;
import com.pascal.ptm.utils.Datasource;

public class TicketServiceExample {
    public static void main(String[] args) {
        System.out.println("Ticket Service Example");
        Datasource datasource = new Datasource();
        TicketRepo ticketRepo = new TicketRepo(datasource);
        TicketService ticketService = new TicketService(ticketRepo);

        Ticket ticket = new Ticket();
        ticket.setVehicleNumber("BA 1 PA 1234");

        ticket = ticketService.createTicket(ticket);
        System.out.println("Ticket created: " + ticket);
    }
}
