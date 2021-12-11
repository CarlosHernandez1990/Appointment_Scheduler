package sample.DatabaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.AppointmentReport;
import sample.Model.Appointments;
import sample.helper.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * This class is the interface between the Appointments class and the sql database.
 */

public class AppointmentDatabase {

    /**
     * This method is in charge of getting all appointments.
     * This method returns an observable list contain all appointments extracted from the sql database.
     * @return This returns the appointments list.
     * @throws SQLException This will throw SQLExceptions.
     */

    public static ObservableList<Appointments> getAppointments() throws SQLException {
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            DateTimeFormatter dateConversion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Timestamp starts = rs.getTimestamp("Start");
            Timestamp ends = rs.getTimestamp("End");
            Timestamp createdDates = rs.getTimestamp("Create_Date");
            LocalDateTime start = null;
            LocalDateTime end = null;
            LocalDateTime createdDate = null;
           if (starts!=null) start = starts.toLocalDateTime();
           if (ends != null) end = ends.toLocalDateTime();
           if (createdDates != null)createdDate = createdDates.toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, createdDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            appointmentList.add(appointments);
        }

        return appointmentList;
    }

    /**
     * This method is in charge of adding an appointment.
     * This method collects different variables to create a new appointment record in the sql database.
     * @param title       The title of the appointment.
     * @param description The description of the appointment.
     * @param location    The location of the appointment.
     * @param type        The type of appointment.
     * @param contactID   The contactID of the contact.
     * @param start       The start time and date of the appointment.
     * @param ends        Then end time and date of the appointment.
     * @param customerID  The customer ID of the customer.
     * @param userID      The user ID of the user.
     * @throws SQLException This will throw SQLException.
     */

    public static void addAppointment(String title, String description, String location, String type, int contactID, LocalDateTime start, LocalDateTime ends, int customerID, int userID, String createdBy, String lastUpdatedBy) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Contact_ID, Start, End, Customer_ID, User_ID, Created_By, Last_Updated_By) Values( ?, ?, ?, ?,(SELECT Contact_ID FROM contacts WHERE contacts.Contact_ID = ?), ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setInt(5, contactID);
        ps.setTimestamp(6, Timestamp.valueOf(start));
        ps.setTimestamp(7, Timestamp.valueOf(ends));
        ps.setInt(8, customerID);
        ps.setInt(9, userID);
        createdBy = UserDatabase.getUserName(userID);
        ps.setString(10,createdBy );
        lastUpdatedBy = UserDatabase.getUserName(userID);
        ps.setString(11, UserDatabase.getUserName(userID));
        ps.execute();
    }

    /**
     * This method is in charge of updating appointments.
     * This method collects variables that will be used to update a certain appointment located by the appointment ID.
     * @param title         The title of the appointment.
     * @param description   The description of the appointment.
     * @param location      The location of the appointment.
     * @param types         The type of appointment.
     * @param contactID     The contactID of the contact.
     * @param start         The start time and date of the appointment.
     * @param end           Then end time and date of the appointment.
     * @param customerID    The customer ID of the customer.
     * @param userID        The user ID of the user.
     * @param appointmentID The ID of the appointment.
     * @throws SQLException This will throw SQLException.
     */

    public static void updateAppointments(String title, String description, String location, String types, int contactID,
                                          LocalDateTime start, LocalDateTime end, int customerID, int userID, int appointmentID) throws SQLException {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Contact_ID = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Last_Updated_By = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, types);
            ps.setInt(5, contactID);
            ps.setTimestamp(6, Timestamp.valueOf(start));
            ps.setTimestamp(7, Timestamp.valueOf(end));
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(11, appointmentID);
            ps.setString(10, UserDatabase.getUserName(userID));
            ps.executeUpdate();
        } catch (NullPointerException e) {
        }
    }

    /**
     * This method is in charge of the deletion of an appointment.
     * This method deletes an appointment from the sql database by searching for a corresponding appointment ID.
     * @param appointmentId This variable contains the ID of the corresponding appointment that is going to be deleted.
     * @throws SQLException This will throw SQLExceptions.
     */

    public static void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.execute();
    }

    /**
     * This method is in charge of returning certain appointment schedules.
     * This method is passed an integer value that represents a contact's ID. The corresponding appointment schedules related
     * to a certain contact is returned as an observable appointment list.
     * @param contactID This is the ID of a certain contact.
     * @return The appointment list is returned.
     * @throws SQLException This will throw SQLException.
     */

    public static ObservableList<Appointments> getAppointmentSchedules(int contactID) throws SQLException {
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Contact_ID = " + contactID;
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            DateTimeFormatter dateConversion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Timestamp starts = rs.getTimestamp("Start");
            Timestamp ends = rs.getTimestamp("End");
            Timestamp createdDates = rs.getTimestamp("Create_Date");
            LocalDateTime start = starts.toLocalDateTime();
            LocalDateTime end = ends.toLocalDateTime();
            LocalDateTime createdDate = null;
            if(createdDates != null) createdDates.toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            contactID = rs.getInt("Contact_ID");
            Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, createdDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            appointmentList.add(appointments);
        }

        return appointmentList;
    }

    /**
     * This method is in charge of returning certain user's appointments.
     * This method is passed and integer value that represents a user's ID. The database is searched for appointments
     * related to that certain user. These appointments are returned in the form of an observable list.
     * @param userId This is the ID of a certain user.
     * @return This will return an observable appointment list.
     * @throws SQLException This will throw SQLExceptions.
     */

    public static ObservableList<Appointments> getAppointments(int userId) throws SQLException {
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE User_ID = " + userId;
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            DateTimeFormatter dateConversion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Timestamp starts = rs.getTimestamp("Start");
            Timestamp ends = rs.getTimestamp("End");
            Timestamp createdDates = rs.getTimestamp("Create_Date");
            LocalDateTime start = starts.toLocalDateTime();
            LocalDateTime end = ends.toLocalDateTime();
            LocalDateTime createdDate = null;
            if (createdDates != null) createdDate = createdDates.toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointments appointments = new Appointments(appointmentID, title, description, location, type, start , end, createdDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            appointmentList.add(appointments);
        }

        return appointmentList;
    }
}














