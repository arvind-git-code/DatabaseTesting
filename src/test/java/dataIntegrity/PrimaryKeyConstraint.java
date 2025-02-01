package dataIntegrity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PrimaryKeyConstraint {

	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	@Test
	public void testPrimaryKeyConstraint() {
	    String tableName = "employees";
	    String sql = "SELECT COUNT(*) FROM all_constraints WHERE table_name = UPPER(?) AND constraint_type = 'P'"; // 'P' for Primary Key
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, tableName);
	        ResultSet rs = stmt.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        Assert.assertTrue(count > 0, "Primary key constraint not found for table " + tableName + ".");
	    } catch (SQLException e) {
	        Assert.fail("Error checking primary key constraint: " + e.getMessage());
	    }
	}}
	
	
	
	
	