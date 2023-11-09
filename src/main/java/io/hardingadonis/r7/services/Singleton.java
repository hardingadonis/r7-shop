package io.hardingadonis.r7.services;

import io.hardingadonis.r7.dao.*;
import io.hardingadonis.r7.dao.impl.mysql.*;
import io.hardingadonis.r7.services.impl.mysql.*;

public class Singleton {

    public static DBContext dbContext;

    public static AdminDAO adminDAO;

    public static UserDAO userDAO;

    static {
        dbContext = new DBContextMySQLImpl();

        adminDAO = new AdminDAOMySQLImpl();

        userDAO = new UserDAOMySQLImpl();
    }
}
