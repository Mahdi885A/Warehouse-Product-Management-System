package repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {

    Long save(T t);

    boolean update(T t,Long id);

    boolean delete(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();

}
