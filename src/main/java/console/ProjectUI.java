package console;

import dao.jdbc.ProjectDaoJDBC;
import objects.Project;
import java.sql.SQLException;
import java.util.Scanner;

public class ProjectUI {
    public static void insertProject(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        System.out.print("Enter name: ");
        String name = in.next();
        System.out.print("Enter info: ");
        String info = in.next();
        new ProjectDaoJDBC().insert(new Project(id, name, info));
    }

    public static void updateProject(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        Project project = new ProjectDaoJDBC().selectById(id);
        System.out.println(project);
        System.out.println("Select a field to update");
        System.out.println("1. Name");
        System.out.println("2. Info");
        System.out.println("3. Finish update");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                System.out.print("Enter project name: ");
                String name = in.next();
                project.setName(name);
                new ProjectDaoJDBC().update(project);
            } else if (choice == 2) {
                System.out.print("Enter project info: ");
                String info = in.next();
                project.setInfo(info);
                new ProjectDaoJDBC().update(project);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }
}
