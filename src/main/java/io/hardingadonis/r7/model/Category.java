package io.hardingadonis.r7.model;

import java.time.*;

public class Category {
    private int ID;
    private String name;
    private String slug;
    private int parentID;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public Category() {
    }

    public Category(String name, String slug, int parentID) {
        this.name = name;
        this.slug = slug;
        this.parentID = parentID;
    }

    public Category(String name, String slug, int parentID, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.name = name;
        this.slug = slug;
        this.parentID = parentID;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public Category(int ID, String name, String slug, int parentID, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.ID = ID;
        this.name = name;
        this.slug = slug;
        this.parentID = parentID;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
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

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "Category{" + "ID=" + ID + ", name=" + name + ", slug=" + slug + ", parentID=" + parentID + ", createAt=" + createAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt + '}';
    }
}