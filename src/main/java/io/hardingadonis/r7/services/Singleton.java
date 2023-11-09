package io.hardingadonis.r7.services;

import io.hardingadonis.r7.dao.*;
import io.hardingadonis.r7.dao.impl.mysql.*;
import io.hardingadonis.r7.services.impl.mysql.*;

public class Singleton {

    public static DBContext dbContext;

    public static AdminDAO adminDAO;

    public static CartDAO cartDAO;

    public static CategoryDAO categoryDAO;
    
    public static OrderDAO orderDAO;

    public static OrderDataDAO orderDataDAO;

    public static ProductDAO productDAO;

    public static UserDAO userDAO;

    static {
        dbContext = new DBContextMySQLImpl();

        adminDAO = new AdminDAOMySQLImpl();

        cartDAO = new CartDAOMySQLImpl();

        categoryDAO = new CategoryDAOMySQLImpl();
        
        orderDAO = new OrderDAOMySQLImpl();

        orderDataDAO = new OrderDataDAOMySQLImpl();

        productDAO = new ProductDAOMySQLImpl();

        userDAO = new UserDAOMySQLImpl();
    }
}
