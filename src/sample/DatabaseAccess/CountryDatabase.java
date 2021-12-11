package sample.DatabaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.DateTimeStringConverter;
import sample.Model.Country;
import sample.helper.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This Class contains methods that retrieves countries information from the sql database.
 */

public class CountryDatabase {

    /**
     * This method is in charge of returning observable lists of countries.
     * This method retrieves country's information and returns them in the form of an observable list.
     * @return This returns an oberservable list of countries.
     * @throws SQLException This will throw SQLExceptions.
     */

    public static ObservableList<Country> getCountries() throws SQLException {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                String createDates = rs.getString("Create_Date");
                DateTimeFormatter converter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createDate = LocalDateTime.parse(createDates, converter);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Country c = new Country(countryId, countryName, createDate, createdBy, lastUpdate, lastUpdatedBy);
                countryList.add(c);
            }

        return countryList;
    }
}
