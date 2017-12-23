package webapp.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import webapp.models.Todo;

/**
 * The Job if this is class is to get information from a database.
 * To be able to do CRUD operations on it.
 * @author Petko-PC
 *
 */
public class TodoService
{
	
	// Connects to the Database, if there is a problem with the connection throws an exception
	//private DataSource datasource;
	
	
	private static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL_USERNAME_PASSWORD = "jdbc:mysql://localhost/todo_app?user=root&password=p3tkotrenev";

	/**
	 * This method connects to the database and get all available students.
	 * @return a list of all available todos from the database.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Todo> getTodos() throws SQLException, ClassNotFoundException {
		List<Todo> todos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(COM_MYSQL_JDBC_DRIVER);
			conn = DriverManager.getConnection(DBURL_USERNAME_PASSWORD);
			String sql = "select * from todo_app.todos";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String name = rs.getString("name");
				String category = rs.getString("category");
				
				Todo todo = new Todo(name, category);
				todos.add(todo);
			}
		} finally {
			close(conn, stmt, rs);
		}
		
		return todos;
	}

	/**
	 * Reliably closes all connection, that may still be open after the execution
	 * of the query.
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	private void close(Connection conn, Statement stmt, ResultSet rs)
	{
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addTodo(Todo todo) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO todos (name, category) VALUES (?, ?)";
			Class.forName(COM_MYSQL_JDBC_DRIVER);
			conn = DriverManager.getConnection(DBURL_USERNAME_PASSWORD); 
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, todo.getName());
			statement.setString(2, todo.getCategory());
			statement.executeUpdate();
		} finally {
			close(conn, stmt, rs);
		}
		
	}
	
	public void deleteTodo(Todo todo) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "delete from todos where name = ?";

		try {
			conn = DriverManager.getConnection(DBURL_USERNAME_PASSWORD); 
			preparedStatement = conn.prepareStatement(deleteSQL);
			preparedStatement.setString(1, todo.getName());

			// execute delete SQL stetement
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			close(conn, preparedStatement, null);
		}
	}
	
	
}
