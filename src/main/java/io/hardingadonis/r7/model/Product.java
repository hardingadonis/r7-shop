package io.hardingadonis.r7.model;

import java.time.*;
import java.util.*;

public class Product {

    private int ID;
    private String brand;
    private String model;
    private int categoryID;
    private String CPU;
    private String RAM;
    private String VGA;
    private String screenSize;
    private String hardDisk;
    private String OS;
    private long price;
    private int amount;
    private List<String> images;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public Product() {
    }

    public Product(String brand, String model, int categoryID, String CPU, String RAM, String VGA, String screenSize, String hardDisk, String OS, long price, int amount, List<String> images) {
        this.brand = brand;
        this.model = model;
        this.categoryID = categoryID;
        this.CPU = CPU;
        this.RAM = RAM;
        this.VGA = VGA;
        this.screenSize = screenSize;
        this.hardDisk = hardDisk;
        this.OS = OS;
        this.price = price;
        this.amount = amount;
        this.images = images;
    }

    public Product(String brand, String model, int categoryID, String CPU, String RAM, String VGA, String screenSize, String hardDisk, String OS, long price, int amount, List<String> images, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.brand = brand;
        this.model = model;
        this.categoryID = categoryID;
        this.CPU = CPU;
        this.RAM = RAM;
        this.VGA = VGA;
        this.screenSize = screenSize;
        this.hardDisk = hardDisk;
        this.OS = OS;
        this.price = price;
        this.amount = amount;
        this.images = images;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public Product(int ID, String brand, String model, int categoryID, String CPU, String RAM, String VGA, String screenSize, String hardDisk, String OS, long price, int amount, List<String> images, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.ID = ID;
        this.brand = brand;
        this.model = model;
        this.categoryID = categoryID;
        this.CPU = CPU;
        this.RAM = RAM;
        this.VGA = VGA;
        this.screenSize = screenSize;
        this.hardDisk = hardDisk;
        this.OS = OS;
        this.price = price;
        this.amount = amount;
        this.images = images;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getVGA() {
        return VGA;
    }

    public void setVGA(String VGA) {
        this.VGA = VGA;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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
        return "Product{" + "ID=" + ID + ", brand=" + brand + ", model=" + model + ", categoryID=" + categoryID + ", CPU=" + CPU + ", RAM=" + RAM + ", VGA=" + VGA + ", screenSize=" + screenSize + ", hardDisk=" + hardDisk + ", OS=" + OS + ", price=" + price + ", amount=" + amount + ", images=" + images + ", createAt=" + createAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt + '}';
    }
}
