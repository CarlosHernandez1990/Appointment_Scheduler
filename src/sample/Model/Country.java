package sample.Model;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * This class contains all methods and variables that manage the country class.
 */
public class Country {
    int countryId;
    String countryName;
    LocalDateTime createDate;
    String createdBy;
    Timestamp Last_Update;
    String Last_Updated_by;

    /**
     * This is the constructor of the country class.
     * @param countryId This represents to country's ID.
     * @param countryName This is the name of the country.
     * @param createDate This is the date of when the country record is created.
     * @param createdBy This is who created the country record.
     * @param last_Update This is when the country date was last updated.
     * @param last_Updated_by This is who last updated the country date.
     */
    public Country(int countryId, String countryName, LocalDateTime createDate, String createdBy, Timestamp last_Update, String last_Updated_by) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        Last_Update = last_Update;
        Last_Updated_by = last_Updated_by;
    }

    /**
     * This method gets a country's ID.
     * @return This is the country ID that is being returned.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * This method sets the ID field of a country
     * @param countryId This is the country ID that is being set.
     */

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This method gets the name of a country.
     * @return This is the county's name that is being returned.
     */

    public String getCountryName() {
        return countryName;
    }

    /**
     * This is the method that sets a country's name in the database.
     * @param countryName This is the country name that is being returned.
     */

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * This method gets the date of when the country's record was created.
     * @return This returns the country's record created date.
     */

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * This method sets the time of when a country record was created.
     * @param createDate This is the value of creation date that is being passed to the method.
     */

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * This method gets the user info of who created the country's record.
     * @return This is who created a country record.
     */

    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method sets who created the country's record.
     * @param createdBy This is the user who created a country's record that is being set.
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method gets a timestamp of when the country's record was last updated.
     * @return This returns a timestamp of when record was last updated.
     */

    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * This method sets a timestamp of when a country's record was latst updated.
     * @param last_Update This when the country's record was last updated.
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * This method gets who last updated a country's record.
     * @return This returns who last updated a country's record.
     */

    public String getLast_Updated_by() {
        return Last_Updated_by;
    }

    /**
     * This method sets who last updated a country's record.
     * @param last_Updated_by This is the variable being set of who last updated a country's record.
     */

    public void setLast_Updated_by(String last_Updated_by) {
        Last_Updated_by = last_Updated_by;
    }


}
