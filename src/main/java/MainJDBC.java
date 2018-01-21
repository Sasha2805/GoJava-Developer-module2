import console.DaoUI;
import console.DatabasePropertiesUI;
import dao.jdbc.*;
import java.sql.SQLException;
import java.util.Scanner;

public class MainJDBC {
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Database Manager");
        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Enter database information");
            System.out.println("2. Exit");
            int choice = in.nextInt();
            if (choice == 1) {
                break;
            } else if (choice == 2) {
                in.close();
                return;
            } else {
                System.out.println("You entered an invalid number..Enter the number again.");
            }
        }
        DatabasePropertiesUI.saveProperties(in);

        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Connect to the database");
            System.out.println("2. Exit");
            int choice = in.nextInt();
            if (choice == 1) {
                System.out.println("Select the type of object you want to work with");
                System.out.println("1. Developer");
                System.out.println("2. Project");
                System.out.println("3. Customer");
                System.out.println("4. Company");
                System.out.println("5. Skill");
                int objNum = in.nextInt();
                if (objNum == 1) {
                   DaoUI.getMethods(in, new DeveloperDaoJDBC());
                } else if (objNum == 2) {
                    DaoUI.getMethods(in, new ProjectDaoJDBC());
                } else if (objNum == 3) {
                    DaoUI.getMethods(in, new CustomerDaoJDBC());
                } else if (objNum == 4) {
                    DaoUI.getMethods(in, new CompanyDaoJDBC());
                } else {
                    DaoUI.getMethods(in, new SkillDaoJDBC());
                }
                in.close();
                break;
            } else if (choice == 2) {
                in.close();
                return;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }
}