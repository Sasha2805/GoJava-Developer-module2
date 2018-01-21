package console;

import dao.jdbc.DeveloperDaoJDBC;
import objects.Developer;
import java.sql.SQLException;
import java.util.Scanner;

public class DeveloperConsole {
    public static void insertDeveloper(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        System.out.print("Enter first name: ");
        String firstName = in.next();
        System.out.print("Enter last name: ");
        String lastName = in.next();
        System.out.print("Enter age: ");
        int age = in.nextInt();
        System.out.print("Enter sex: ");
        String sex = in.next();
        System.out.print("Enter salary: ");
        double salary = in.nextDouble();
        new DeveloperDaoJDBC().insert(new Developer(id, firstName, lastName, age, sex, salary));
    }

    public static void updateDeveloper(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        Developer developer = new DeveloperDaoJDBC().selectById(id);
        System.out.println(developer);
        System.out.println("Select a field to update");
        System.out.println("1. First name");
        System.out.println("2. Last name");
        System.out.println("3. Age");
        System.out.println("4. Sex");
        System.out.println("5. Salary");
        System.out.println("6. Finish update");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                System.out.print("Enter first name: ");
                String firstName = in.next();
                developer.setFirstName(firstName);
                new DeveloperDaoJDBC().update(developer);
            } else if (choice == 2) {
                System.out.print("Enter last name: ");
                String lastName = in.next();
                developer.setLastName(lastName);
                new DeveloperDaoJDBC().update(developer);
            } else if (choice == 3) {
                System.out.print("Enter age: ");
                int age = in.nextInt();
                developer.setAge(age);
                new DeveloperDaoJDBC().update(developer);
            } else if (choice == 4) {
                System.out.print("Enter sex: ");
                String sex = in.next();
                developer.setSex(sex);
                new DeveloperDaoJDBC().update(developer);
            } else if (choice == 5) {
                System.out.print("Enter salary: ");
                double salary = in.nextDouble();
                developer.setSalary(salary);
                new DeveloperDaoJDBC().update(developer);
            } else if (choice == 6){
                break;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }
}
