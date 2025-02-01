package schemaValidation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ColumnExists {
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	
	
	@Test
	public void testColumnExists() {
	    String tableName = "employees";
	    String columnName = "FIRST_NAME";
	    String sql = "SELECT COUNT(*) FROM all_tab_columns WHERE table_name = UPPER(?) AND column_name = UPPER(?)";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, tableName);
	        stmt.setString(2, columnName);
	        ResultSet rs = stmt.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        Assert.assertTrue(count > 0, "Column " + columnName + " does not exist in table " + tableName + ".");
	    } catch (SQLException e) {
	        Assert.fail("Error checking column existence: " + e.getMessage());
	    }
	    }
	
	
	
	
	
}
