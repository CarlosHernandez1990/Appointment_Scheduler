package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseAccess.CountryDatabase;
import sample.DatabaseAccess.CustomerDatabase;
import sample.DatabaseAccess.DivisionsDatabase;
import sample.Model.Country;
import sample.Model.Customers;
import sample.Model.FirstLevelDivisions;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This class contains the methods that control the update customers page.
 */

public class UpdateCustomerController {
    @FXML
    TextField customerIdCol;
    @FXML
    TextField customerNameCol;
    @FXML
    TextField customerAddressCol;
    @FXML
    TextField customerPostalCol;
    @FXML
    TextField customerPhoneCol;
    @FXML
    ComboBox countryCombo;
    @FXML
    ComboBox divisionCombo;
    @FXML
    TextField name;
    @FXML
    TextField address;
    @FXML
    TextField postal;
    @FXML
    TextField phone;
    @FXML
    Button save;
    @FXML
    Button exit;
    private Customers rowTransfer;

    /**
     * This method initializes the update customers page.
     * @throws SQLException This will throw SQLExceptions.
     */

    public void initialize() throws SQLException {
        ObservableList countryName = FXCollections.observableArrayList();
        ObservableList<Country> country = CountryDatabase.getCountries();

        for (Country c : country) {
            String name = c.getCountryName();
            countryName.add(name);
        }

        countryCombo.getItems().addAll(countryName);
    }

    /**
     * This method transfers customers information from the main controller.
     * This method transfers customer information from the selected row on the previous page. The information that is
     * transferred to this method auto-populates in the text fields and combo boxes on the form.
     * @param rowTransfer This variable carries information from the selected row on the previous page.
     */

    public void rowTransfers(Customers rowTransfer){
    this.rowTransfer = rowTransfer;
    customerIdCol.setText(String.valueOf(rowTransfer.getCustomer_ID()));
    customerNameCol.setText(rowTransfer.getCustomer_name());
    customerAddressCol.setText(rowTransfer.getAddress());
    customerPostalCol.setText(rowTransfer.getPostal_Code());
    customerPhoneCol.setText(rowTransfer.getPhone());
    int divisionID = rowTransfer.getDivisionID();
    String countryName = DivisionsDatabase.getCountryName(divisionID);
    divisionCombo.setValue(divisionID);
    countryCombo.setValue(countryName);
    }

    /**This method controls the actions of all combo boxes on the page.
     * This method filters the first level divisions combo box by what is selected in the country name combo box.
     * @param actionEvent This is an action event handler.
     * @throws SQLException This will throw SQLException.
     */

    public void countryHandler(ActionEvent actionEvent) throws SQLException {
        ObservableList divisionsList = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivisions> divisions = DivisionsDatabase.getFirstLevelDivisions();
        int choice = countryCombo.getSelectionModel().getSelectedIndex();
        divisionCombo.getItems().clear();
        if(choice == 0){
            for(FirstLevelDivisions first : divisions){
                if(first.getCountryID() == 1){
                    String division = first.getDivision();
                    divisionsList.add(division);
                    divisionCombo.setItems(divisionsList);
                }
            }
        }

        else if(choice == 1){
            for(FirstLevelDivisions first : divisions){
                if(first.getCountryID() == 2){
                    String division = first.getDivision();
                    divisionCombo.getItems().clear();
                    divisionsList.add(division);
                    divisionCombo.getItems().addAll(divisionsList);
                }
            }
        }

        else {
            for(FirstLevelDivisions first : divisions){
                if(first.getCountryID() == 3){
                    String division = first.getDivision();
                    divisionCombo.getItems().clear();
                    divisionsList.add(division);
                    divisionCombo.getItems().addAll(divisionsList);
                }
            }
        }
    }

    /**
     * This method is in charge of adding customers to the database.
     * This method uses text fields and combo boxes to acquire user information needed to create customer records.
     * @param actionEvent This method is an action event handler that is initiated by clicking the save button.
     * @throws IOException This will throw IOExceptions.
     * @throws SQLException This will throw SQLExceptions.
     */

    public void addHandler(ActionEvent actionEvent) throws IOException, SQLException {
        try {
            int choice = countryCombo.getSelectionModel().getSelectedIndex();
            int divisionID = 0;

            if (choice == 0) {
                divisionID = 1;
            }

            else if (choice == 1) {
                divisionID = 2;
            }

            else {
                divisionID = 3;
            }

            for (FirstLevelDivisions first : DivisionsDatabase.getFirstLevelDivisions()) {
                Object divisionName = first.getDivision();
                Object divisionChoice = divisionCombo.getSelectionModel().getSelectedItem();
                if (divisionChoice.equals(divisionName)) {
                    divisionID = first.getDivisionId();
                }
            }

            int customerId = rowTransfer.getCustomer_ID();
            String Name = customerNameCol.getText();
            String Address = customerAddressCol.getText();
            String Postal = customerPostalCol.getText();
            String Phone = customerPhoneCol.getText();

            if (Name.isEmpty() || Address.isEmpty() || Postal.isEmpty() || Phone.isEmpty()) {
                throw new  RuntimeException("Please enter a valid response for all text fields and drop down selection lists.");
            }

            else{
                CustomerDatabase.updateCustomers(Name, Address, Postal, Phone, divisionID, customerId);
            }

            Parent child = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
            Scene scene = new Scene(child);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.centerOnScreen();
            window.show();
        }

        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid response for all text fields and drop down selection lists.");
            alert.show();
        }

        catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid response for all text fields and drop down selection lists.");
            alert.show();
        }
    }

    /**
     * This method initiates a scene change when users press the exit button.
     * This method is an action event method handler triggered by the users press the exit button.
     * @param actionEvent This is an action event handler.
     * @throws IOException This throws IOExceptions.
     */

    public void exitHandler(ActionEvent actionEvent) throws IOException {
        Parent child = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
        Scene scene = new Scene(child);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
        window.show();

    }
}


