package com.mitrais.jee.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * This interface provides generic CRUD operation
 *
 * @param <T>
 * @param <ID>
 */
public interface Dao<T, ID> {
    /**
     * Find single record by ID
     * @param id id
     * @return single record by id
     * @throws SQLException
     */
    Optional<T> find(ID id) throws SQLException;

    /**
     * Find all record
     * @return list of records
     * @throws SQLException
     */
    List<T> findAll() throws SQLException;

    /**
     * Save record to database
     * @param o record
     * @return true if success
     * @throws SQLException
     */
    boolean save(T o) throws SQLException;

    /**
     * Update existing record to database
     * @param o record
     * @return true if success
     * @throws SQLException
     */
    boolean update(T o) throws SQLException;

    /**
     * Remove existing record from database
     * @param o record
     * @return true if success
     * @throws SQLException
     */
    boolean delete(T o) throws SQLException;
}
