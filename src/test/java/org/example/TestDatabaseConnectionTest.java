package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TestDatabaseConnectionTest {

    @Test
    void testMain() {
        WebDriverManager.chromedriver().setup();
        String dbUrl = "jdbc:mysql://localhost:3306/EmployeeDB";
        String user = "root";
        String password = "######";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, user, password);

            assertNotNull(conn, "Connection should not be null.");
            assertInstanceOf(Connection.class, conn, "The object should be an instance of Connection.");

            conn.close();
        } catch (ClassNotFoundException e) {
            fail("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }

}