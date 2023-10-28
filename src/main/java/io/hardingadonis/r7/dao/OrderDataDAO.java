package io.hardingadonis.r7.dao;

import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.util.*;

public class OrderDataDAO {

    private static final OrderDataDAO instance = new OrderDataDAO();

    public static OrderDataDAO getInstance() {
        return instance;
    }

    public List<OrderData> getAll(int orderID) {
        List<OrderData> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM order_data WHERE order_id = ?");
            smt.setInt(1, orderID);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("product_id");
                int amount = rs.getInt("amount");

                list.add(new OrderData(orderID, productID, amount));
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void insert(OrderData obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO order_data(order_id, product_id, amount) VALUES (?, ?, ?)");
            smt.setInt(1, obj.getOrderID());
            smt.setInt(2, obj.getProductID());
            smt.setInt(3, obj.getAmount());

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
