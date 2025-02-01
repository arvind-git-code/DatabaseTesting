package schemaValidation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;
public class TableExists {
	
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	@Test
	public void testTableExists() {
	    String tableName = "COUNTRIES";
	    String sql = "SELECT COUNT(*) FROM all_tables WHERE table_name = UPPER(?)"; // Oracle uses ALL_TABLES
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, tableName); // Case-insensitive search (UPPER)
	        ResultSet rs = stmt.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        Assert.assertTrue(count > 0, "Table " + tableName + " does not exist.");
	    }catch (SQLException e) {
	        Assert.fail("Error checking table existence: " + e.getMessage());
	    }
	}
}