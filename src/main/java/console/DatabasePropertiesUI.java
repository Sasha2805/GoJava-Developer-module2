package console;

import properties.PropertyLoader;
import java.util.Scanner;

public class DatabasePropertiesConsole {
    public static void saveProperties(Scanner in){
        System.out.print("Database name:");
        String databaseName = in.next();

        System.out.print("Host name:");
        String hostName = in.next();

        System.out.print("TCP/IP port:");
        String port = in.next();

        System.out.print("User name:");
        String userName = in.next();

        System.out.print("User password:");
        String userPassword = in.next();
        PropertyLoader.saveDbProperties(databaseName, hostName, port, userName, userPassword);
    }
}
