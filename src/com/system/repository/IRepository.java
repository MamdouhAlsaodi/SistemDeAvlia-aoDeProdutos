package com.system.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    void save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void delete(Long id);
}
