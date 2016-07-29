package by.grodno.zagart.java.dao;

import by.grodno.zagart.java.interfaces.IdentifiableEntity;
import by.grodno.zagart.java.interfaces.Loggable;
import by.grodno.zagart.java.interfaces.Reflective;
import org.hibernate.Criteria;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.*;

import static by.grodno.zagart.java.util.HibernateUtil.getCurrentSession;

/**
 * Abstract class represents DAO layer.
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractHibernateDao
                     <T extends IdentifiableEntity,
                     PK extends Serializable>
                     implements GenericDao<T, PK>, Loggable, Reflective {

    private final T entityObj;

    { entityObj = (T) getGenericObject(0); }

    public PK save(T obj) {
        getCurrentSession().save(obj);
        return (PK) obj.getId();
    }

    @Override
    public void update(T obj) {
        getCurrentSession().update(obj);
    }

    @Override
    public List<T> getAll() {
        Criteria allRecords = getCurrentSession().createCriteria(entityObj.getClass());
        return allRecords.list();
    }

    @Override
    public List<T> getByCriteria(Criteria criteria) {
        return criteria.list();
    }

    @Override
    public List<T> getListByQuery(String hql) {
        return (List<T>) getCurrentSession().createQuery(hql).list();
    }

    @Override
    public Set<PK> getPkSetByQuery(String hql) {
        return new HashSet<>(getCurrentSession().createQuery(hql).list());
    }

    @Override
    public int executeQuery(String hql, Map<String, Object> parameters) {
        Query query = getCurrentSession().createQuery(hql);
        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }
        return query.executeUpdate();
    }

    @Override
    public T getById(PK id) {
        return (T) getCurrentSession().get(entityObj.getClass(), id);
    }

    @Override
    public void delete(PK id) {
        T obj = (T) getCurrentSession().load(entityObj.getClass(), id);
        getCurrentSession().delete(obj);
    }

    @Override
    public void delete(T obj) {
        getCurrentSession().delete(obj);
    }

}
