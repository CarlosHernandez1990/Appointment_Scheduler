package sample.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


    public class DatabaseConnection {
        private static final String databaseName = "client_schedule";
        private static final String DB_URL = "jdbc:mysql://localhost/" + databaseName + "?connectionTimeZone=SERVER";
        private static final String username = "sqlUser";
        private static final String password = "Passw0rd!";
        private static final String driver = "com.mysql.cj.jdbc.Driver";
        public static Connection connectionLink = null;




        public static Connection makeConnection() {
            try {
                Class.forName(driver);
                connectionLink = DriverManager.getConnection(DB_URL, username, password);
                System.out.println("Connection Successful");
            }
            catch (SQLException e) {
                System.out.println("Connection error");
            }
            catch (ClassNotFoundException e){
                System.out.println("Class not found");
            }
            return connectionLink;
        }

        public static void closeConnection() throws ClassNotFoundException, SQLException, Exception {
            connectionLink.close();
        }

    }

