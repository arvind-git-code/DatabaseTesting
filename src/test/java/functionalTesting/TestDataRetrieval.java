package functionalTesting;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDataRetrieval {
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	@Test
	public void testDataRetrieval() {
	    String sql = "SELECT * FROM employees WHERE first_name = 'Lex'";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        Assert.assertTrue(rs.next(), "No data found.");
	        // Verify retrieved data against expected values
	    } catch (SQLException e) {
	        Assert.fail("Error retrieving data: " + e.getMessage());
	    }
	}
	
	
	
}
