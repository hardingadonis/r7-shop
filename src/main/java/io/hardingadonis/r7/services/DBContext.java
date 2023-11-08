package io.hardingadonis.r7.services;

import java.sql.*;

public interface DBContext {

    public Connection getConnection();

    public void closeConnection(Connection connection);
}
