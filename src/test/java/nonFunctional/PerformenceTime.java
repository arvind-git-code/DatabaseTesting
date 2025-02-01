package nonFunctional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PerformenceTime {
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	@Test
	public void testPerformance() throws SQLException {
		String sql1="select * from employees";
		String sql2="select * from departments";
		String sql3="select * from locations";
		String sql4="select * from jobs";
		String sql5="select * from job_history";
		String sql6="select * from countries";
		String sql7="select * from regions";

		
	   Connection con =DriverManager.getConnection(url,user,password);
	   Statement stmt=con.createStatement();
	   long startTime = System.currentTimeMillis();
	   System.out.println(startTime);
	   stmt.executeQuery("select * from employees");
	   stmt.executeQuery("select * from departments");
	   stmt.executeQuery("select * from locations");
	   stmt.executeQuery("select * from jobs");
	   stmt.executeQuery("select * from job_history");
	   stmt.executeQuery("select * from countries");
	   stmt.executeQuery("select * from regions");
	   stmt.executeQuery("select * from ALL_tables");
	   stmt.executeQuery("select * from ALL_TABLES");
	   stmt.executeQuery("select * from ALL_CONSTRAINTS");
	   stmt.executeQuery("select * from ALL_OBJECTS");
	   stmt.executeQuery("select * from ALL_INDEXES");
	   stmt.executeQuery("select * from ALL_TAB_COLUMNS");
	   stmt.executeQuery("select * from ALL_USERS");
	   stmt.executeQuery("select * from ALL_TAB_PARTITIONS");
	   stmt.executeQuery("select * from ALL_TAB_SUBPARTITIONS");
	   
	    long endTime = System.currentTimeMillis();
	    System.out.println(endTime);
	    long elapsedTime = endTime - startTime;
	    System.out.println(elapsedTime);
	    Assert.assertTrue(elapsedTime < 1000, "Operation took too long: " + elapsedTime + "ms"); 
	    
	    stmt.close();
	    con.close();
	}
	
	
	
}
