package sample.DatabaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Customers;
import sample.helper.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class contains all the methods used to interact with customer records.
 */

public class CustomerDatabase {

    /**
     * This method retrieves all customer records.
     * This method returns all customer records in the form of an observable list.
     * @return This returns an observable customers list.
     */

    public static ObservableList<Customers> getCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        ObservableList<Customers> customerList = FXCollections.observableArrayList();
   //     try {
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String createDates = rs.getString("Create_Date");
                DateTimeFormatter dateConversion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createDate = null;
                if(createDates != null) LocalDateTime.parse(createDates, dateConversion);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                Customers customer = new Customers(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
                customerList.add(customer);
            }
   //     }

   //     catch (SQLException | NullPointerException | DateTimeParseException e){
   //         System.out.println("SQLException or NullpointerException");
    //  }

        return customerList;
    }

    /**
     * This method is responsible for add a customer to the database.
     * This method is used to collect different variables used to create a new customer record in the sql database.
     * @param customerName This is the name of the customer.
     * @param address This is the customer's corresponding address.
     * @param postalCode This is the customer's postal code.
     * @param phone This is the customer's phone number.
     * @param divisionId This the division ID that the customer is located in.
     * @throws SQLException This throws SQLExceptions.
     */

    public static void addCustomers(String customerName, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers ( Customer_Name, Address, Postal_Code, Phone, Division_ID) Values( ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.execute();
        }

        catch (SQLException e) {
            System.out.println("SQL Exception");
        }
    }

    /**
     * This method is responsible for deleting a customer's record.
     * The method deletes a customer record by being passed a customer ID value that corresponds with the customer record
     * that is being deleted.
     * @param customerID This is integer value of the Customer ID.
     * @throws SQLException This will throw SQLExceptions.
     */

        public static void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customers where Customer_ID = ?";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.execute();
        }

    /**
     * This method is responsible for updating a customer records.
     * This method updates a customer's record by collecting variables that contain info about that customer.
     * @param customerName This is the name of the customer.
     * @param address This is the customer's corresponding address.
     * @param postalCode This is the customer's postal code.
     * @param phone This is the customer's phone number.
     * @param divisionId This the division ID that the customer is located in.
     * @param customerID This is the customer's ID.
     * @throws SQLException This will throw SQLExceptions.
     */

    public static void updateCustomers(String customerName, String address, String postalCode, String phone, int divisionId, int customerID) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        try {
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);;
            ps.setInt(6, customerID);
            ps.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
