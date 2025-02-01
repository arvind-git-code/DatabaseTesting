package plsql;

public class TestStroedProcedure {

	@Test
	public void testStoredProcedure() {
	    String procedureName = "update_customer_balance";
	    int customerId = 123;
	    double amount = 50.0;

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         CallableStatement stmt = conn.prepareCall("{call " + procedureName + "(?, ?)}")) { // Call stored procedure
	        stmt.setInt(1, customerId);
	        stmt.setDouble(2, amount);
	        stmt.execute();

	        // Query to verify the balance update (Oracle SQL)
	        String sql = "SELECT balance FROM customers WHERE customer_id = ?";
	        try (PreparedStatement selectStmt = conn.prepareStatement(sql)) {
	            selectStmt.setInt(1, customerId);
	            ResultSet rs = selectStmt.executeQuery();
	            rs.next();
	            double updatedBalance = rs.getDouble("balance");

	            double expectedBalance = getExpectedBalance(customerId, amount);
	            Assert.assertEquals(expectedBalance, updatedBalance, 0.001, "Balance not updated correctly.");
	        }

	    } catch (SQLException e) {
	        Assert.fail("Error testing stored procedure: " + e.getMessage());
	    }
	}

	// ... (Helper method getExpectedBalance remains the same)
	
	
}
