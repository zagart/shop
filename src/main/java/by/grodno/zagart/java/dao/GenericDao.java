package by.grodno.zagart.java.dao;

import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    PK save(final T obj);

    void update(final T obj);

    List<T> getAll();

    List<T> getByCriteria(Criteria criteria);

    List<T> getListByQuery(String hql);

    T getById(final PK id);

    void delete(final PK id);

    void delete(final T obj);

}
