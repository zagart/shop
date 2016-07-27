package by.grodno.zagart.java.services;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * Interface that defines methods which necassary
 * for service classes.
 *
 * @param <T>
 * @param <PK>
 */
public interface GenericService<T, PK extends Serializable> {

    PK save(final T obj);

    void update(final T obj);

    List<T> getAll();

    List<T> getByCriterion(Criterion criterion);

    List<T> getByCriterion(List<Criterion> criterions);

    List<T> getListByQuery(String hql);

    T getById(final PK id);

    void delete(final PK id);

    void delete(final T obj);

}
