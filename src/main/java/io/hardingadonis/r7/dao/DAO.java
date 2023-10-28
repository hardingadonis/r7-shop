package io.hardingadonis.r7.dao;

import java.util.*;

public interface DAO<T> {

    public List<T> getAll();

    public Optional<T> get(int ID);

    public void insert(T obj);

    public void update(T obj);

    public void delete(int ID);
}
