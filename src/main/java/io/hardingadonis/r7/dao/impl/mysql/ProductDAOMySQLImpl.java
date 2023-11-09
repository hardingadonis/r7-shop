package io.hardingadonis.r7.dao.impl.mysql;

import io.hardingadonis.r7.dao.*;
import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.time.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ProductDAOMySQLImpl implements ProductDAO {

    private static List<String> toList(String json) {
        List<String> list = new ArrayList<>();

        try {
            JSONArray arr = (JSONArray) new JSONParser().parse(json);
            for (Object img : arr) {
                list.add((String) img);
            }
        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    private static String toJson(List<String> list) {
        JSONArray json = new JSONArray();

        for (String img : list) {
            json.add(img);
        }

        return json.toJSONString();
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM product WHERE delete_at IS NULL");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int categoryID = rs.getInt("category_id");
                String CPU = rs.getString("cpu");
                String RAM = rs.getString("ram");
                String VGA = rs.getString("vga");
                String screenSize = rs.getString("screen_size");
                String hardDisk = rs.getString("hard_disk");
                String OS = rs.getString("os");
                long price = rs.getLong("price");
                int amount = rs.getInt("amount");
                List<String> images = toList(rs.getString("images"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                list.add(new Product(ID, brand, model, categoryID, CPU, RAM, VGA, screenSize, hardDisk, OS, price, amount, images, createAt, updateAt, deleteAt));
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<Product> getAllByCategoryID(int categoryID) {
        List<Product> list = new ArrayList<>();

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM product WHERE delete_at IS NULL AND category_id = ?");
            smt.setInt(1, categoryID);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                String CPU = rs.getString("cpu");
                String RAM = rs.getString("ram");
                String VGA = rs.getString("vga");
                String screenSize = rs.getString("screen_size");
                String hardDisk = rs.getString("hard_disk");
                String OS = rs.getString("os");
                long price = rs.getLong("price");
                int amount = rs.getInt("amount");
                List<String> images = toList(rs.getString("images"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                list.add(new Product(ID, brand, model, categoryID, CPU, RAM, VGA, screenSize, hardDisk, OS, price, amount, images, createAt, updateAt, deleteAt));
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<Product> getAllByPrice(int min, int max) {
        List<Product> list = new ArrayList<>();

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM product WHERE delete_at IS NULL AND price BETWEEN ? AND ?");
            smt.setInt(1, min);
            smt.setInt(2, max);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int categoryID = rs.getInt("category_id");
                String CPU = rs.getString("cpu");
                String RAM = rs.getString("ram");
                String VGA = rs.getString("vga");
                String screenSize = rs.getString("screen_size");
                String hardDisk = rs.getString("hard_disk");
                String OS = rs.getString("os");
                long price = rs.getLong("price");
                int amount = rs.getInt("amount");
                List<String> images = toList(rs.getString("images"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                list.add(new Product(ID, brand, model, categoryID, CPU, RAM, VGA, screenSize, hardDisk, OS, price, amount, images, createAt, updateAt, deleteAt));
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Optional<Product> get(int ID) {
        Product product = null;

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM product WHERE id = ? AND delete_at IS NULL");
            smt.setInt(1, ID);

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int categoryID = rs.getInt("category_id");
                String CPU = rs.getString("cpu");
                String RAM = rs.getString("ram");
                String VGA = rs.getString("vga");
                String screenSize = rs.getString("screen_size");
                String hardDisk = rs.getString("hard_disk");
                String OS = rs.getString("os");
                long price = rs.getLong("price");
                int amount = rs.getInt("amount");
                List<String> images = toList(rs.getString("images"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                product = new Product(ID, brand, model, categoryID, CPU, RAM, VGA, screenSize, hardDisk, OS, price, amount, images, createAt, updateAt, deleteAt);
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Optional.ofNullable(product);
    }

    @Override
    public void insert(Product obj) {
        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO product(brand, model, category_id, cpu, ram, vga, screen_size, hard_disk, os, price, amount, images, create_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            smt.setString(1, obj.getBrand());
            smt.setString(2, obj.getModel());
            smt.setInt(3, obj.getCategoryID());
            smt.setString(4, obj.getCPU());
            smt.setString(5, obj.getRAM());
            smt.setString(6, obj.getVGA());
            smt.setString(7, obj.getScreenSize());
            smt.setString(8, obj.getHardDisk());
            smt.setString(9, obj.getOS());
            smt.setLong(10, obj.getPrice());
            smt.setInt(11, obj.getAmount());
            smt.setString(12, toJson(obj.getImages()));
            smt.setString(13, Converter.convert(LocalDateTime.now()));

            smt.executeUpdate();

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Product obj) {
        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE product SET brand = ?, model = ?, category_id = ?, cpu = ?, ram = ?, vga = ?, screen_size = ?, hard_disk = ?, os = ?, price = ?, amount = ?, images = ?, update_at = ? WHERE id = ? AND delete_at IS NULL");
            smt.setString(1, obj.getBrand());
            smt.setString(2, obj.getModel());
            smt.setInt(3, obj.getCategoryID());
            smt.setString(4, obj.getCPU());
            smt.setString(5, obj.getRAM());
            smt.setString(6, obj.getVGA());
            smt.setString(7, obj.getScreenSize());
            smt.setString(8, obj.getHardDisk());
            smt.setString(9, obj.getOS());
            smt.setLong(10, obj.getPrice());
            smt.setInt(11, obj.getAmount());
            smt.setString(12, toJson(obj.getImages()));
            smt.setString(13, Converter.convert(LocalDateTime.now()));
            smt.setInt(14, obj.getID());

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

            PreparedStatement smt = conn.prepareStatement("UPDATE product SET delete_at = ? WHERE id = ? AND delete_at IS NULL");
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

            PreparedStatement smt = conn.prepareStatement("SELECT COUNT(*) FROM product WHERE delete_at IS NULL");

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

    @Override
    public int countByCategoryID(int categoryID) {
        int count = 0;

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT COUNT(*) FROM product WHERE delete_at IS NULL AND category_id = ?");
            smt.setInt(1, categoryID);

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

    @Override
    public int countByPrice(int min, int max) {
        int count = 0;

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT COUNT(*) FROM product WHERE delete_at IS NULL AND price BETWEEN ? AND ?");
            smt.setInt(1, min);
            smt.setInt(2, max);

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
