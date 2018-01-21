package connectionJdbc;

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
            System.out.println("The driver is connected.");

            Connection connection = DriverManager.getConnection(
                String.format(URL, dbProperties.getProperty("hostName"),
                    dbProperties.getProperty("port"),
                    dbProperties.getProperty("databaseName")),
                dbProperties.getProperty("userName"),
                dbProperties.getProperty("userPassword"));
            System.out.println("Connection established.");

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}