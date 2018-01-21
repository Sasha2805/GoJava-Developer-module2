package jdbc.dbCRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TableCreator {
    public static void createTable(Connection connection){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the table:");
        String tableName = scanner.next();
        String fields = "";

        while (true){
            System.out.print("Enter the name of the field:");
            fields = fields.concat(scanner.next() + " " +
                setFieldType(scanner) + " " +
                setParameter("NOT NULL", scanner)  +
                setParameter("AUTO_INCREMENT", scanner) +
                setParameter("PRIMARY KEY", scanner));

            System.out.println("Make a choice:");
            System.out.println("1. Add another field");
            System.out.println("2. Finish adding fields");
            int choice = setChoice(scanner, 1, 2);

            if (choice == 1) {
                fields = fields.concat(", ");
            } else {
                break;
            }
        }

        String sql = "CREATE TABLE " + tableName + "(" + fields + ");";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // The method specifies the type of field
    private static String setFieldType(Scanner scanner){
        System.out.println("Select type of the field:");
        System.out.println("1. CHAR");
        System.out.println("2. VARCHAR(if you put a greater value than 255 it will be converted to a TEXT type)");
        System.out.println("3. INT");
        System.out.println("4. DOUBLE");
        System.out.println("5. DECIMAL");
        int choice = setChoice(scanner, 1, 5);

        if (choice == 1) {
            return setRange("CHAR", 0, 255, scanner);
        } else if (choice == 2) {
            return setRange("VARCHAR", 0, 65535, scanner);
        } else if (choice == 3) {
            return "INT ";
        } else if (choice == 4) {
            return setParameters("DOUBLE", scanner);
        } else {
            return setParameters("DECIMAL", scanner);
        }
    }

    // The method specifies the maximum type range
    private static String setRange(String type, int minRangeValue, int maxRangeValue, Scanner scanner){
        System.out.println("Make a choice:");
        System.out.println("1. Specify the maximum value of type");
        System.out.println("2. Set default and continue");
        int choice = setChoice(scanner, 1, 2);

        if (choice == 1){
            System.out.println("Enter a number between " + minRangeValue + " and " + maxRangeValue);
            int value = scanner.nextInt();
            while (value < minRangeValue || value > maxRangeValue){
                System.out.println("The number is not within the specified range. Enter the number again");
                value = scanner.nextInt();
            }
            return type + "(" + value + ") ";
        } else {
            return type + "(" + "255" + ") ";
        }
    }

    // The method specifies the type parameters
    private static String setParameters(String type, Scanner scanner){
        System.out.println("Make a choice:");
        System.out.println("1. Specify the type parameters");
        System.out.println("2. Set default and continue");
        int choice = setChoice(scanner, 1, 2);

        if (choice == 1){
            System.out.print("Enter the maximum number of digits of the number: ");
            int size = scanner.nextInt();
            System.out.print("Enter the maximum number of digits to the right of the decimal point: ");
            int d = scanner.nextInt();

            return type + "(" + size + ", " + d + ") ";
        } else {
            return type + "(" + 10 + ", " + 2 + ") ";
        }
    }

    // The method specifies the field parameters
    private static String setParameter(String parameter, Scanner scanner){
        System.out.println("Set parameter " + parameter + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = setChoice(scanner, 1, 2);

        if (choice == 1){
            return parameter + " ";
        } else {
            return "";
        }
    }

    // The method checks the selected menu item
    private static int setChoice(Scanner scanner, int minNumberChoice, int maxNumberChoice){
        int choice = scanner.nextInt();
        while (choice < minNumberChoice || choice > maxNumberChoice){
            System.out.println("You entered an invalid number...Enter the number again.");
            choice = scanner.nextInt();
        }
        return choice;
    }
}
