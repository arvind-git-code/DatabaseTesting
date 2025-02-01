package plsql;

public class TriggerTest {

	@Test
	public void testTrigger() {
	    String insertSql = "INSERT INTO orders (customer_id, order_date, total_amount) VALUES (456, DATE '2024-07-27', 100.0)"; // Oracle DATE literal

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         Statement stmt = conn.createStatement()) {
	        stmt.executeUpdate(insertSql);

	        // Query the audit table (example)
	        String selectSql = "SELECT * FROM order_audit WHERE order_id = (SELECT MAX(order_id) FROM orders)"; // Get last inserted order_id
	        try (ResultSet rs = stmt.executeQuery(selectSql)) {
	            Assert.assertTrue(rs.next(), "Trigger did not fire.");
	            // ... (other assertions)
	        }
	    } catch (SQLException e) {
	        Assert.fail("Error testing trigger: " + e.getMessage());
	    }
	}
	
}
