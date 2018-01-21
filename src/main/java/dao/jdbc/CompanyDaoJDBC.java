package dao.jdbc;

import connection.ConnectorJDBC;
import dao.CompanyDao;
import objects.Company;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoJDBC implements CompanyDao {
    @Override
    public Company selectById(int id) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM companies WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Company company = new Company(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("info"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return company;
    }

    @Override
    public List<Company> selectAll() throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM companies";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Company> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new Company(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("info")));
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
        String sql = "DELETE FROM companies WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void insert(Company object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "INSERT INTO companies(id, name, info) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, object.getId());
        preparedStatement.setString(2, object.getName());
        preparedStatement.setString(3, object.getInfo());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(Company object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "UPDATE companies SET name = ?, info = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getInfo());
        preparedStatement.setInt(3, object.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }
}
