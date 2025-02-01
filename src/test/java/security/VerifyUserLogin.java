package security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;
public class VerifyUserLogin {
	
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	
	
	@Test
	public void testUserLogin() {
	    String username = "dbuser1";
	    String password = "12345";
	    try (Connection conn = DriverManager.getConnection(url, username, password)) {
	        Assert.assertNotNull(conn, "Login failed for user: " + username);
	    } catch (SQLException e) {
	        Assert.fail("Login failed for user: " + username + " - " + e.getMessage());
	    }
	}
	
}
