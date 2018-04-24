package com.buzz.test.pattern.other.unitofwork;

public interface IUnitOfWork<T> {

    String INSERT = "INSERT";
    String DELETE = "DELETE";
    String MODIFY = "MODITY";

    void registerNew(T entity);

    void registerModify(T entity);

    void registerDelete(T entity);

    void commit();

}
