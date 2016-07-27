package by.grodno.zagart.java.dao;

import by.grodno.zagart.java.interfaces.IdentifiableEntity;
import by.grodno.zagart.java.interfaces.Loggable;
import by.grodno.zagart.java.interfaces.Reflective;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getCurrentSession;

/**
 * Abstract class represents DAO layer.
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
        getCurrentSession().persist(obj);
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
        return (List<T>) getCurrentSession().createQuery(hql).getResultList();
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
