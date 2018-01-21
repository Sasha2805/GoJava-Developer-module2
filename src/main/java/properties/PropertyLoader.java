package properties;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyLoader {
    // Loading properties from the file
    public static Properties loadProperties(String filePath){
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filePath);
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // Saving properties to the file
    public static void saveProperties(Properties properties, String filePath, String fileName) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            Files.createDirectory(Paths.get(filePath));
        }
        OutputStream output = null;
        try {
            File file = new File(filePath + fileName);
            output = new FileOutputStream(file);
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Saving database settings in the properties file
    public static boolean saveDbProperties(String databaseName, String hostName, String port, String userName,
                                          String userPassword){
        Properties dbProperties = new Properties();
        dbProperties.setProperty("databaseName", databaseName);
        dbProperties.setProperty("hostName", hostName);
        dbProperties.setProperty("port", port);
        dbProperties.setProperty("userName", userName);
        dbProperties.setProperty("userPassword", userPassword);
        try {
            saveProperties(dbProperties, "src/main/resources/", "dbProperties/database.properties");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
