package com.tercertotest.controller.dao.implement;

import com.tercertotest.controller.tda.LinkedList;

public interface InterfazDao<T> {
    public void persist(T obj) throws Exception;
    public void merge(T obj, Integer index) throws Exception;
    public LinkedList listAll();
    public T get(Integer id) throws Exception;
    public void delete(Integer id) throws Exception;
}