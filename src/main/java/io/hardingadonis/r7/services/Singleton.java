package io.hardingadonis.r7.services;

import io.hardingadonis.r7.dao.*;
import io.hardingadonis.r7.dao.impl.mysql.*;
import io.hardingadonis.r7.services.impl.mysql.*;

public class Singleton {

    public static DBContext dbContext;

    public static AdminDAO adminDAO;

    public static CategoryDAO categoryDAO;

    public static ProductDAO productDAO;

    public static UserDAO userDAO;

    static {
        dbContext = new DBContextMySQLImpl();

        adminDAO = new AdminDAOMySQLImpl();

        categoryDAO = new CategoryDAOMySQLImpl();

        productDAO = new ProductDAOMySQLImpl();

        userDAO = new UserDAOMySQLImpl();
    }
}
