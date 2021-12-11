package sample.DatabaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.FirstLevelDivisions;
import sample.helper.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains all methods needed to interact with the First-Level-Divisions in the database.
 */

public class DivisionsDatabase {

    /**
     * This method obtains all First-Level-Divisions contained in the database.
     * This method gets all first level divisions and store them in an observable list.
     * @return This returns the observable divisions list
     */

    public static ObservableList<FirstLevelDivisions> getFirstLevelDivisions(){
        ObservableList<FirstLevelDivisions> divisionsList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                String createDates = rs.getString("Create_Date");
                DateTimeFormatter conversion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createDate = LocalDateTime.parse(createDates, conversion);
                String createdBY = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryID = rs.getInt("COUNTRY_ID");
                FirstLevelDivisions divisions = new FirstLevelDivisions(divisionId, division, createDate, createdBY, lastUpdate, lastUpdatedBy,countryID);
                divisionsList.add(divisions);
            }
        }

        catch (SQLException e){}

        return divisionsList;
    }

    /**
     * This method obtains country's name.
     * This method gets a county's name by being pass a integer values that represents a corresponding first level division.
     * @param divisionId This is the integer values that is used to find a country's name.
     * @return This returns a country's name.
     */

    public static String getCountryName(int divisionId){
        ObservableList<FirstLevelDivisions> divisionsList = FXCollections.observableArrayList();
        String countryName = null;
        try{
            String sql = "SELECT Country From countries, first_level_divisions where first_level_divisions.Division_ID = " + divisionId + " and first_level_divisions.COUNTRY_ID = countries.Country_ID";
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                countryName = rs.getString("Country");
            }
        }

        catch (SQLException e){}

       return countryName;
    }
}
