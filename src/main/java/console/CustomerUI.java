package console;

import dao.jdbc.CustomerDaoJDBC;
import objects.Customer;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerUI {
    public static void insertCustomer(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        System.out.print("Enter first name: ");
        String firstName = in.next();
        System.out.print("Enter last name: ");
        String lastName = in.next();
        System.out.print("Enter info: ");
        String info = in.next();
        new CustomerDaoJDBC().insert(new Customer(id, firstName, lastName, info));
    }

    public static void updateCustomer(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        Customer customer = new CustomerDaoJDBC().selectById(id);
        System.out.println(customer);
        System.out.println("Select a field to update");
        System.out.println("1. First name");
        System.out.println("2. Last name");
        System.out.println("3. Info");
        System.out.println("4. Finish update");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                System.out.print("Enter first name: ");
                String firstName = in.next();
                customer.setFirstName(firstName);
                new CustomerDaoJDBC().update(customer);
            } else if (choice == 2) {
                System.out.print("Enter last name: ");
                String lastName = in.next();
                customer.setLastName(lastName);
                new CustomerDaoJDBC().update(customer);
            } else  if (choice == 3) {
                System.out.print("Enter customer info: ");
                String info = in.next();
                customer.setInfo(info);
                new CustomerDaoJDBC().update(customer);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }
}
