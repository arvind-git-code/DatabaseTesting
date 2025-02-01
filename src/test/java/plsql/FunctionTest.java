package plsql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;
public class FunctionTest {
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	@Test
	public void testDatabaseFunction() {
	    String functionName = "calculate_discount";
	    int quantity = 10;
	    double price = 25.0;

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         CallableStatement stmt = conn.prepareCall("{? = call " + functionName + "(?, ?)}")) {
	        stmt.registerOutParameter(1, java.sql.Types.DOUBLE);
	        stmt.setInt(2, quantity);
	        stmt.setDouble(3, price);
	        stmt.execute();

	        double discount = stmt.getDouble(1);
	        double expectedDiscount = calculateExpectedDiscount(quantity, price);
	        Assert.assertEquals(expectedDiscount, discount, 0.001, "Incorrect discount calculated.");

	    } catch (SQLException e) {
	        Assert.fail("Error testing database function: " + e.getMessage());
	    }
	}
	
	    public static double calculateExpectedDiscount(int quantity, double price) {
	        // Assuming a 10% discount for quantities over 10 and a 5% discount for quantities over 5
	        if (quantity > 10) {
	            return price * 0.10;
	        } else if (quantity > 5) {
	            return price * 0.05;
	        } else {
	            return 0.0;
	        }
	    }
	}
	

