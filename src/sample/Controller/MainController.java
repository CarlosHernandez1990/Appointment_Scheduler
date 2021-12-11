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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DatabaseAccess.AppointmentDatabase;
import sample.DatabaseAccess.CustomerDatabase;
import sample.Model.Appointments;
import sample.Model.Customers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contains the methods that control the main page.
 */

public class MainController {
    @FXML
    TableView<Customers> customerTable;
    @FXML
    TableColumn<Customers, Integer> cidCol;
    @FXML
    TableColumn<Customers, String> cnameCol;
    @FXML
    TableColumn<Customers, String> caddressCol;
    @FXML
    TableColumn<Customers, String> czipCol;
    @FXML
    TableColumn<Customers, String> cphoneCol;
    @FXML
    TableColumn<Customers, Integer> cdivisionCol;
    @FXML
    TableColumn<Customers, Integer> createDateCol;
    @FXML
    TableView<Appointments> appointmentTable;
    @FXML
    TableColumn<Appointments, Integer> aIdCol;
    @FXML
    TableColumn<Appointments, String> aTitleCol;
    @FXML
    TableColumn<Appointments, String> aDescriptionCol;
    @FXML
    TableColumn<Appointments, String> aLocationCol;
    @FXML
    TableColumn<Appointments, String> aTypeCol;
    @FXML
    TableColumn<Appointments, LocalDateTime> aStartCol;
    @FXML
    TableColumn<Appointments, LocalDateTime> aEndCol;
    @FXML
    TableColumn<Appointments, Integer> aCustomerIdCol;
    @FXML
    TableColumn<Appointments, String> aUserIdCol;
    @FXML
    TableColumn<Appointments, String> aContactIdCol;
    @FXML
    TabPane tabPane;
    @FXML
    Tab mainTab;
    @FXML
    Tab monthTab;
    @FXML
    Tab weekTab;
    @FXML
    Button add;
    @FXML
    Button delete;
    @FXML
    Button update;
    @FXML
    Button removeAppointment;
    @FXML
    Button updateAppointment;
    @FXML
    Button newAppointments;
    @FXML
    Button viewReportsButton;
    @FXML
    AnchorPane mainPage;
    @FXML
    TextField searchField;


    /**
     * This method initializes the main page.
     * This method initializes the main page by populating information in the appointments and customer tableviews.
     * @throws SQLException
     */

    public void initialize() throws SQLException {
        ObservableList<Customers> customersList = CustomerDatabase.getCustomers();
        customerTable.setItems(customersList);
        cidCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        cnameCol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        caddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        czipCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
        cphoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cdivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));

        ObservableList<Appointments> appointmentsList = AppointmentDatabase.getAppointments();
        appointmentTable.setItems(appointmentsList);
        aIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        aDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        aLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        aContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        aUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /**
     * This method is controlled by the action event of pressing the Add Customer button.
     * This method changes the scene from the Main screen to the Add Customer form.
     * @param actionEvent This action event is handled by the add customers button.
     * @throws IOException This will throw IOExceptions.
     */

    public void addHandler(ActionEvent actionEvent) throws IOException {
        Parent child = FXMLLoader.load(getClass().getResource("/sample/View/NewCustomer.fxml"));
        Scene scene = new Scene(child);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
        window.show();
    }

    /**
     * This method is handled by the action event of pressing the Remove Customer button.
     * This method is used to remove customers from the database by selecting a row and pressing the Remove Customer
     * button.
     * @param actionEvent This action event is initiated by pressing the Remove Customers Button.
     * @throws SQLException This will throw SQLExceptions
     */

    public void deleteHandler(ActionEvent actionEvent) throws SQLException {
        try {
            Customers cust = customerTable.getSelectionModel().getSelectedItem();
            int custId = cust.getCustomer_ID();
            String customer = customerTable.getSelectionModel().getSelectedItem().toString();
            ObservableList<Appointments> allAppointments = AppointmentDatabase.getAppointments();
            boolean customerHasAppointment = false;
            for(Appointments appointments : allAppointments){
                int testId = appointments.getCustomerID();
                if (testId == custId)
                {
                    customerHasAppointment = true;
                    break;
                }
            }

            if(!customerHasAppointment) {
                if (customer != "[]") {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Deletion Confirmation");
                    alert.setContentText("Are you sure you want to proceed in deleting customer record.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        CustomerDatabase.deleteCustomer(custId);
                        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
                        alert.setContentText(cust.getCustomer_name() + " has been successfully deleted.");
                        alert.show();
                    }
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please delete all appointments related to this customer in order proceed with deletion.");
                alert.show();
            }

            ObservableList<Customers> customersList = CustomerDatabase.getCustomers();
            customerTable.setItems(customersList);
            cidCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
            cnameCol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
            caddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            czipCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            cphoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            cdivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
            createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        }

        catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please delete all appointments related to this customer in order proceed with deletion.");
            alert.show();
        }

        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row in order to proceed.");
            alert.show();
        }
    }

    /**
     * This method is an action event that controls what occurs when the Update Customer button is pressed.
     * This method allows users to update a customer's account by selecting a row and pressing the Update Customer Button
     * @param actionEvent This action event is initiated by pressing the Update Customer button.
     * @throws IOException This will throw IOExceptions.
     */

    public void updateHandler(ActionEvent actionEvent) throws IOException {
        try {
            Customers customerRow = customerTable.getSelectionModel().getSelectedItem();
            String customer = customerTable.getSelectionModel().getSelectedItem().toString();

            if (customer != "[]") {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/View/UpdateCustomer.fxml"));
                Parent root = fxmlLoader.load();
                Stage window = (Stage) update.getScene().getWindow();
                window.setScene(new Scene(root));
                window.centerOnScreen();
                window.show();
                UpdateCustomerController rowTransfer = fxmlLoader.getController();
                rowTransfer.rowTransfers(customerRow);
            }
        }

        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row in order to proceed");
            alert.show();
        }
    }

    /**
     * This method handles what occurs when the New Appointments button is pressed.
     * This method is an action event handler that changes scene to the New Appointment form when the New Appointments
     * button is pressed.
     * @param actionEvent This action event is initiated by pressing the New Appointments Button.
     * @throws IOException This will throw IOExceptions.
     */

    public void newAppointmentHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/View/NewAppointment.fxml"));
        Parent root = fxmlLoader.load();
        Stage window = (Stage) update.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.show();
    }

    /**
     * This method handles what happens when the Remove Appointments button is pressed.
     * This method deletes appointments from the database when a row is selected in the appointments table and the Remove
     * Appointments button is pressed.
     * @throws SQLException This will throw SQLExceptions.
     */

    public void deleteAppointmentsHandler() throws SQLException {
        try {
            Appointments rowSelected = appointmentTable.getSelectionModel().getSelectedItem();
            String row = appointmentTable.getSelectionModel().getSelectedItem().toString();

            if (row != "[]") {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deletion Confirmation");
                alert.setContentText("Are you sure you want to delete this Appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    int appointmentId = rowSelected.getAppointmentID();
                    String type = appointmentTable.getSelectionModel().getSelectedItem().getType();
                    Alert deletion = new Alert(Alert.AlertType.INFORMATION);
                    deletion.setTitle("Deletion Successful");
                    deletion.setContentText("Appointment " + appointmentId + " for " + type + " has been successfully deleted.");
                    deletion.show();
                    AppointmentDatabase.deleteAppointment(appointmentId);

                    ObservableList<Appointments> appointmentsList = AppointmentDatabase.getAppointments();
                    appointmentTable.setItems(appointmentsList);
                    aIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                    aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                    aDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                    aLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
                    aContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
                    aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                    aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
                    aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
                    aCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
                    aUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
                }
            }
        }

        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row in order to proceed");
            alert.show();
        }

        javafx.scene.control.SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        tabPane.getSelectionModel().select(mainTab);
    }

    /**
     * This method controls what happens when the Update Appointments button is pressed.
     * This method transfers info from the row selected in the appointments table by pressing the update appointment button.
     * This info is transferred to the Update Appointment form when the scene is changed.
     * @throws IOException This will throw IOExceptions.
     */

    public void updateAppointmentsHandler() throws IOException {
        try {
            String customer = appointmentTable.getSelectionModel().getSelectedItem().toString();
            if (customer != "[]") {
                Appointments rowSelected = appointmentTable.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/View/UpdateAppointment.fxml"));
                Parent root = fxmlLoader.load();
                Stage window = (Stage) update.getScene().getWindow();
                window.setScene(new Scene(root));
                window.centerOnScreen();
                window.show();
                UpdateAppointmentController rowTransfer = fxmlLoader.getController();
                rowTransfer.rowTransfers(rowSelected);
            }
        }

        catch (NullPointerException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a valid row in order to proceed");
            alert.show();
        }
    }

    /**
     * This method controls what happens when the month tab is selected above the appointment table.
     * This method filters the info in the appointment table by organizing appointments that will occur in the current
     * month.
     * <b> A lambda expression was used to create the list labeled "monthList1". This expression reduces how many
     * lines of code are needed and allows for a easier way to filter through a list. This list filters through a list
     * by year. This list adds each appointment that is set to happen in the current year and places them in an observable list.</b>
     * <b> The second lambda expression labeled "monthList2" is used to filter through the results of the first list
     * by looking for appointments that are in the current month. </b>
     * @throws SQLException This will throw SQLExceptions.
     */

    public void monthHandler() throws SQLException {
        try {
            ObservableList<Appointments> appointmentsList = AppointmentDatabase.getAppointments();
            ObservableList<Appointments> appointment = FXCollections.observableArrayList();
            List<Appointments> monthList1 = appointmentsList.stream()
                    .filter(appointments -> appointments.getStart().getYear() == LocalDateTime.now().getYear())
                    .collect(Collectors.toList());
            List<Appointments> monthList2 = monthList1.stream().filter(appointments -> appointments.getStart().getMonth() == LocalDateTime.now().getMonth())
                    .collect(Collectors.toList());
            appointment.addAll(monthList2);
            appointmentTable.setItems(appointment);
            aIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            aDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            aLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            aContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            aCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            aUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        }

        catch (NullPointerException e) {}
    }

    /**
     * This method controls what happens when the week tab is selected that is located above the appointment table.
     * This method filters the info in the appointment table by organizing appointments that will occur in the current
     * week.
     * <b> A lambda expression was used to create the list labeled "appointmentList1". This expression reduces how many
     * lines of code are needed and allows for a easier way to filter through a list. This list filters through a list
     * by year. This list adds each appointment that is set to happen in the current year and places them in an observable list.</b>
     * <b> The second lambda expression labeled "appointmentList2" is used to filter through the results of the first list
     * by looking for appointments that are in the current week. </b>
     * @throws SQLException This will throw SQLExceptions.
     */

    public void weekHandler() throws SQLException {
        ObservableList<Appointments> appointmentsList = AppointmentDatabase.getAppointments();
        ObservableList appointment = FXCollections.observableArrayList();
        TemporalField week = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        List<Appointments> appointmentsList1 = appointmentsList.stream().filter(appointments -> appointments.getStart().getYear()== LocalDateTime.now().getYear()).collect(Collectors.toList());
        List<Appointments> appointmentsList2 = appointmentsList1.stream().filter(appointments -> appointments.getStart().get(week) == LocalDateTime.now().get(week))
                .collect(Collectors.toList());
        appointment.addAll(appointmentsList2);
        try {
            appointmentTable.setItems(appointment);
            aIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            aDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            aLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            aContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            aCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            aUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        }

        catch (NullPointerException e) {}
    }

    /**
     * This method controls what happens when the All tab is selected that is located above the appointments table.
     * This method shows all appointments in the database in the appointment tableview.
     * @throws SQLException This will throw SQLExceptions.
     */

    public void mainTabHandler() throws SQLException {
        try {
            ObservableList<Appointments> appointmentsList = AppointmentDatabase.getAppointments();
            appointmentTable.setItems(appointmentsList);
            aIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            aDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            aLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            aContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            aCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            aUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        }

        catch (NullPointerException e) {}
    }

    /**
     * This method controls what happens when the View Reports button is pressed.
     * This method changes the current scene to the View Reports page when the View Reports button is pressed.
     * @throws IOException This will throw IOExceptions.
     */

    public void viewReportsHandler() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/View/ViewReports.fxml"));
        Parent root = fxmlLoader.load();
        Stage window = (Stage) viewReportsButton.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.show();
    }

    public void searchHandler() throws SQLException {
        ObservableList<Customers> customers = CustomerDatabase.getCustomers();
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        String name = searchField.getText();
        String numName = searchField.getText();
        boolean exist = false;
        if(!name.isEmpty()){
            for(Customers c : customers){
                if(String.valueOf(c.getCustomer_ID()).contains(numName)){
                    customersList.add(c);
                }
                else if(c.getCustomer_name().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                {
                    customersList.add(c);
                }
                else {
                    exist = true;
                }
            }
        }

        if(name.isEmpty()){
            customerTable.setItems(customers);
            cidCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
            cnameCol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
            caddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            czipCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            cphoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            cdivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
            createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        }
        else if (exist)
        {
            customerTable.setItems(customersList);
            cidCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
            cnameCol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
            caddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            czipCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            cphoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            cdivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
            createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        }
        else
        {
            customerTable.setItems(null);

        }

    }
}



