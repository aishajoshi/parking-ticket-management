package com.pascal.ptm.service;

import com.pascal.ptm.entities.Ticket;
import com.pascal.ptm.repo.TicketRepo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
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
            Ticket ticket = this.getTicketByTicketNumber(ticketNumber);
            if (ticket == null) {
                System.out.println("Ticket not found");
                return false;
            }

            LocalDateTime exitTime = LocalDateTime.now(); // Assuming exit time is the current time
            int ticketId = ticket.getTicketId(); // Assuming you have a method to get ticket ID from Ticket object

            long durationSeconds = Duration.between(ticket.getEntryTime(), exitTime).getSeconds(); // Calculate duration in seconds
            double totalAmount = calculateTotalAmount(durationSeconds); // Calculate total amount
            int updatedBy = 0; // Assuming you have a method to get the user ID who is checking out the ticket

            return ticketRepo.saveTicketCheckoutDetail(ticketId, exitTime, updatedBy, totalAmount, durationSeconds);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return false; // Return false indicating failure
        }
    }

    private double calculateTotalAmount(long durationSeconds) {
        double totalAmount = 0;

        if (durationSeconds >= 60*60) { // If duration is more than or equal to an hour
            totalAmount = Math.ceil(durationSeconds / 3600.0) * 25; // Hourly rate: 25 rs
        } else if (durationSeconds >= 60*30) { // If duration is more than or equal to half an hour
            totalAmount = Math.ceil(durationSeconds / 1800.0) * 15; // Half hour rate: 15 rs
        } else if (durationSeconds >= 300) { // If duration is more than or equal to 5 minutes
            totalAmount = 10; // Less than 5 min rate: 10 rs
        }
        return totalAmount;
    }

    // Method to calculate total amount based on entry and exit times (you can adjust the logic accordingly)
    private double calculateTotalAmount(LocalDateTime entryTime, LocalDateTime exitTime) {
        // Your logic to calculate total amount based on entry and exit times
        // For example, you can calculate duration and multiply by rate
        return 0.0; // Placeholder value
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
