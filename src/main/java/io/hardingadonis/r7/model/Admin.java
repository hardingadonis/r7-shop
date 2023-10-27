package io.hardingadonis.r7.model;

import io.hardingadonis.r7.model.detail.*;
import java.time.*;

public class Admin {

    private int ID;
    private String username;
    private String hashedPassword;
    private AdminRole role;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Admin() {
    }

    public Admin(String username, String hashedPassword, AdminRole role) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public Admin(String username, String hashedPassword, AdminRole role, LocalDateTime createAt, LocalDateTime updateAt) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Admin(int ID, String username, String hashedPassword, AdminRole role, LocalDateTime createAt, LocalDateTime updateAt) {
        this.ID = ID;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Admin{" + "ID=" + ID + ", username=" + username + ", hashedPassword=" + hashedPassword + ", role=" + role + ", createAt=" + createAt + ", updateAt=" + updateAt + '}';
    }
}
