package Model.da;

import java.util.List;

public interface DataAccess <T , I> extends AutoCloseable{
    void save(T t) throws Exception;
    void edit(T t) throws Exception;
    void remove(I id) throws Exception;
    List<T> findAll() throws Exception;
    T findById(I id) throws Exception;
}
