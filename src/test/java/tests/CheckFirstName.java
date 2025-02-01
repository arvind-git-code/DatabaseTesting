package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckFirstName {

	private static Connection con;
	private static Statement stmt;

	@BeforeClass
	public void setup() {
		try {

			// Load Oracle JDBC driver
//            Class.forName("oracle.jdbc.driver.OracleDriver");

//			make connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/FREE", "dbuser1", "12345");
			stmt = con.createStatement();
			System.out.println("database connection complete");

		} catch (SQLException e) {
			System.out.println("Connection not Stablished");
			e.printStackTrace();
		}
	}

	@Test
	public void testName() throws SQLException {
		ResultSet rs = stmt.executeQuery("select * from employees");

		Assert.assertTrue(rs.next(),"No data selected");
		Assert.assertEquals(rs.getString("first_name"), "Steven");
//		rs.findColumn("")
		

	}

	@AfterClass
	public void tearDown() {
		try {
			if (stmt != null) {
				stmt.close();
				System.out.println("Statement closed");
			}
			if (con != null && !con.isClosed()) {
				con.close();
		System.out.println("Database clonnection closed");
			}

		} catch (SQLException e) {
			System.out.println("There is an error in close connection");
		}

	}
}
