package sample.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class contains the variables and methods used to manage appointments.
 */

public class Appointments {
    int appointmentID;
    String title;
    String description;
    String location;
    String type;
    LocalDateTime start;
    LocalDateTime end;
    LocalDateTime createdDate;
    String createdBy;
    Timestamp lastUpdate;
    String lastUpdatedBy;
    int customerID;
    int userID;
    int contactID;

    /**
     * This is the constructor of the appointment class.
     * @param appointmentID This is the ID of an appointment.
     * @param title This is the title of an appointment.
     * @param description This is the description of an appointment.
     * @param location This is the location of an appointment.
     * @param type This is the type of appointment.
     * @param start This is the start time of an appointment.
     * @param end This is the end time of an appointment.
     * @param createdDate This the date that an appointment was created.
     * @param createdBy This is who the appointment was created by.
     * @param lastUpdate This is when the appointment was last updated.
     * @param lastUpdatedBy This is who updated the appointment last.
     * @param customerID The is the ID of a customer account.
     * @param userID This is the ID of a user.
     * @param contactID This is a contact's ID.
     */

    public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createdDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * This is used to retrieve an appointment ID.
     * @return This returns an appointment ID.
     */

    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * This is used to set an appointments ID.
     * @param appointmentID This is the appointment ID that is used to set an appointment ID.
     */

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     *This method is used to retrieve an appointments title.
     * @return This returns the title of an appointment.
     */

    public String getTitle() {
        return title;
    }

    /**
     * This method is used to set an appointments title.
     * @param title This is the title of an appointment that is being set.
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method is used to get an appointments description.
     * @return This returns an appointments description.
     */

    public String getDescription() {
        return description;
    }

    /**
     * This method is used to set the description of an appointment.
     * @param description This is the description that is being set.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method is used to get the appointment Location.
     * @return This returns the location.
     */

    public String getLocation() {
        return location;
    }

    /**
     * This method is used to set the location of an appointment
     * @param location This is the location that is being set.
     */

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * The is the method that is used to retrieve the type of appointment.
     * @return This is the type of appoinment
     */

    public String getType() {
        return type;
    }

    /**
     * This method is used to set the type of an appointment.
     * @param type This is the type of appointment that is being set.
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method is used to retrieve the start time of an appointment.
     * @return This is the start time variable that is being retrieved.
     */

    public LocalDateTime getStart() {
        return start;
    }

    /**
     * This method is used to set the start time of an appointment.
     * @param start This is the start time of an appointments that is being set.
     */

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * This method is used to get the end time of an appointment.
     * @return This is the end time of an appointment that is being returned.
     */

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * This method sets the end time of an appointment.
     * @param end This is the end time of an appointment that is being set.
     */

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * This method is used to get the created day of an appointment.
     * @return This is the created date of an appointment that is being returned.
     */

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * This method sets the created date of an appointment.
     * @param createdDate This is the created date of an appointment that is being set.
     */

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method is used to get the user info of who created the appointment.
     * @return This is the user info of who created the appointment.
     */

    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method sets who created the appointment.
     * @param createdBy This variable represents who created the appointment.
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method gets the timestamp of when the appointment was last updated.
     * @return This returns the timestamp of when the appointment was last updated.
     */

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method sets a timestamp of when the appointment was last updated.
     * @param lastUpdate This is the timestamp being set of when the appointment was last updated.
     */

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * This method gets the user info of the user who last updated the appointment.
     * @return This returns who last updated the appointment.
     */

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method sets the user info of the user who last updated the appointment.
     * @param lastUpdatedBy This is the user info of the user who last updated the appointment.
     */

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method gets the customer ID of a customer.
     * @return This is the customer ID of the customer's record.
     */

    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method set the customer ID of a customer.
     * @param customerID This is the variable that is set as the customer's ID.
     */

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * This method gets a user's ID.
     * @return This is the user ID that is returned.
     */

    public int getUserID() {
        return userID;
    }

    /**
     * This method sets a user's ID.
     * @param userID This is the user ID that is set.
     */

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * This method gets the contact's ID.
     * @return This is the contact's ID that is returned.
     */

    public int getContactID() {
        return contactID;
    }

    /**
     * This method sets a contact's ID.
     * @param contactID This the ID of the contact that is being set.
     */

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
