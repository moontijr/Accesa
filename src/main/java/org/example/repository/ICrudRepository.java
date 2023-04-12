package org.example.repository;

/**
 * interface that will be implemented for each Repository
 * @param <ID> for a unique id
 * @param <E> for an entity
 */

public interface ICrudRepository <ID,E> {
    void add (E entity);
    void remove (ID id);
    void update(ID id, E newEntity);
    E findById(ID id);
}
