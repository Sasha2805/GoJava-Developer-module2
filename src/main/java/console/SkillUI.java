package console;

import dao.jdbc.SkillDaoJDBC;
import objects.Skill;
import java.sql.SQLException;
import java.util.Scanner;

public class SkillUI {
    public static void insertSkill(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        System.out.print("Enter branch of development: ");
        String branchDevelopment = in.next();
        System.out.print("Enter skill level: ");
        String skillLevel = in.next();
        new SkillDaoJDBC().insert(new Skill(id, branchDevelopment, skillLevel));
    }

    public static void updateSkill(Scanner in) throws SQLException {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        Skill skill = new SkillDaoJDBC().selectById(id);
        System.out.println(skill);
        System.out.println("Select a field to update");
        System.out.println("1. Branch of development");
        System.out.println("2. Skill level");
        System.out.println("3. Finish update");
        int choice = in.nextInt();
        while (true) {
            if (choice == 1) {
                System.out.print("Enter branch of development: ");
                String branchDevelopment = in.next();
                skill.setBranchDevelopment(branchDevelopment);
                new SkillDaoJDBC().update(skill);
            } else if (choice == 2) {
                System.out.print("Enter skill level: ");
                String skillLevel = in.next();
                skill.setSkillLevel(skillLevel);
                new SkillDaoJDBC().update(skill);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }
}
