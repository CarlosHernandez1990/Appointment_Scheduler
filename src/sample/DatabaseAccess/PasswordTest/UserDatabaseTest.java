package sample.DatabaseAccess.PasswordTest;

import org.junit.jupiter.api.Test;
import sample.DatabaseAccess.UserDatabase;
import sample.Model.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserDatabaseTest {

    @Test
    void getPassword() {
        assertEquals("test", UserDatabase.getPassword("test"));
    }
}