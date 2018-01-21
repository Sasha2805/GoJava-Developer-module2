package console;

import dao.jdbc.CompanyDaoJDBC;
import objects.Company;
import java.sql.SQLException;
import java.util.Scanner;

public class CompanyConsole {
    public static void insertCompany(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        System.out.print("Enter name: ");
        String name = in.next();
        System.out.print("Enter info: ");
        String info = in.next();
        new CompanyDaoJDBC().insert(new Company(id, name, info));
    }

    public static void updateCompany(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        Company company = new CompanyDaoJDBC().selectById(id);
        System.out.println(company);
        System.out.println("Select a field to update");
        System.out.println("1. Name");
        System.out.println("2. Info");
        System.out.println("3. Finish update");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                System.out.print("Enter project name: ");
                String name = in.next();
                company.setName(name);
                new CompanyDaoJDBC().update(company);
            } else if (choice == 2) {
                System.out.print("Enter project info: ");
                String info = in.next();
                company.setInfo(info);
                new CompanyDaoJDBC().update(company);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }
}
