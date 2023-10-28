package io.hardingadonis.r7.dao;

import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.model.detail.*;
import io.hardingadonis.r7.services.*;
import java.sql.*;
import java.time.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class UserDAO implements DAO<User> {

    private static final UserDAO instance = new UserDAO();

    public static UserDAO getInstance() {
        return instance;
    }

    private static List<UserAddress> toList(String json) {
        List<UserAddress> list = new ArrayList<>();

        try {
            JSONArray arr = (JSONArray) new JSONParser().parse(json);

            for (Object address : arr) {
                String province = (String) ((JSONObject) address).get("province");
                String district = (String) ((JSONObject) address).get("district");
                String ward = (String) ((JSONObject) address).get("ward");
                String specific = (String) ((JSONObject) address).get("specific");

                list.add(new UserAddress(province, district, ward, specific));
            }
        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    private static String toJson(List<UserAddress> list) {
        JSONArray json = new JSONArray();

        for (UserAddress address : list) {
            JSONObject obj = new JSONObject();
            obj.put("province", address.getProvince());
            obj.put("district", address.getDistrict());
            obj.put("ward", address.getWard());
            obj.put("specific", address.getSpecific());

            json.add(obj);
        }

        return json.toJSONString();
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM user WHERE delete_at IS NULL");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String fullName = rs.getString("full_name");
                int birthYear = rs.getInt("birth_year");
                UserGender gender = UserGender.create(rs.getString("gender"));
                String email = rs.getString("email");
                String hashedPassword = rs.getString("hashed_password");
                String avatarPath = rs.getString("avatar_path");
                List<UserAddress> address = toList(rs.getString("address"));
                UserStatus status = UserStatus.create(rs.getString("status"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                list.add(new User(ID, fullName, birthYear, gender, email, hashedPassword, avatarPath, address, status, createAt, updateAt, deleteAt));
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Optional<User> get(int ID) {
        User user = null;

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM user WHERE id = ? AND delete_at IS NULL");
            smt.setInt(1, ID);

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("full_name");
                int birthYear = rs.getInt("birth_year");
                UserGender gender = UserGender.create(rs.getString("gender"));
                String email = rs.getString("email");
                String hashedPassword = rs.getString("hashed_password");
                String avatarPath = rs.getString("avatar_path");
                List<UserAddress> address = toList(rs.getString("address"));
                UserStatus status = UserStatus.create(rs.getString("status"));
                LocalDateTime createAt = Converter.convert(rs.getTimestamp("create_at"));
                LocalDateTime updateAt = Converter.convert(rs.getTimestamp("update_at"));
                LocalDateTime deleteAt = Converter.convert(rs.getTimestamp("delete_at"));

                user = new User(ID, fullName, birthYear, gender, email, hashedPassword, avatarPath, address, status, createAt, updateAt, deleteAt);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Optional.ofNullable(user);
    }

    @Override
    public void insert(User obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO user(full_name, birth_year, gender, email, hashed_password, avatar_path, address, status, create_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            smt.setString(1, obj.getFullName());
            smt.setInt(2, obj.getBirthYear());
            smt.setString(3, obj.getGender().toString());
            smt.setString(4, obj.getEmail());
            smt.setString(5, obj.getHashedPassword());
            smt.setString(6, obj.getAvatarPath());
            smt.setString(7, toJson(obj.getAddress()));
            smt.setString(8, obj.getStatus().toString());
            smt.setString(9, Converter.convert(LocalDateTime.now()));
            
            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(User obj) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE user SET full_name = ?, birth_year = ?, gender = ?, email = ?, hashed_password = ?, avatar_path = ?, address = ?, status = ?, update_at = ? WHERE id = ? AND delete_at IS NULL");
            smt.setString(1, obj.getFullName());
            smt.setInt(2, obj.getBirthYear());
            smt.setString(3, obj.getGender().toString());
            smt.setString(4, obj.getEmail());
            smt.setString(5, obj.getHashedPassword());
            smt.setString(6, obj.getAvatarPath());
            smt.setString(7, toJson(obj.getAddress()));
            smt.setString(8, obj.getStatus().toString());
            smt.setString(9, Converter.convert(LocalDateTime.now()));
            smt.setInt(10, obj.getID());
            
            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int ID) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE user SET delete_at = ? WHERE id = ? AND delete_at IS NULL");
            smt.setString(1, Converter.convert(LocalDateTime.now()));
            smt.setInt(2, ID);

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
