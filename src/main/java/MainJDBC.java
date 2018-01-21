import dao.Dao;
import dao.jdbc.*;
import objects.*;
import properties.PropertyLoader;
import java.sql.SQLException;
import java.util.Scanner;

public class MainJDBC {
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Database Manager");
        while (true){
            System.out.println("Select an action:");
            System.out.println("1. Enter database information");
            System.out.println("2. Exit");
            int choice = in.nextInt();
            if (choice == 1){
                break;
            } else if (choice == 2) {
                in.close();
                return;
            } else {
                System.out.println("You entered an invalid number..Enter the number again.");
            }
        }

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

        while (true){
            System.out.println("Select an action:");
            System.out.println("1. Connect to the database");
            System.out.println("2. Exit");
            int choice = in.nextInt();
            if (choice == 1){
                PropertyLoader.saveDbProperties(databaseName, hostName, port, userName, userPassword);
                System.out.println("Select the type of object you want to work with");
                System.out.println("1. Developer");
                System.out.println("2. Project");
                System.out.println("3. Customer");
                System.out.println("4. Company");
                System.out.println("5. Skill");
                int objNum = in.nextInt();
                if (objNum == 1){
                    getDaoMethods(in, new DeveloperDaoJDBC());
                } else if (objNum == 2){
                    getDaoMethods(in, new ProjectDaoJDBC());
                } else if (objNum == 3) {
                    getDaoMethods(in, new CustomerDaoJDBC());
                } else if (objNum == 4) {
                    getDaoMethods(in, new CompanyDaoJDBC());
                } else {
                    getDaoMethods(in, new SkillDaoJDBC());
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

    private static void getDaoMethods(Scanner in, Dao objectDao) throws SQLException {
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
                if (objectDao instanceof DeveloperDaoJDBC){
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
                    ((DeveloperDaoJDBC) objectDao).insert(new Developer(id, firstName, lastName, age, sex, salary));
                } else if (objectDao instanceof ProjectDaoJDBC) {
                    System.out.print("Enter id: ");
                    int id = in.nextInt();
                    System.out.print("Enter name: ");
                    String name = in.next();
                    System.out.print("Enter info: ");
                    String info = in.next();
                    ((ProjectDaoJDBC) objectDao).insert(new Project(id, name, info));
                } else if (objectDao instanceof SkillDaoJDBC) {
                    System.out.print("Enter id: ");
                    int id = in.nextInt();
                    System.out.print("Enter branch of development: ");
                    String branchDevelopment = in.next();
                    System.out.print("Enter skill level: ");
                    String skillLevel = in.next();
                    ((SkillDaoJDBC) objectDao).insert(new Skill(id, branchDevelopment, skillLevel));
                } else if (objectDao instanceof CompanyDaoJDBC) {
                    System.out.print("Enter id: ");
                    int id = in.nextInt();
                    System.out.print("Enter name: ");
                    String name = in.next();
                    System.out.print("Enter info: ");
                    String info = in.next();
                    ((CompanyDaoJDBC) objectDao).insert(new Company(id, name, info));
                } else {
                    System.out.print("Enter id: ");
                    int id = in.nextInt();
                    System.out.print("Enter first name: ");
                    String firstName = in.next();
                    System.out.print("Enter last name: ");
                    String lastName = in.next();
                    System.out.print("Enter info: ");
                    String info = in.next();
                    ((CustomerDaoJDBC) objectDao).insert(new Customer(id, firstName, lastName, info));
                }
            } else if (choice == 5) {
                if (objectDao instanceof DeveloperDaoJDBC) {
                   updateDeveloper(in);
                } else if (objectDao instanceof ProjectDaoJDBC) {
                    updateProject(in);
                } else if (objectDao instanceof SkillDaoJDBC) {
                    updateSkill(in);
                } else if (objectDao instanceof CompanyDaoJDBC) {
                    updateCompany(in);
                } else {
                    updateCustomer(in);
                }
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("You entered an invalid number...Enter the number again.");
            }
        }
    }

    private static void updateDeveloper(Scanner in) throws SQLException {
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

    private static void updateProject(Scanner in) throws SQLException {
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

    private static void updateSkill(Scanner in) throws SQLException {
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

    private static void updateCompany(Scanner in) throws SQLException {
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

    private static void updateCustomer(Scanner in) throws SQLException {
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
