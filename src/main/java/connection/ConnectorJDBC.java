package connection;

import properties.PropertyLoader;
import java.sql.*;
import java.util.Properties;

public class ConnectorJDBC {
    private static final String URL = "jdbc:mysql://%s:%s/%s?useSSL=false&useUnicode=true&" +
            "useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Properties dbProperties = PropertyLoader.loadProperties("src/main/resources/dbProperties" +
            "/database.properties");

    // Getting connection with database
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                String.format(URL, dbProperties.getProperty("hostName"),
                    dbProperties.getProperty("port"),
                    dbProperties.getProperty("databaseName")),
                dbProperties.getProperty("userName"),
                dbProperties.getProperty("userPassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}