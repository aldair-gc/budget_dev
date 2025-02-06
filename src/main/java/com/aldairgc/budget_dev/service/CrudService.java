package com.aldairgc.budget_dev.service;

public interface CrudService<ID, T> {
    T create(T dto);

    T update(ID id, T dto);

    void delete(ID id);

    T findById(ID id);
}
