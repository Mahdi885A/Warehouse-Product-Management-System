package service;

import java.util.List;
import java.util.Optional;

public interface GenericService <T>{

    Long creat (T t);

    boolean update(T t,Long id);

    boolean delete (Long id);

    Optional<T> findById(Long id);

    List<T> findAll();
}
