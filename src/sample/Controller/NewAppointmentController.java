package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DatabaseAccess.AppointmentDatabase;
import sample.DatabaseAccess.ContactDatabase;
import sample.DatabaseAccess.CustomerDatabase;
import sample.DatabaseAccess.UserDatabase;
import sample.Model.Appointments;
import sample.Model.Contacts;
import sample.Model.Customers;
import sample.Model.Users;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;

/**
 * This class contains all methods that are used to manage new appointments.
 */
public class NewAppointmentController {

    @FXML
    TextField appointmentID;
    @FXML
    TextField title;
    @FXML
    TextField description;
    @FXML
    TextField local;
    @FXML
    TextField type;
    @FXML
    TextField contactID;
    @FXML
    ComboBox customersName;
    @FXML
    TextField customerID;
    @FXML
    ComboBox userID;
    @FXML
    ComboBox contactName;
    @FXML
    ComboBox startComboH;
    @FXML
    ComboBox startComboM;

    @FXML
    ComboBox endComboH;
    @FXML
    ComboBox endComboM;

    @FXML
    DatePicker startTimeDate;

    @FXML
    Button save;
    @FXML
    Button exit;

    /**
     * This method initializes the new appointments controller.
     * @throws SQLException
     */

    public void initialize() throws SQLException {
        String hourBank[] = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
        String minBank[] = {":00",":15",":30",":45"};
        String sec = ":00";
        startComboH.getItems().addAll(hourBank);
        startComboM.getItems().addAll(minBank);
        endComboH.getItems().addAll(hourBank);
        endComboM.getItems().addAll(minBank);
        ObservableList<Contacts> names = ContactDatabase.getContacts();
        ObservableList contactOptions = FXCollections.observableArrayList();
        for (Contacts contacts : names){
            String nameOption = contacts.getContactName();
            contactOptions.add(nameOption);
        }

        contactName.getItems().addAll(contactOptions);
        ObservableList<Customers> cNames = CustomerDatabase.getCustomers();
        ObservableList customers = FXCollections.observableArrayList();
        for (Customers customer : cNames) {
            String cNameOption = customer.getCustomer_name();
            customers.add(cNameOption);
        }

        customersName.getItems().addAll(customers);
        ObservableList<Users> users = UserDatabase.getUsers();
        ObservableList ids = FXCollections.observableArrayList();
        for (Users user : users) {
            int id = user.getUserID();
            ids.add(id);
        }

        userID.getItems().addAll(ids);
    }

    /**
     * This method handles actions related to the contacts combo box.
     * This method controls what happens when a user selects an option from the combo box. The contact id text field is filled
     * with a value when users select a name from the contacts combo box.
     */

    public void contactHandler(){
        for (Contacts contacts : ContactDatabase.getContacts()){
            String name = String.valueOf(contactName.getSelectionModel().getSelectedItem());
            if (contacts.getContactName().equalsIgnoreCase(name)){
                int id = contacts.getContactID();
                contactID.setText(String.valueOf(id));
            }
        }
    }

    /**
     * This method handles actions related to the customer combo box.
     * This method populates the customer ID in a text field that corresponds to the customer name that is selected from the
     * combo box.
     */

    public void customerHandler() throws SQLException {
        for (Customers customers : CustomerDatabase.getCustomers()){
            String customer = String.valueOf(customersName.getSelectionModel().getSelectedItem());
            if (customer.equalsIgnoreCase(customers.getCustomer_name())){
                int id = customers.getCustomer_ID();
                customerID.setText(String.valueOf(id));
            }
        }
    }

    /**
     * This method handles what happens when the save button is pressed.
     * This method collects user submitted info from the text fields and combo boxes to create a new appointment. This
     * method contains validation checks to maintain the integrity of appointments made.
     * @throws SQLException This will throw SQLExceptions.
     * @throws IOException This will throw IOExceptions.
     */

    public void saveHandler() throws SQLException, IOException {
        try {
            String titles = title.getText();
            String descriptions = description.getText();
            String locations = local.getText();
            String types = type.getText();
            int contactId = Integer.parseInt(contactID.getText());
            int customerIDs = Integer.parseInt(customerID.getText());
            int userIDs = (int) userID.getSelectionModel().getSelectedItem();
            String createdBy = UserDatabase.getUserName(userIDs);
            String lastUpdatedBy = UserDatabase.getUserName(userIDs);
            DateTimeFormatter dateConversion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String hour = String.valueOf(startComboH.getSelectionModel().getSelectedItem());
            hour = " " + hour;
            String minute = String.valueOf(startComboM.getSelectionModel().getSelectedItem());
            String start = (hour + minute);
            String startDate = String.valueOf(startTimeDate.getValue()) + start;
            LocalDateTime starts = LocalDateTime.parse(startDate, dateConversion);
            String hours = String.valueOf(endComboH.getSelectionModel().getSelectedItem());
            hours = " " + hours;
            String minutes = String.valueOf(endComboM.getSelectionModel().getSelectedItem());
            String end = (hours + minutes);
            String endDate = String.valueOf(startTimeDate.getValue()) + end;
            LocalDateTime ends = LocalDateTime.parse(endDate, dateConversion);

            LocalTime opening = LocalTime.of(8, 0, 0);
            LocalDateTime businessOpen = LocalDateTime.of(startTimeDate.getValue(), opening);
            ZonedDateTime utcOpen = businessOpen.atZone(ZoneId.of("US/Eastern"));
            ZonedDateTime etcOpen = utcOpen.withZoneSameInstant(ZoneId.systemDefault());
            LocalTime closing = LocalTime.of(22, 0, 1);
            LocalDateTime businessClose = LocalDateTime.of(startTimeDate.getValue(), closing);
            ZonedDateTime utcClose = businessClose.atZone(ZoneId.of("US/Eastern"));
            ZonedDateTime etcClose = utcClose.withZoneSameInstant(ZoneId.systemDefault());
            String overlap = "no";

            if (starts.getDayOfWeek().equals(DayOfWeek.SATURDAY) || starts.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a week day to book an appointment.");
                alert.setTitle("Error");
                alert.show();
            }

            else if ((starts.plusSeconds(1).isAfter(ChronoLocalDateTime.from(etcOpen))) && (ends.minusSeconds(1).isBefore(ChronoLocalDateTime.from(etcClose)))) {
                if (ends.minusSeconds(1).isAfter(starts)) {
                    if (starts.plusSeconds(1).isAfter(LocalDateTime.now())) {
                    ObservableList<Appointments> allAppointment = AppointmentDatabase.getAppointments();
                    LocalDateTime first = starts;
                    LocalDateTime last = ends;

                    for (Appointments index : allAppointment) {
                        if (first.isAfter(index.getEnd()) || (last.isBefore(index.getStart()))) {
                            overlap = "no";
                        }

                        else {
                            overlap = "yes";
                        }
                    }

                        if (overlap.equals("no")) {
                            if(types.isEmpty() || locations.isEmpty() || titles.isEmpty() || descriptions.isEmpty()){
                                throw new RuntimeException("Please make sure every field has been filled out.");
                            }
                            else {
                                AppointmentDatabase.addAppointment(titles, descriptions, locations, types, contactId, starts, ends, customerIDs, userIDs, createdBy, lastUpdatedBy);
                            }
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/View/Main.fxml"));
                            Parent root = fxmlLoader.load();
                            Stage window = (Stage) save.getScene().getWindow();
                            window.setScene(new Scene(root));
                            window.centerOnScreen();
                            window.show();
                        }

                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Scheduling conflict");
                            alert.setContentText("Appointments for the same customer can not overlap");
                            alert.show();
                        }
                    }

                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please make sure that the appointment time being set is after the current time");
                        alert.setTitle("Error");
                        alert.show();
                    }
                }

                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please make sure the start is before the end time of the appointment.");
                    alert.setTitle("Error");
                    alert.show();
                }
            }

            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make appointment between the business hours of " + etcOpen.toLocalTime().toString()
                        + "-" + etcClose.toLocalTime().toString() + ".");
                alert.setTitle("Error");
                alert.show();
            }
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Make sure to enter correct information in each field");
            alert.setTitle("Error");
            alert.show();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please make sure every field has been filled out.");
            alert.setTitle("Error");
            alert.show();
        }
    }

    /**
     * This method controls what happens when the exit button is pressed.
     * This method will change scenes to return to the main screen.
     * @param actionEvent This is an action event triggered by pressing the exit button.
     * @throws IOException This will throw IOExceptions
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
