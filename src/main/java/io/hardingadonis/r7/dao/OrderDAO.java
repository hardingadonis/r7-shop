package io.hardingadonis.r7.dao;

import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.model.detail.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class OrderDAO implements DAO<Order> {

    private static final OrderDAO instance = new OrderDAO();

    public static OrderDAO getInstance() {
        return instance;
    }

    @Override
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM order");

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                int ID = rs.getInt("id");
                int userID = rs.getInt("user_id");
                long totalPrice = rs.getInt("total_price");
                Payment payment = Payment.create(rs.getString("payment"));
                OrderStatus status = OrderStatus.create(rs.getString("status"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));

                list.add(new Order(ID, userID, totalPrice, payment, status, createAt, updateAt));
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Optional<Order> get(int ID) {
        Order order = null;

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM order WHERE id = ?");
            smt.setInt(1, ID);

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("user_id");
                long totalPrice = rs.getInt("total_price");
                Payment payment = Payment.create(rs.getString("payment"));
                OrderStatus status = OrderStatus.create(rs.getString("status"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));

                order = new Order(ID, userID, totalPrice, payment, status, createAt, updateAt);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Optional.ofNullable(order);
    }

    @Override
    public void insert(Order obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO order(user_id, total_price, payment, status, create_at) VALUES (?, ?, ?, ?, ?)");
            smt.setInt(1, obj.getUserID());
            smt.setLong(2, obj.getTotalPrice());
            smt.setString(3, obj.getPayment().toString());
            smt.setString(4, obj.getStatus().toString());
            smt.setString(5, Converter.convert(LocalDateTime.now()));

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Order obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE order SET user_id = ?, total_price = ?, payment = ?, status = ?, update_at = ? WHERE id = ?");
            smt.setInt(1, obj.getUserID());
            smt.setLong(2, obj.getTotalPrice());
            smt.setString(3, obj.getPayment().toString());
            smt.setString(4, obj.getStatus().toString());
            smt.setString(5, Converter.convert(LocalDateTime.now()));
            smt.setInt(6, obj.getID());

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int ID) {
        // Do nothing
    }
}
