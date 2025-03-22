package org.example;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void testGetConnection_Fail() {
        try {
            Connection connection = Database_Connection.getConnection();
            assertNotNull(connection, "Connection should establish whenever the mySQL driver is connected and installed properly");
        } catch (SQLException e) {
            List<String> expectedMessages = List.of("MySQL Driver not found");
            List<String> actualMessages = List.of(e.getMessage());

            assertLinesMatch(expectedMessages, actualMessages, "ERROR ERROR ERROR 'MySQL Driver not found'.");
            fail("Expected failure: " + e.getMessage());
        }
    }
}


