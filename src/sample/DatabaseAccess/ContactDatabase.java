package sample.DatabaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Contacts;
import sample.helper.DatabaseConnection;

import java.lang.ref.PhantomReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains methods that relate to contacts in the sql database.
 */

public class ContactDatabase {

    /**
     * This method acquires all contacts.
     * This method returns all contacts in a observable list.
     * @return This returns an observable contacts list.
     */

    public static ObservableList<Contacts> getContacts(){
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = DatabaseConnection.makeConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts contacts = new Contacts(contactID, contactName, email);
                contactsList.add(contacts);
            }
        }

        catch (SQLException e){}

        return contactsList;
    }
}
