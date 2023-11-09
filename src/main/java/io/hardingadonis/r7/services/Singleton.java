package io.hardingadonis.r7.services;

import io.hardingadonis.r7.dao.*;
import io.hardingadonis.r7.dao.impl.mysql.*;
import io.hardingadonis.r7.services.impl.mysql.*;

public class Singleton {

    public static DBContext context;
    
    public static AdminDAO adminDAO;

    static {
        context = new DBContextMySQLImpl();
        
        adminDAO = new AdminDAOMySQLImpl();
    }
}
