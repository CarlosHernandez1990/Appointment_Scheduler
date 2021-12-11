Title: Software 2 Scheduling Application.

Purpose: The purpose of the application is to give users the ability to manage all aspects of appointments. Users can create, update, or remove appointments. Users are able to schedule appointments according to their local time zone. 

Author: Carlos Hernandez

Contact Information: 530-691-2050

Student Application Version: V1

Date: 10/7/2021

IDE: IntelliJ IDEA Community Eddition 2021.1.3

JDK: 11.0.12_windows-x64

SDK: JavaFX 11.0.2

Directions:

	Log in to the application by enterting your username and password and then pressing the log in button. Users names and password are case sensitive. When you first log in you will be directed to a main screen. A pop up will appear stating whether or not there is an appointment for you in the next 15 minutes. Press ok when this pop up appears to dismiss it. On the main screen there are two differents sections. Those sections are labeled "Customers" and "Appointments". 

	In the customers sections there is a tableview of all customer accounts. There are four buttons next to the table view labeled "Add Customer", "Update Customer", "Remove Customer", and "View Reports". 

	To add a customer click on the Add Customer button. This will take you to a form that will allow you to generate a new customer record. All fields must be filled out before pressing the save button. 

	To update a customer account, select a row must and then press the Update Customer button. A row must be selected to update a account. When you press this button you will be taken to a form which allows you to alter customer information. All fields must be filled out before pressing the save button.

	To remove a customer, a row must be selected before the Remove Customer button is pressed. If that customer has current appointments then an error message will be populated stating that all customer appointments must be removed before deletion. Once all criteria is met the customer account can be removed.

	To view reports press the View Reports button. You will be taken to the reports page.

	In appointments section there is a tableview display all appointments. This tableview has three labeled tabs that allow you to filter through appointments by current week, current month, or a view of them all. Underneath the tabled view there are three buttons. The buttons are labeled "New Appointments", "Update Appointments", and "Remove appointments".

	To add an appointment press the New Appointemnts button. This will take you to a form where information can be entered in to create an appointment. To make an appointment an appointment must be made between business hours, the date select has to be on a week day, the start time has to be after the current time, and the start time must be before the end time of the appointment. No field may be empty. After all information is entered press the save button.

	To update an appointment please select a row from the appointments tableview and then press the Update Appointments button. A row must be selected in order to proceed to the update form. To update an appointment an appointment must be in between business hours, the date selected has to be on a week day, the start time has to be after the current time, and the start time of the appointment must be before its end time. No field may be empty. After all information has been entered press the save button.

	To remove a appointment, a row must be selected in the appointment table view. After a row is selected press the remove button to delete that record.

Additional Report: The additional report I decided to do is an appointment count by Country and Division. This allows users to see which Counrties and Divisions are booking the most appointments. This info is display in a tableview in the view reports page.

MySQL Connector: Java-8.0.26






