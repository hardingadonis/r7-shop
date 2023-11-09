package io.hardingadonis.r7.dao.impl.mysql;

import io.hardingadonis.r7.dao.*;
import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.model.detail.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class AdminDAOMySQLImpl implements AdminDAO {
    
    @Override
    public List<Admin> getAll() {
        List<Admin> list = new ArrayList<>();
        
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("SELECT * FROM admin WHERE delete_at IS NULL");
            
            ResultSet rs = smt.executeQuery();
            
            while (rs.next()) {
                int ID = rs.getInt("id");
                String username = rs.getString("username");
                String hashedPassword = rs.getString("hashed_password");
                AdminRole role = AdminRole.create(rs.getString("role"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));
                
                list.add(new Admin(ID, username, hashedPassword, role, createAt, updateAt, deleteAt));
            }
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public List<Admin> getAllByRole(AdminRole role) {
        List<Admin> list = new ArrayList<>();
        
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("SELECT * FROM admin WHERE delete_at IS NULL AND role = ?");
            smt.setString(1, role.toString());
            
            ResultSet rs = smt.executeQuery();
            
            while (rs.next()) {
                int ID = rs.getInt("id");
                String username = rs.getString("username");
                String hashedPassword = rs.getString("hashed_password");
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));
                
                list.add(new Admin(ID, username, hashedPassword, role, createAt, updateAt, deleteAt));
            }
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public Optional<Admin> get(int ID) {
        Admin admin = null;
        
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("SELECT * FROM admin WHERE id = ? AND delete_at IS NULL");
            smt.setInt(1, ID);
            
            ResultSet rs = smt.executeQuery();
            
            if (rs.next()) {
                String username = rs.getString("username");
                String hashedPassword = rs.getString("hashed_password");
                AdminRole role = AdminRole.create(rs.getString("role"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));
                
                admin = new Admin(ID, username, hashedPassword, role, createAt, updateAt, deleteAt);
            }
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return Optional.ofNullable(admin);
    }
    
    @Override
    public Optional<Admin> get(String username) {
        Admin admin = null;
        
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND delete_at IS NULL");
            smt.setString(1, username);
            
            ResultSet rs = smt.executeQuery();
            
            if (rs.next()) {
                int ID = rs.getInt("id");
                String hashedPassword = rs.getString("hashed_password");
                AdminRole role = AdminRole.create(rs.getString("role"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));
                
                admin = new Admin(ID, username, hashedPassword, role, createAt, updateAt, deleteAt);
            }
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return Optional.ofNullable(admin);
    }
    
    @Override
    public void insert(Admin obj) {
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("INSERT INTO admin(username, hashed_password, role, create_at) VALUES (?, ?, ?, ?)");
            smt.setString(1, obj.getUsername());
            smt.setString(2, obj.getHashedPassword());
            smt.setString(3, obj.getRole().toString());
            smt.setString(4, Converter.convert(LocalDateTime.now()));
            
            smt.executeUpdate();
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void update(Admin obj) {
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("UPDATE admin SET username = ?, hashed_password = ?, role = ?, update_at = ? WHERE id = ? AND delete_at IS NULL");
            smt.setString(1, obj.getUsername());
            smt.setString(2, obj.getHashedPassword());
            smt.setString(3, obj.getRole().toString());
            smt.setString(4, Converter.convert(LocalDateTime.now()));
            smt.setInt(5, obj.getID());
            
            smt.executeUpdate();
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int ID) {
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("UPDATE admin SET delete_at = ? WHERE id = ? AND delete_at IS NULL");
            smt.setString(1, Converter.convert(LocalDateTime.now()));
            smt.setInt(2, ID);
            
            smt.executeUpdate();
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public int count() {
        int count = 0;
        
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("SELECT COUNT(*) FROM admin WHERE delete_at IS NULL");
            
            ResultSet rs = smt.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return count;
    }
    
    @Override
    public int countByRole(AdminRole role) {
        int count = 0;
        
        try {
            Connection conn = Singleton.context.getConnection();
            
            PreparedStatement smt = conn.prepareStatement("SELECT COUNT(*) FROM admin WHERE delete_at IS NULL AND role = ?");
            smt.setString(1, role.toString());
            
            ResultSet rs = smt.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
            Singleton.context.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return count;
    }
}
