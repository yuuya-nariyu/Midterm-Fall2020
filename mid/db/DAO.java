package mid.db;

import java.util.List;

public interface DAO<T> {
    List<T> getAll() throws DAOException;
    boolean add(T t) throws DAOException;
    boolean delete(T t) throws DAOException;
    boolean update(T t) throws DAOException;
    T get(String t) throws DAOException;
}
