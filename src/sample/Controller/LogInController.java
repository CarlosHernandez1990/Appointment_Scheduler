package sample.Controller;

import com.sun.jdi.Value;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DatabaseAccess.AppointmentDatabase;
import sample.DatabaseAccess.CustomerDatabase;
import sample.DatabaseAccess.UserDatabase;
import sample.Model.Appointments;
import sample.Model.Customers;
import sample.Model.Users;

import java.io.*;
import java.security.Key;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class contains the methods that control the Log In page.
 */

public class LogInController {

    @FXML
    TextField userNameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label userNameLabel;
    @FXML
    Label passwordLabel;
    @FXML
    Label locationLabel;
    @FXML
    Button logInButton;
    @FXML
    Button closeButton;
    @FXML
    Button cancelButton;

    /**
     * This method initializes the Log In screen.
     * This method controls whether the page will be set in French or in English.
     * @throws SQLException
     */

    public void initialize() throws SQLException {
        ZoneId zone = ZoneId.of(String.valueOf(ZoneId.systemDefault()));
        locationLabel.setText(String.valueOf(zone));
        ResourceBundle rb = ResourceBundle.getBundle("sample/Model/Tal", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {
            userNameLabel.setText(rb.getString("User-Name"));
            passwordLabel.setText(rb.getString("Password"));
            logInButton.setText(rb.getString("Log-In"));
            cancelButton.setText(rb.getString("Cancel"));
        }
    }

    /**
     * This method controls what happens when the log in button is pressed.
     * This method obtains the user name and password entered and compares it to what is in the database. If the password
     * and user name do not match what is in the database then an error message will appear. If a match is found then the scene
     * changes to the main screen.
     * @throws IOException This will throw IOExceptions.
     * @throws SQLException This will throw SQLExceptions.
     */

    public void logInHandler() throws IOException, SQLException {
        String userName = userNameField.getText();
        boolean logInSuccessful = false;
        String filename = "login_activity.txt";
        File file = new File(filename);
        BufferedWriter logWriter = new BufferedWriter(new FileWriter(file , true));
        String logInSuccess = "User " + userNameField.getText() + ": Successful Log In on " + LocalDate.now() + " at " + Timestamp.valueOf(LocalDateTime.now()) + ".";
        String logInUnSuccessful = "User " + userNameField.getText() + ": Log In unsuccessful on " + LocalDate.now() + " at " + Timestamp.valueOf(LocalDateTime.now()) + ".";


        if (passwordField.getText().equals(UserDatabase.getPassword(userName))) {
            logInSuccessful = true;
            logWriter.newLine();
           logWriter.write(logInSuccess);
           logWriter.close();
        }

        if (!logInSuccessful) {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("sample/Model/Tal", Locale.getDefault());
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText(rb.getString("Unsuccessful"));
                alert1.setTitle(rb.getString("Log-In"));
                alert1.setHeaderText(rb.getString("Message"));
                alert1.show();
                logWriter.newLine();
                logWriter.write(logInUnSuccessful);
                logWriter.close();
            }

            else
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Log In");
                alert1.setContentText("Log in was unsuccessful. Please enter correct User Name and Password combination.");
                alert1.show();
                logWriter.newLine();
                logWriter.write(logInUnSuccessful);
                logWriter.close();
            }
            return;
        }
        int userID = UserDatabase.getUserID(userName);
        ObservableList<Appointments> appointments = AppointmentDatabase.getAppointments(userID);
        boolean upcomingAppointment = false;
        for (Appointments alert : appointments) {
            LocalDateTime appointmentTime = alert.getStart();
            ZonedDateTime zdt = ZonedDateTime.of(appointmentTime, ZoneId.systemDefault());

            int aptId = alert.getAppointmentID();
            if ((LocalDateTime.now().plusMinutes(15).isAfter(zdt.toLocalDateTime()))&&(zdt.toLocalDateTime().isAfter(LocalDateTime.now()))) {
                Alert timeAlert = new Alert(Alert.AlertType.INFORMATION);
                timeAlert.setTitle("Appointment Alert");
                timeAlert.setContentText("Appointment " + aptId + " at " + appointmentTime + " will be happening soon.");
                timeAlert.show();
                upcomingAppointment = true;

            }
        }
        if(!upcomingAppointment) {
            Alert timeAlert = new Alert(Alert.AlertType.INFORMATION);
            timeAlert.setTitle("Appointment Alert");
            timeAlert.setContentText("No upcoming Appointment within 15 minutes");
            timeAlert.show();
        }

        if (logInSuccessful) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/View/Main.fxml"));
            Parent root = fxmlLoader.load();
            Stage window = (Stage) logInButton.getScene().getWindow();
            window.setScene(new Scene(root));
            window.centerOnScreen();
            window.show();
        }
    }
}
