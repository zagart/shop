package by.grodno.zagart.java.dao;

import by.grodno.zagart.java.interfaces.Identifiable;
import by.grodno.zagart.java.interfaces.Loggable;
import by.grodno.zagart.java.interfaces.Reflective;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static by.grodno.zagart.java.util.HibernateUtil.getCurrentSession;

/**
 * Abstract class represents DAO layer.
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractHibernateDao
                     <T extends Identifiable,
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
        String hql = String.format("select target from %s target", entityObj.getClass().getName());
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
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
        query.setProperties(parameters);
        return query.executeUpdate();
    }

    @Override
    public T getById(PK id) {
        String hql = String.format("select target from %s target where id = :targetId", entityObj.getClass().getName());
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("targetId", id);
        return (T) query.uniqueResult();
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
