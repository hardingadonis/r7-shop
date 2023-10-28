package io.hardingadonis.r7.dao;

import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.util.*;

public class CartDAO {

    private static final CartDAO instance = new CartDAO();

    public static CartDAO getInstance() {
        return instance;
    }

    public List<Cart> getAll(int userID) {
        List<Cart> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM cart WHERE user_id = ?");
            smt.setInt(1, userID);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("product_id");
                int amount = rs.getInt("amount");

                list.add(new Cart(userID, productID, amount));
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void insert(Cart obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO cart(user_id, product_id, amount) VALUES (?, ?, ?)");
            smt.setInt(1, obj.getUserID());
            smt.setInt(2, obj.getProductID());
            smt.setInt(3, obj.getAmount());

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void update(Cart obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE cart SET amount WHERE user_id = ? AND product_id = ?");
            smt.setInt(1, obj.getAmount());
            smt.setInt(2, obj.getUserID());
            smt.setInt(3, obj.getProductID());

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void delete(int userID, int productID) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("DELETE FROM cart WHERE user_id = ? AND product_id = ?");
            smt.setInt(1, userID);
            smt.setInt(2, productID);

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteAll(int userID) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("DELETE FROM cart WHERE user_id = ?");
            smt.setInt(1, userID);

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
