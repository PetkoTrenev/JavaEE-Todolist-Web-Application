package webapp.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil
{
	private static Connection dbConnection = null;

	public static Connection getConnection()
	{
		if (dbConnection != null) {
			return dbConnection;
		} else {
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream stream = classLoader.getResourceAsStream("db.properties");
				/*
				 * InputStream inputStream = DbUtil.class.getClassLoader()
				 * .getResourceAsStream("/src/main/resources/db.properties");
				 */
				Properties properties = new Properties();
				if (properties != null) {
					properties.load(stream);

					String dbDriver = properties.getProperty("dbDriver");
					String connectionUrl = properties.getProperty("connectionUrl");
					String userName = properties.getProperty("userName");
					String password = properties.getProperty("password");

					Class.forName(dbDriver).newInstance();
					dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dbConnection;
		}
	}
}
