package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DatabaseAccess.*;
import sample.Model.*;
import java.io.IOException;
import java.sql.SQLException;


/**
 * This class contains all the info and methods needed to view reports.
 */

public class ViewReportsController {
    @FXML
    TableColumn<AppointmentReport, String> type;
    @FXML
    TableView<AppointmentReport> monthTable;
    @FXML
    TableColumn<AppointmentReport, String> monthCol;
    @FXML
    TableColumn<AppointmentReport, Integer> monthCountCol;
    @FXML
    ComboBox contactName;
    @FXML
    TableView scheduleTable;
    @FXML
    TableColumn aIdCol;
    @FXML
    TableColumn aTitleCol;
    @FXML
    TableColumn aTypeCol;
    @FXML
    TableColumn aDescriptionCol;
    @FXML
    TableColumn aStartCol;
    @FXML
    TableColumn aEndCol;
    @FXML
    TableColumn aCustomerIdCol;
    @FXML
    TableColumn aContactIdCol;
    @FXML
    TableView locationTable;
    @FXML
    TableColumn countCol;
    @FXML
    TableColumn divisionCol;
    @FXML
    TableColumn locationCol;
    @FXML
    Button mainButton;
    @FXML
    Button exitButton;

    /**
     * This method initializes the view reports screen.
     * @throws SQLException
     */

    public void initialize() throws SQLException {
        ObservableList<Contacts> names = ContactDatabase.getContacts();
        ObservableList contactOptions = FXCollections.observableArrayList();

        for (Contacts contacts : names) {
            String nameOption = contacts.getContactName();
            contactOptions.add(nameOption);
        }

        contactName.getItems().addAll(contactOptions);
        ObservableList monthReport = AppointmentReportDatabase.appointmentReportByMonth();
        monthTable.setItems(monthReport);
        monthCountCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        type.setCellValueFactory(new PropertyValueFactory<>("types"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        ObservableList locationReport = AppointmentReportDatabase.appointmentReportByLocation();
        locationTable.setItems(locationReport);
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("types"));
    }

    /**
     * This method handles populating the contact schedule tableview.
     * The method returns the selected contact's appointment schedule. The contact is selected from the drop down combo
     * box. Once a contact is selected the tableview automatically populates that contact's appointment schedule.
     * @throws SQLException
     */

    public void contactHandler() throws SQLException {
        int contactID = 0;

        for (Contacts contacts : ContactDatabase.getContacts()) {
            String name = String.valueOf(contactName.getSelectionModel().getSelectedItem());
            if (contacts.getContactName().equalsIgnoreCase(name)) {
                contactID = contacts.getContactID();
            }

            scheduleTable.setItems(AppointmentDatabase.getAppointmentSchedules(contactID));
            aIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            aDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            aContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            aCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        }
    }

    /**
     * This method handles what action occurs when the main button is pressed.
     * This method returns users to the main screen of the application.
     * @param actionEvent This action event is controlled by the main button.
     * @throws IOException This will throw IOExceptions.
     */

    public void mainHandler(ActionEvent actionEvent) throws IOException {
        Parent child = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
        Scene scene = new Scene(child);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
        window.show();
    }

    /**
     * This method handles what action occurs when the exit button is pressed.
     * This method closes out of the application.
     * @param actionEvent This action event is controlled by the exit button.
     * @throws IOException This will throw IOExceptions.
     */

    public void exitHandler(ActionEvent actionEvent) throws IOException {
        Parent child = FXMLLoader.load(getClass().getResource("/sample/View/Main.fxml"));
        Scene scene = new Scene(child);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
        window.close();
    }
}





