package com.example.authorizerestfulapi.service;

import java.util.List;
import java.util.Optional;

public abstract class AbsService<T> {
    public abstract List<T> getAll();
    public abstract Optional<T> get(int id);
    public abstract T add(T t);
    public abstract T update(int id, T t);
    public abstract boolean delete(int id);
}
