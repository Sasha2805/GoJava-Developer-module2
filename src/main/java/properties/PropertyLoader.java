package properties;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {
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
}
