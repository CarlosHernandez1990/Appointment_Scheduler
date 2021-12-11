package sample.Model;

/**
 * This class contains all the methods and variable used to compose the contacts class.
 */

public class Contacts {
    int contactID;
    String contactName;
    String email;

    /**
     * This is the constructor of contacts class.
     * @param contactID This is the contact's ID.
     * @param contactName This is the contact's name.
     * @param email This is the contact's email.
     */

    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * The method gets the contact's ID.
     * @return This is the contact's ID that is returned.
     */

    public int getContactID() {
        return contactID;
    }

    /**
     * The method sets a contact's ID.
     * @param contactID This the ID of the contact that is being set.
     */

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * This method gets the contact's name.
     * @return This returns that contact's name.
     */

    public String getContactName() {
        return contactName;
    }

    /**
     * This sets the contact's name.
     * @param contactName This is name of a contact that is being set.
     */

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * This method gets the email of the contact.
     * @return This is the email of the contact that is being returned.
     */

    public String getEmail() {
        return email;
    }

    /**
     * This method sets a Contacts email.
     * @param email This is the contact's email that is being set.
     */

    public void setEmail(String email) {
        this.email = email;
    }
}
