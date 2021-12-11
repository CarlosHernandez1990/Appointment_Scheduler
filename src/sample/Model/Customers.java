package sample.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class contains methods and variables that compose the customers class.
 */

public class Customers {
    int customer_ID;
    String customer_name;
    String address;
    String postal_Code;
    String phone;
    LocalDateTime createDate;
    String createdBy;
    Timestamp lastUpdate;
    String lastUpdatedBy;
    int divisionID;

    /**
     * This is the constructor of the customers class.
     * @param customer_ID This is the ID of a customer.
     * @param customer_name This is the name of a customer.
     * @param address This is the address of a customer.
     * @param postal_Code This is the postal code of a customer.
     * @param phone This is the phone number of a customer.
     * @param createDate This is when the customer record was created.
     * @param createdBy This who created the customer record.
     * @param lastUpdate This is when the customer record was last updated.
     * @param lastUpdatedBy This is who last updated the customer record.
     * @param divisionID This is the division where the customer resides in.
     */

    public Customers(int customer_ID, String customer_name, String address, String postal_Code, String phone, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) {
        this.customer_ID = customer_ID;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     * This method gets the customer's ID.
     * @return This returns the customers ID.
     */

    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * This method sets a customers ID.
     * @param customer_ID This is the customer ID that is being set.
     */

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /**
     * This method gets a customers name.
     * @return This returns a customers name.
     */

    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * This sets a customers name.
     * @param customer_name This is the customer name that is being set.
     */

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * This method gets the address of a customer record.
     * @return This will return the address of a customer.
     */

    public String getAddress() {
        return address;
    }

    /**
     * This method sets a customer's address.
     * @param address This is the address of a customer that is being set.
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method gets the postal code of a customer record.
     * @return This returns a postal code.
     */

    public String getPostal_Code() {
        return postal_Code;
    }

    /**
     * This method sets a customer's postal code.
     * @param postal_Code This is the postal code that is being set.
     */

    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    /**
     * This method gets a customer's phone number.
     * @return This returns a customer's phone number.
     */

    public String getPhone() {
        return phone;
    }

    /**
     * This method sets a customer's phone number.
     * @param phone This is the phone number of a customer's record that is being set.
     */

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method gets the created date of a customer's record.
     * @return This returns the created date of a customer's record.
     */

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * This method sets the date of creation of a customer's record.
     * @param createDate This is the created date of a customer's record that is being set.
     */

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * This method finds out who created a customer's record.
     * @return This returns who created the customer's record.
     */

    public String getCreatedBy() {
        return createdBy; }

    /**
     *This method sets who created a customer's record.
     * @param createdBy This is who is being set as the creator of customer's record.
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy; }

    /**
     * This method gets when the last update to a customer's record occurred.
     * @return This is when the last update to a customer's record occurred.
     */

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method sets when a customer's record was last updated.
     * @param lastUpdate This is a timestamp of when a customer's record was last updated by.
     */

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * This method gets who last updated a customer's record.
     * @return This who updated a customer's record last.
     */

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method sets who last updated a customer's record.
     * @param lastUpdatedBy This is who updated a customer's record last that is being set.
     */

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method gets a customer's division ID that they reside in.
     * @return This is the division ID of where a customer resides that is being returned.
     */

    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This method sets a division ID of customer record.
     * @param divisionID This is the division ID that is being set.
     */

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
