package io.hardingadonis.r7.dao;

import io.hardingadonis.r7.model.*;
import io.hardingadonis.r7.model.detail.*;
import java.util.*;

public interface AdminDAO {

    public List<Admin> getAll();

    public Optional<Admin> get(int ID);

    public Optional<Admin> get(String username);

    public void insert(Admin obj);

    public void update(Admin obj);

    public void delete(int ID);
    
    public int count();
    
    public int countByRole(AdminRole role);
}
