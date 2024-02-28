package com.pascal.ptm.service;

import com.pascal.ptm.entities.Ticket;
import com.pascal.ptm.repo.TicketRepo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class TicketService {
    TicketRepo ticketRepo;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Ticket createTicket(Ticket ticket) {
        try {

            if (!isValidTicket(ticket)) {
                System.out.println("Invalid ticket information. Please check your input.");
                return null;
            }

            String ticketNumber = generateTicketNumber();

            ticket.setEntryTime(LocalDateTime.now());

            ticket.setTicketNumber(ticketNumber);

            ticketRepo.createTicket(ticket);

            // TODO: Get saved ticket if needed

            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private boolean isValidTicket(Ticket ticket) {
        if (ticket.getVehicleNumber() == null || Objects.equals(ticket.getVehicleNumber(), "")) {
            System.out.println("vehicle number is required");
            return false;
        }
        return true;
    }

    private String generateTicketNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String datePart = dateFormat.format(new Date());
        Random random = new Random();
        int randomNumber = random.nextInt(10000);

        return datePart + String.format("%04d", randomNumber);
    }
}