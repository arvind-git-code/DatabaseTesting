package plsql;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.*;

public class TriggerTest {
		
	final private String url="jdbc:oracle:thin:@//localhost:1521/FREE";
	final private String user="dbuser1";
	final private String password="12345";
	
	
    @Test
    public void testInsertTrigger() throws SQLException {
       
    	String insertSql = "INSERT INTO departments (department_id, department_name) VALUES (53, 'New Department')";
    	Connection conn =DriverManager.getConnection(url,user,password);
        // Insert a new department
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(insertSql);

        // Verify audit log entry
        String selectSql = "SELECT * FROM dept_audit_log WHERE operation = 'INSERT' AND department_id = 53";
        ResultSet rs = stmt.executeQuery(selectSql);
        assertTrue(rs.next(), "Insert trigger did not fire.");
        assertEquals(rs.getString("operation"), "INSERT");
        assertEquals(rs.getString("new_name"), "New Department");

        // ... (Cleanup - optional: Delete inserted department) ...

        // ... (Close connection) ...
        conn.close();
    }

    @Test
    public void testUpdateTrigger() throws SQLException {
    	
    	// Update an existing department
    	String updateSql = "UPDATE departments SET department_name = 'Updated Dept' WHERE department_id = 50";
    	
    	Connection conn =DriverManager.getConnection(url,user,password);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(updateSql);

        // Verify audit log entry
        String selectSql = "SELECT * FROM dept_audit_log WHERE operation = 'UPDATE' AND department_id = 50";
        ResultSet rs = stmt.executeQuery(selectSql);
        assertTrue(rs.next(), "Update trigger did not fire.");
        assertEquals(rs.getString("operation"), "UPDATE");
        // ... (Further assertions on old_name and new_name) ...

        // ... (Close connection) ...
        conn.close();
    }

    @Test
    public void testDeleteTrigger() throws SQLException {
    	
    	// Delete a department (ensure this is a test department)
    	String deleteSql = "DELETE FROM departments WHERE department_id = 50"; 
    	
    	Connection conn =DriverManager.getConnection(url,user,password);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(deleteSql);

        // Verify audit log entry
        String selectSql = "SELECT * FROM dept_audit_log WHERE operation = 'DELETE' AND department_id = 50";
        ResultSet rs = stmt.executeQuery(selectSql);
        assertTrue(rs.next(), "Delete trigger did not fire.");
        assertEquals(rs.getString("operation"), "DELETE");

        // ... (Close connection) ...
        conn.close();
    }

    // ... (Database connection setup and cleanup methods) ...
}