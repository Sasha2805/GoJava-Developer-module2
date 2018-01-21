package dao.jdbc;

import connection.ConnectorJDBC;
import dao.DeveloperDao;
import objects.Developer;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperDaoJDBC implements DeveloperDao {
    private String pathToTheQuery = "src/main/resources/queries/";

    @Override
    public Developer selectById(int id) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM developers WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Developer developer = new Developer(resultSet.getInt("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getInt("age"),
                resultSet.getString("sex"),
                resultSet.getDouble("salary"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return developer;
    }

    @Override
    public List<Developer> selectAll() throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM developers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Developer> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new Developer(resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age"),
                    resultSet.getString("sex"),
                    resultSet.getDouble("salary")));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return result;
    }

    @Override
    public void deleteById(int id) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "DELETE FROM developers WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void insert(Developer object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "INSERT INTO developers(id, firstName, lastName, age, sex, salary) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, object.getId());
        preparedStatement.setString(2, object.getFirstName());
        preparedStatement.setString(3, object.getLastName());
        preparedStatement.setInt(4, object.getAge());
        preparedStatement.setString(5, object.getSex());
        preparedStatement.setDouble(6, object.getSalary());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(Developer object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "UPDATE developers SET firstName = ?, lastName = ?, age = ?, sex = ?, salary = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, object.getFirstName());
        preparedStatement.setString(2, object.getLastName());
        preparedStatement.setInt(3, object.getAge());
        preparedStatement.setString(4, object.getSex());
        preparedStatement.setDouble(5, object.getSalary());
        preparedStatement.setInt(6, object.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    // The method returns all developers working on the same project
    public List<Developer> selectAllByProjectName(String projectName) throws SQLException {
        return selectAllByCondition(projectName, pathToTheQuery + "developersByProjectName.sql");
    }

    // The method returns all developers by skill level
    public List<Developer> selectAllBySkillLevel(String skillLevel) throws SQLException {
        return selectAllByCondition(skillLevel, pathToTheQuery + "developersBySkillLevel.sql");
    }

    // The method returns all developers by branch of development
    public List<Developer> selectAllByBranchDevelopment(String branchDevelopment) throws SQLException {
        return selectAllByCondition(branchDevelopment, pathToTheQuery + "developersByBranchDevelopment.sql");
    }

    // The method returns salary of all developers of a separate project
    public double getSalaryOfDevelopersOnProject(String projectName) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = null;
        try {
            sql = new Scanner(new File(pathToTheQuery + "salaryByProjectName.sql"))
                    .useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, projectName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        double salary = resultSet.getDouble(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return salary;
    }

    private List<Developer> selectAllByCondition(String conditionalField, String pathToSql) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = null;
        try {
            sql = new Scanner(new File(pathToSql)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, conditionalField);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Developer> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new Developer(resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age"),
                    resultSet.getString("sex"),
                    resultSet.getDouble("salary")));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return result;
    }
}
