package sample.DatabaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.AppointmentReport;
import sample.helper.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains all the methods used to view and retrieve appointment reports.
 */

public class AppointmentReportDatabase {



    /**
     * This method returns an appointment report count organized by month.
     * This method returns an observable list that is grouped by appointment month and count of each appointment by month.
     * @return This returns and appointment report list grouped by month.
     * @throws SQLException This will throw SQLExceptions.
     */

    public static ObservableList<AppointmentReport> appointmentReportByMonth() throws SQLException {
        ObservableList<AppointmentReport> monthReport = FXCollections.observableArrayList();
        String sql = "SELECT (MonthName(Start)) as Month, count(Type) as Count, Type From Appointments group by Type";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String types = rs.getString("Type");
            int count = rs.getInt("Count");
            String month = rs.getString("Month");
            System.out.println(month);
            AppointmentReport placeholder = new AppointmentReport(types, count, month);
            monthReport.add(placeholder);
        }

        return monthReport;
    }

    /**
     * This method returns an appointment report count organized by Location.
     * This method returns an observable list that is grouped by appointment Location and count of each appointment by Location.
     * @return This returns and appointment report list grouped by Location.
     * @throws SQLException This will throw SQLExceptions.
     */

    public static ObservableList<AppointmentReport> appointmentReportByLocation() throws SQLException {
        ObservableList<AppointmentReport> appointReport = FXCollections.observableArrayList();
        String sql = "SELECT Country, Division, count(Appointment_ID) as Count From customers, countries, first_level_divisions, " +
                "appointments where appointments.Customer_ID = customers.Customer_ID and customers.Division_ID = first_level_divisions.Division_ID " +
                "and first_level_divisions.COUNTRY_ID = countries.Country_ID group by Division";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String country = rs.getString("Country");
            String division = rs.getString("Division");
            int count = rs.getInt("Count");

            AppointmentReport placeholder = new AppointmentReport(country, count, division);
            appointReport.add(placeholder);
        }

        return appointReport;
    }
}
