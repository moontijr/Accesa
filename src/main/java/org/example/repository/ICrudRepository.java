package org.example.repository;

public interface ICrudRepository <ID,E> {
    void add (E entity);
    void remove (ID id);
    void update(ID id, E newEntity);
    E findById(ID id);
}
