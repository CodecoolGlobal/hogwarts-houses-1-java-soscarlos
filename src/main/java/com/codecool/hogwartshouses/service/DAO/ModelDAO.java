package com.codecool.hogwartshouses.service.DAO;

import java.util.Optional;
import java.util.Set;

public interface ModelDAO<T> {

    Optional<T> get(long id);

    Set<T> getAll();

    void save(T t);

    Optional<T> update(T t, long id);

    boolean delete(long id);
}
