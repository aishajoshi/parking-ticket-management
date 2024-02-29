package com.pascal.ptm.service;

import com.pascal.ptm.entities.Ticket;
import com.pascal.ptm.repo.TicketRepo;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class TicketService {
    private final TicketRepo ticketRepo;

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

    public Ticket getTicketByTicketNumber(String ticketNumber) {
        try {
            if (ticketNumber == null || ticketNumber.isEmpty()) {
                System.out.println("Invalid ticket number.");
                return null;
            }
            return ticketRepo.getTicketByTicketNumber(ticketNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkoutTicket(String ticketNumber) {
        try {

            Ticket  ticket = this.getTicketByTicketNumber(ticketNumber);
            if (ticket == null){
                System.out.println("Ticket not found");
                return false;
            }
            LocalDateTime entryTime = ticket.getEntryTime();
            LocalDateTime exitTime = ticket.getExitTime();




            return ticketRepo.saveTicketCheckoutDetail(ticketId, exitTime, updatedBy, totalAmount);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return false; // Return false indicating failure
        }
    }

    private boolean isValidTicket(Ticket ticket) {
        return ticket.getVehicleNumber() != null && !ticket.getVehicleNumber().isEmpty();
    }

    private String generateTicketNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String datePart = dateFormat.format(new Date());
        Random random = new Random();
        int randomNumber = random.nextInt(10000);

        return datePart + String.format("%04d", randomNumber);
    }
}
