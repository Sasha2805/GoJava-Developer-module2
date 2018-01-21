package dao.jdbc;

import connection.ConnectorJDBC;
import dao.CustomerDao;
import objects.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoJDBC implements CustomerDao {
    @Override
    public Customer selectById(int id) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM customers WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Customer customer = new Customer(resultSet.getInt("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("Info"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return customer;
    }

    @Override
    public List<Customer> selectAll() throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM customers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Customer> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new Customer(resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("Info")));
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
        String sql = "DELETE FROM customers WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void insert(Customer object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "INSERT INTO customers(id, firstName, lastName, info) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, object.getId());
        preparedStatement.setString(2, object.getFirstName());
        preparedStatement.setString(3, object.getLastName());
        preparedStatement.setString(4, object.getInfo());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(Customer object) throws SQLException {
        Connection connection = ConnectorJDBC.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "UPDATE customers SET firstName = ?, lastName = ?, info = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, object.getFirstName());
        preparedStatement.setString(2, object.getLastName());
        preparedStatement.setString(3, object.getInfo());
        preparedStatement.setInt(4, object.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }
}
