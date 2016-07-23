package by.grodno.zagart.java.services;

import by.grodno.zagart.java.dao.GenericDao1;
import by.grodno.zagart.java.interfaces.IdentifiableEntity;
import by.grodno.zagart.java.entities.Order1;
import by.grodno.zagart.java.interfaces.Loggable;
import by.grodno.zagart.java.interfaces.ReflectiveGeneric;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil1.*;

/**
 * Created by Zagart on 22.07.2016.
 */
public abstract class AbstractHibernateService
                    <T extends IdentifiableEntity,
                    PK extends Serializable,
                    DAO extends GenericDao1>
                    implements GenericService<T, PK>, Loggable, ReflectiveGeneric {

    private GenericDao1 dao = (GenericDao1) getGenericObject(2, logger);
    private final T entityObj;

    { entityObj = (T) getGenericObject(0, logger); }

    @Override
    public PK save(T obj) {
        openCurrentSessionWithTransaction();
        dao.save(obj);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object saved with id = %d.",
                entityObj.getEntityName(),
                obj.getId()));
        return (PK) obj.getId();
    }

    @Override
    public void update(T obj) {
        openCurrentSessionWithTransaction();
        dao.update(obj);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object with id = %d updated.",
                entityObj.getEntityName(),
                obj.getId()));
    }

    @Override
    public List<T> getAll() {
        openCurrentSession();
        List<T> daoAll = dao.getAll();
        closeCurrentSession();
        logger.info(String.format("All %s objects pulled from database(%d).",
                entityObj.getEntityName(),
                daoAll.size()));
        return daoAll;
    }

    @Override
    public List<T> getByCriterion(Criterion criterion) {
        openCurrentSession();
        Criteria criteria = getCurrentSession().createCriteria(entityObj.getClass()).add(criterion);
        List<Order1> daoByCriteria = dao.getByCriteria(criteria);
        closeCurrentSession();
        logger.info(String.format("%s objects pulled from database by criterion(%d).",
                entityObj.getEntityName(),
                daoByCriteria.size()));
        return (List<T>) daoByCriteria;
    }

    @Override
    public List<T> getByCriterion(List<Criterion> criterions) {
        openCurrentSession();
        Criteria criteria = getCurrentSession().createCriteria(entityObj.getClass());
        criterions.forEach(criteria::add);
        List<Order1> daoByCriteria = dao.getByCriteria(criteria);
        closeCurrentSession();
        logger.info(String.format("%s objects pulled from database by criterions(%d).",
                entityObj.getEntityName(),
                daoByCriteria.size()));
        return (List<T>) daoByCriteria;
    }

    @Override
    public T getById(PK id) {
        openCurrentSession();
        T obj = (T) dao.getById(id);
        closeCurrentSession();
        logger.info(String.format("%s object pulled from database by id = %d.",
                entityObj.getEntityName(),
                obj.getId()));
        return obj;
    }

    @Override
    public void delete(PK id) {
        openCurrentSessionWithTransaction();
        dao.delete(id);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object deleted from database by id = %d.", entityObj.getEntityName(), id));
    }

    @Override
    public void delete(T obj) {
        openCurrentSessionWithTransaction();
        dao.delete(obj);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object with id = %d deleted from database.",
                entityObj.getEntityName(),
                obj.getId()));
    }

}