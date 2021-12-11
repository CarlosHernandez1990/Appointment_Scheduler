package sample.DatabaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Users;
import sample.helper.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains all the methods used to interact with the users database.
 */

public class UserDatabase {

    /**
     * This method gets all users from the database.
     * This method obtains all users information and returns it in an observable list.
     * @return This returns the an observable users list.
     */

    public static ObservableList<Users> getUsers(){
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                String createDates = rs.getString("Create_Date");
                DateTimeFormatter converter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createDate = LocalDateTime.parse(createDates, converter);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Users users = new Users(userID, userName ,password , createDate, createdBy,lastUpdate, lastUpdatedBy);
                usersList.add(users);
            }
        }

        catch(SQLException e){}

        return usersList;
    }

    /**
     * This method gets a users password.
     * This method gets a users password by passing it a string that contains a username. This method is used to compare
     * user names with matching passwords.
     * @param userName This string contain the username.
     * @return This returns a users password.
     */

    public static String getPassword(String userName){
        String password = null;
        try{
            String sql = "SELECT Password FROM users Where User_Name = '" + String.valueOf(userName) + "'";
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                password = rs.getString("Password");
            }
        }

        catch(SQLException e){}

        return password;
    }

    /**
     * This method gets a users ID.
     * This method gets a users ID by passing a string that represents a username.
     * @param userName This string contains the user name.
     * @return This returns an integer value that represents a user ID.
     * @throws SQLException This will throw an SQLException.
     */

    public static int getUserID(String userName) throws SQLException {
        int userID = 0;
        String sql = "SELECT User_ID FROM users WHERE User_Name = '" + String.valueOf(userName) + "'";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            userID = rs.getInt("User_ID");
        }

        return userID;
    }

    /**
     * The method gets the user name of a user.
     * This method gets the user name of  a user by passing in the user's user ID.
     * @param userID This is the user ID that is passed into the method.
     * @return This returns the user name.
     * @throws SQLException This will throw SQLExceptions.
     */
    public static String getUserName(int userID) throws SQLException {
        String userName = null;
        String sql = "SELECT User_Name FROM users WHERE User_ID = '" + userID + "'";
        PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            userName = rs.getString("User_Name");
        }

        return userName;
    }
}
