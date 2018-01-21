package console;

import dao.Dao;
import dao.jdbc.CompanyDaoJDBC;
import dao.jdbc.DeveloperDaoJDBC;
import dao.jdbc.ProjectDaoJDBC;
import dao.jdbc.SkillDaoJDBC;
import java.sql.SQLException;
import java.util.Scanner;

public class DaoConsole {
    public static void getMethods(Scanner in, Dao objectDao) throws SQLException {
        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Select by id");
            System.out.println("2. Select all");
            System.out.println("3. Delete by id");
            System.out.println("4. Insert");
            System.out.println("5. Update");
            System.out.println("6. Finish work with the object");
            int choice = in.nextInt();
            if (choice == 1) {
                System.out.print("Enter id: ");
                int id = in.nextInt();
                System.out.println(objectDao.selectById(id));
            } else if (choice == 2) {
                for (Object object : objectDao.selectAll()) {
                    System.out.println(object);
                }
            } else if (choice == 3) {
                System.out.print("Enter id: ");
                int id = in.nextInt();
                objectDao.deleteById(id);
                System.out.println("Developer deleted.");
            } else if (choice == 4) {
                if (objectDao instanceof DeveloperDaoJDBC) {
                    DeveloperConsole.insertDeveloper(in);
                } else if (objectDao instanceof ProjectDaoJDBC) {
                    ProjectConsole.insertProject(in);
                } else if (objectDao instanceof SkillDaoJDBC) {
                    SkillConsole.insertSkill(in);
                } else if (objectDao instanceof CompanyDaoJDBC) {
                    CompanyConsole.insertCompany(in);
                } else {
                    CustomerConsole.insertCustomer(in);
                }
            } else if (choice == 5) {
                if (objectDao instanceof DeveloperDaoJDBC) {
                    DeveloperConsole.updateDeveloper(in);
                } else if (objectDao instanceof ProjectDaoJDBC) {
                    ProjectConsole.updateProject(in);
                } else if (objectDao instanceof SkillDaoJDBC) {
                    SkillConsole.updateSkill(in);
                } else if (objectDao instanceof CompanyDaoJDBC) {
                    CompanyConsole.updateCompany(in);
                } else {
                    CustomerConsole.updateCustomer(in);
                }
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }
}
