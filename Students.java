import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Students {
	// constant
	static final String database = "test";
	static final String URL = "jdbc:mysql://localhost:3306/" + database;
	static final String USER = "root";
	static final String PASSWORD = "123456";
	static private Connection conn = null;
	static private Statement stmt = null;

	public static void createTableStudents() throws Exception {
		String sql = "CREATE TABLE IF NOT EXISTS student (name VARCHAR(40), photo TEXT, class VARCHAR(128), notes TEXT) CHARSET=utf8;";
		stmt = conn.createStatement();
		stmt.executeLargeUpdate(sql);
	}

	public static void createTableNotes() throws Exception {
		String sql = "CREATE TABLE IF NOT EXISTS note (createdTime DATETIME, updateTime DATETIME, typeOfNote VARCHAR(20), whoHasAccessToIt VARCHAR(40), notesDescription TEXT) CHARSET=utf8;";
		stmt = conn.createStatement();
		stmt.executeLargeUpdate(sql);
	}

	public static void createTableStaff() throws Exception {
		String sql = "CREATE TABLE IF NOT EXISTS staff (staffName VARCHAR(40), username VARCHAR(40), password VARCHAR(40)) CHARSET=utf8;";
		stmt = conn.createStatement();
		stmt.executeLargeUpdate(sql);
	}

	public static void main(String[] args) throws Exception {
		// connect database var jdbc
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		createTableStudents();
		createTableNotes();
		createTableStaff();

		// insert sample data
		stmt.execute("INSERT INTO student VALUES('testname', 'photo', '2022', 'notes')");

		// execute query based on user input
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
		// print query result
		while (rs.next()) {
			System.out.format("%s %s %s %s\n", rs.getString("name"), rs.getString("photo"), rs.getString("class"), rs.getString("notes"));
		}
		rs.close();
		stmt.close();
		conn.close();
	}
}
