import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class connect {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/parking_ticket_management";

    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Establishing connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Inserting a ticket into the database
            insertTicket("ABC123", Timestamp.valueOf("2024-02-27 10:00:00"), Timestamp.valueOf("2024-02-27 12:00:00"), 2, 10.00, 1, 1, 1, "Some note");

            System.out.println("Ticket inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertTicket(String vehicleNumber, Timestamp entryTime, Timestamp exitTime, int totalTime, double totalAmount, int createdBy, int updatedBy, int customerId, String note) throws SQLException {
        String sql = "INSERT INTO ticket (vehicle_number, entry_time, exit_time, total_time, total_amount, created_by, updated_by, customer_id, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vehicleNumber);
            statement.setTimestamp(2, entryTime);
            statement.setTimestamp(3, exitTime);
            statement.setInt(4, totalTime);
            statement.setDouble(5, totalAmount);
            statement.setInt(6, createdBy);
            statement.setInt(7, updatedBy);
            statement.setInt(8, customerId);
            statement.setString(9, note);

            statement.executeUpdate();
        }
    }
}
