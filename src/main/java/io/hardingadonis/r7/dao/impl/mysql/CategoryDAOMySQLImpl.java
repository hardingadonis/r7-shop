package io.hardingadonis.r7.dao.impl.mysql;

import io.hardingadonis.r7.dao.*;
import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class CategoryDAOMySQLImpl implements CategoryDAO {

    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM category WHERE delete_at IS NULL");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String name = rs.getString("name");
                String slug = rs.getString("slug");
                int parentID = rs.getInt("parent_id");
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                list.add(new Category(ID, name, slug, parentID, createAt, updateAt, deleteAt));
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<Category> getAllByParentID(int parentID) {
        List<Category> list = new ArrayList<>();

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM category WHERE delete_at IS NULL AND parent_id = ?");
            smt.setInt(1, parentID);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String name = rs.getString("name");
                String slug = rs.getString("slug");
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                list.add(new Category(ID, name, slug, parentID, createAt, updateAt, deleteAt));
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Optional<Category> get(int ID) {
        Category category = null;

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM category WHERE id = ? AND delete_at IS NULL");
            smt.setInt(1, ID);

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String slug = rs.getString("slug");
                int parentID = rs.getInt("parent_id");
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                category = new Category(ID, name, slug, parentID, createAt, updateAt, deleteAt);
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Optional.ofNullable(category);
    }

    @Override
    public void insert(Category obj) {
        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO category(name, slug, parent_id, create_at) VALUES (?, ?, ?, ?)");
            smt.setString(1, obj.getName());
            smt.setString(2, obj.getSlug());
            smt.setInt(3, obj.getParentID());
            smt.setString(4, Converter.convert(obj.getCreateAt()));

            smt.executeUpdate();

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Category obj) {
        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE category SET name = ?, slug = ?, parent_id = ?, update_at = ? WHERE id = ? AND delete_at IS NULL");
            smt.setString(1, obj.getName());
            smt.setString(2, obj.getSlug());
            smt.setInt(3, obj.getParentID());
            smt.setString(4, Converter.convert(LocalDateTime.now()));
            smt.setInt(5, obj.getID());

            smt.executeUpdate();

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int ID) {
        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE category SET delete_at = ? WHERE id = ? AND delete_at IS NULL");
            smt.setString(1, Converter.convert(LocalDateTime.now()));
            smt.setInt(2, ID);

            smt.executeUpdate();

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public int count() {
        int count = 0;

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT COUNT(*) FROM category WHERE delete_at IS NULL");

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return count;
    }
}
