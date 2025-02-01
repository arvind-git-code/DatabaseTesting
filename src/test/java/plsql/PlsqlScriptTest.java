package plsql;

public class PlsqlScriptTest {

	@Test
	public void runPLSQLScriptFromFile() throws IOException {
	    String filePath = "path/to/your/script.sql"; // Path to your .sql file

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         Statement stmt = conn.createStatement()) {

	        String plsqlScript = Files.readString(Paths.get(filePath));
	        stmt.execute(plsqlScript);

	        // ... any assertions ...

	    } catch (SQLException e) {
	        Assert.fail("Error running PL/SQL script from file: " + e.getMessage());
	    }
	}
	
	
}
