package dao.jdbc;

import connection.ConnectorJDBC;
import dao.SkillDao;
import objects.Skill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoJDBC implements SkillDao {
    @Override
    public Skill selectById(int id) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM skills WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Skill skill = new Skill(resultSet.getInt("id"),
                resultSet.getString("branchDevelopment"),
                resultSet.getString("skillLevel"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return skill;
    }

    @Override
    public List<Skill> selectAll() throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM skills";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Skill> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new Skill(resultSet.getInt("id"),
                    resultSet.getString("branchDevelopment"),
                    resultSet.getString("skillLevel")));
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
        String sql = "DELETE FROM skills WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void insert(Skill object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "INSERT INTO developers(id, branchDevelopment, skillLevel) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, object.getId());
        preparedStatement.setString(2, object.getBranchDevelopment());
        preparedStatement.setString(3, object.getSkillLevel());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(Skill object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "UPDATE developers SET branchDevelopment = ?, skillLevel = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, object.getBranchDevelopment());
        preparedStatement.setString(2, object.getSkillLevel());
        preparedStatement.setInt(3, object.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }
}
