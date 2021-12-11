package sample.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class contains methods and variables that compose the users class.
 */

public class Users {
    int userID;
    String userName;
    String password;
    LocalDateTime createDate;
    String createdBy;
    Timestamp lastUpdate;
    String lastUpdatedBy;

    /**
     * This is the constructor of the users class.
     * @param userID This is the ID of a user.
     * @param userName This is the name of a user.
     * @param password This is the password of a user.
     * @param createDate This is the creation date of a user record.
     * @param createdBy This is who created the user record.
     * @param lastUpdate This is when the user record was last updated.
     * @param lastUpdatedBy This is who last updated the user record.
     */

    public Users(int userID, String userName, String password, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method gets the user's ID.
     * @return This returns the user's ID.
     */

    public int getUserID() {
        return userID;
    }

    /**
     * This sets a user's ID.
     * @param userID This is the user's ID that is being set.
     */

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * This method gets a user name.
     * @return This is the user name that is being returned.
     */

    public String getUserName() {
        return userName;
    }

    /**
     * This method sets a user name.
     * @param userName This is the user name that is being set.
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method gets a password of a user.
     * @return This is the password of a user that is being returned.
     */

    public String getPassword() {
        return password;
    }

    /**
     * This method sets a password of a user.
     * @param password This is the password of a user that is being set.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method gets the creation date of a user account.
     * @return This is the creation date of a user account that is being returned.
     */

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * This method sets the created date of user.
     * @param createDate This is the create date of a user account that is being returned.
     */

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * This method gets who created the user account.
     * @return This returns the person who created the user account.
     */

    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method sets who created a particular user account.
     * @param createdBy This is who created the user account that is being returned.
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method gets the time of when the last update occurred on a users account.
     * @return This returns the last updated time of a users account.
     */

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method sets when the last update occurred on a user account.
     * @param lastUpdate This is last update on a user account that is being set.
     */

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * This method gets who last updated a user account.
     * @return This is who last updated a user account that is being returned.
     */

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method sets who last updated a user account.
     * @param lastUpdatedBy This is who last updated a user account that is being set.
     */

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}
