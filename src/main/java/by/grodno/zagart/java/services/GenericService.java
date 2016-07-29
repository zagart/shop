package by.grodno.zagart.java.services;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    Set<PK> getPkSetByQuery(String hql);

    int executeQuery(String hql, Map<String, Object> parameters);

    T getById(final PK id);

    void delete(final PK id);

    void delete(final T obj);

}
