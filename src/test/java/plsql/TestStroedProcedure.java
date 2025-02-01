package plsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;
public class TestStroedProcedure {

	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";

	@Test
	public void testStoredProcedure() {
		String procedureName = "update_salary";
		int p_employee_id = 132;
		double p_salary = 89000;

		try (Connection conn = DriverManager.getConnection(url, user, password);
				CallableStatement stmt = conn.prepareCall("{call " + procedureName + "(?, ?)}")) { // Call stored
																									// procedure
			stmt.setInt(1, p_employee_id);
			stmt.setDouble(2, p_salary);
			stmt.execute();

			// Query to verify the balance update (Oracle SQL)
			String sql = "SELECT salary FROM employees WHERE employee_id = ?";
			try (PreparedStatement selectStmt = conn.prepareStatement(sql)) {
				selectStmt.setInt(1, p_employee_id);
				ResultSet rs = selectStmt.executeQuery();
				rs.next();
				double updatedSalary = rs.getDouble("salary");

				double expectedSalary = p_salary;
				Assert.assertEquals(expectedSalary, updatedSalary, "Salary is not updated correctly.");
			}

		} catch (SQLException e) {
			Assert.fail("Error testing stored procedure: " + e.getMessage());
		}
	}




}
