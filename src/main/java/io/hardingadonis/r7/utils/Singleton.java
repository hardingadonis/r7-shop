package io.hardingadonis.r7.utils;

import io.hardingadonis.r7.services.*;
import io.hardingadonis.r7.services.impl.mysql.*;

public class Singleton {

    public static DBContext context;

    static {
        context = new DBContextMySQLImpl();
    }
}
