package by.grodno.zagart.java.dao;

import by.grodno.zagart.java.interfaces.IdentifiableEntity;
import by.grodno.zagart.java.interfaces.Loggable;
import by.grodno.zagart.java.interfaces.ReflectiveGeneric;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getCurrentSession;

/**
 * Created by Zagart on 23.07.2016.
 */
public abstract class AbstractHibernateDao
                     <T extends IdentifiableEntity,
                     PK extends Serializable>
                     implements GenericDao<T, PK>, Loggable, ReflectiveGeneric {

    private final T entityObj;

    { entityObj = (T) getGenericObject(0, logger); }

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
