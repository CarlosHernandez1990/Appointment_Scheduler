package sample.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class contains methods and variables that compose the first level division class.
 */

public class FirstLevelDivisions{
   int divisionId;
   String division;
   LocalDateTime createDate;
   String createdBy;
   Timestamp lastUpdate;
   String lastUpdatedBy;
   int countryID;

    /**
     * This is the first level division constructor.
     * @param divisionId This is the ID of the first level division.
     * @param division This is the name of the first level division.
     * @param createDate This is when the first level division was created.
     * @param createdBy This is who created a specific first level division.
     * @param lastUpdate This is the last update made on a first level division.
     * @param lastUpdatedBy This is who last updated a first level division.
     * @param countryID This is the country ID that pertains to a first level division.
     */

    public FirstLevelDivisions(int divisionId, String division, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int countryID) {
        this.divisionId = divisionId;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * This method gets the ID of a first level division.
     * @return This is the first level division ID that is being returned.
     */

    public int getDivisionId() {
        return divisionId;
    }

    /**
     * The method sets the ID of a first level division.
     * @param divisionId This is the ID being set for a first level division.
     */

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * This method gets the division name of a first level division.
     * @return This the name of a first level division being returned.
     */

    public String getDivision() {
        return division;
    }

    /**
     * This method sets the division name of first level division.
     * @param division This is the division name of a first level division that is being set.
     */

    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * This method gets the created date of a first level division.
     * @return This is the created date of a first level division that is being returned.
     */

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * This method sets the creation date of first level division.
     * @param createDate This is the creation date of a first level division that is being set.
     */

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * This method gets who created a first level division.
     * @return This is who created a first level division that is being returned.
     */

    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method sets who created the first level division.
     * @param createdBy This is who created the first level division that is being set.
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method gets a timestamp of when a first level division was created.
     * @return This returns the timestamp of when the first level division was created.
     */

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method sets when the last update of a first level division occurred.
     * @param lastUpdate This is the timestamp of the last update on a first level division that is being set.
     */

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * This method gets who last updated a first level division.
     * @return This is who created a first level division that is being set.
     */

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method sets who last updated a first level division.
     * @param lastUpdatedBy This is who last updated a first level division that is being set.
     */

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method gets a country ID from a first level division.
     * @return This is the country ID that is related to a first level division that is being returned.
     */

    public int getCountryID() {
        return countryID;
    }

    /**
     * This method sets a country ID of a first level division.
     * @param countryID This is the country ID of a first level division that is being set.
     */

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

}
