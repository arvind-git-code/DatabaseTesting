package functionalTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;
public class TestDataInsertion {

	
	
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	
	@Test
	public void testDataInsertion() {
	    String sql = "INSERT INTO departments (DEPARTMENT_ID, DEPARTMENT_NAME) VALUES ('280','Useless')";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         Statement stmt = conn.createStatement()) {
	        int rowsAffected = stmt.executeUpdate(sql);
	        Assert.assertEquals(1, rowsAffected, "Insertion failed.");
	    } catch (SQLException e) {
	        Assert.fail("Error inserting data: " + e.getMessage());
	    }
	}
	
	
	
}
