package plsql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlsqlScriptTest {

	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	@Test
	public void runPLSQLScriptFromFile() throws IOException {
	    String filePath = "src/test/resources/script.sql"; // Path to your .sql file
	    
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         Statement stmt = conn.createStatement()) {
	    	conn.setAutoCommit(false);
	    	
	        String plsqlScript = Files.readString(Paths.get(filePath));
	        stmt.execute(plsqlScript);
	        conn.commit();
	        // ... any assertions ...
	        conn.close();
	        
	    } catch (SQLException e) {
	        Assert.fail("Error running PL/SQL script from file: " + e.getMessage());
	    }
	    
	   
	   
	}
	
	
	
}
