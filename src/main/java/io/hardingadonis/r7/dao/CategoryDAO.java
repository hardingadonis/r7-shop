package io.hardingadonis.r7.dao;

import io.hardingadonis.r7.model.*;
import java.util.*;

public interface CategoryDAO {

    public List<Category> getAll();
    
    public List<Category> getAllByParentID(int parentID);

    public Optional<Category> get(int ID);

    public void insert(Admin obj);

    public void update(Admin obj);

    public void delete(int ID);

    public int count();
}
