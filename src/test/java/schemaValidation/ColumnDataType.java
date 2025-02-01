package schemaValidation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ColumnDataType {
	
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	
	
	@Test
	public void testColumnDataType()
	{
		
	    String tableName = "jobs";
	    String columnName = "MIN_SALARY";
	    String expectedDataType = "NUMBER"; // Oracle's integer type is NUMBER
	    String sql = "SELECT data_type FROM all_tab_columns WHERE table_name = UPPER(?) AND column_name = UPPER(?)";
	    try (
	    		Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, tableName);
	        stmt.setString(2, columnName);
	        ResultSet rs = stmt.executeQuery();
	        rs.next();
	        String actualDataType = rs.getString(1);
	        Assert.assertEquals(expectedDataType, actualDataType, "Incorrect data type for column " + columnName + ".");
	    } catch (SQLException e) {
	        Assert.fail("Error checking column data type: " + e.getMessage());
	    }
	    }
	}
	
	

