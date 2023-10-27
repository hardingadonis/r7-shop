package io.hardingadonis.r7.dao;

import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.model.detail.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class AdminDAO implements DAO<Admin> {

    private static final AdminDAO instance = new AdminDAO();

    public static AdminDAO getInstance() {
        return instance;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM admin");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String username = rs.getString("username");
                String hashedPassword = rs.getString("hashed_password");
                AdminRole role = AdminRole.create(rs.getString("role"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));

                list.add(new Admin(ID, username, hashedPassword, role, createAt, updateAt));
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Optional<Admin> get(int ID) {
        Admin admin = null;

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM admin WHERE id = ?");
            smt.setInt(1, ID);

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String hashedPassword = rs.getString("hashed_password");
                AdminRole role = AdminRole.create(rs.getString("role"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));

                admin = new Admin(ID, username, hashedPassword, role, createAt, updateAt);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
        }

        return Optional.ofNullable(admin);
    }

    @Override
    public void insert(Admin obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO admin(username, hashed_password, role, create_at, update_at) VALUES (?, ?, ?, ?, ?)");
            smt.setString(1, obj.getUsername());
            smt.setString(2, obj.getHashedPassword());
            smt.setString(3, obj.getRole().toString());
            smt.setString(4, Converter.convert(obj.getCreateAt()));
            smt.setString(5, Converter.convert(obj.getUpdateAt()));

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Admin obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE admin SET username = ?, hashed_password = ?, role = ?, create_at = ?, update_at = ? WHERE id = ?");
            smt.setString(1, obj.getUsername());
            smt.setString(2, obj.getHashedPassword());
            smt.setString(3, obj.getRole().toString());
            smt.setString(4, Converter.convert(obj.getCreateAt()));
            smt.setString(5, Converter.convert(obj.getUpdateAt()));
            smt.setInt(6, obj.getID());
            
            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int ID) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("DELETE from admin WHERE id = ?");
            smt.setInt(1, ID);

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
