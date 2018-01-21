package dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    T selectById(int id) throws SQLException;

    List<T> selectAll() throws SQLException;

    void deleteById(int id) throws SQLException;

    void insert(T object) throws SQLException;

    void update(T object) throws SQLException;
}
