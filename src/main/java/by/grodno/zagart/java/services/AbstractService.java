package by.grodno.zagart.java.services;

import by.grodno.zagart.java.dao.GenericDao;
import by.grodno.zagart.java.entities.IdentifiableEntity;
import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.services.GenericService;
import by.grodno.zagart.java.util.Loggable;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.*;

/**
 * Created by Zagart on 22.07.2016.
 */
public abstract class AbstractService
                    <T extends IdentifiableEntity,
                    PK extends Serializable,
                    DAO extends GenericDao>

                    implements GenericService<T, PK>, Loggable {

    private GenericDao dao = (GenericDao) getGenericObject(2);
    private final T entityObj;

    { entityObj = (T) getGenericObject(0); }

    public AbstractService() {
    }

    private Object getGenericObject(int position) {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> daoClass = (Class<?>) parameterizedType.getActualTypeArguments()[position];
        Constructor<?> constructor = daoClass.getConstructors()[0];
        Object object = null;
        try {
            object = constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public PK save(T obj) {
        openCurrentSessionWithTransaction();
        dao.save(obj);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object saved with id = %d.",
                entityObj.getEntityName(),
                obj.getId()));
        return (PK) obj.getId();
    }

    public void update(T obj) {
        openCurrentSessionWithTransaction();
        dao.update(obj);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object with id = %d updated.",
                entityObj.getEntityName(),
                obj.getId()));
    }

    public List<T> getAll() {
        openCurrentSession();
        List<T> daoAll = dao.getAll();
        closeCurrentSession();
        logger.info(String.format("All %s objects pulled from database(%d).",
                entityObj.getEntityName(),
                daoAll.size()));
        return daoAll;
    }

    public List<T> getByCriterion(Criterion criterion) {
        openCurrentSession();
        Criteria criteria = getCurrentSession().createCriteria(entityObj.getClass()).add(criterion);
        List<Order> daoByCriteria = dao.getByCriteria(criteria);
        closeCurrentSession();
        logger.info(String.format("%s objects pulled from database by criterion(%d).",
                entityObj.getEntityName(),
                daoByCriteria.size()));
        return (List<T>) daoByCriteria;
    }

    public List<T> getByCriterion(List<Criterion> criterions) {
        openCurrentSession();
        Criteria criteria = getCurrentSession().createCriteria(entityObj.getClass());
        criterions.forEach(criteria::add);
        List<Order> daoByCriteria = dao.getByCriteria(criteria);
        closeCurrentSession();
        logger.info(String.format("%s objects pulled from database by criterions(%d).",
                entityObj.getEntityName(),
                daoByCriteria.size()));
        return (List<T>) daoByCriteria;
    }

    public T getById(PK id) {
        openCurrentSession();
        T obj = (T) dao.getById(id);
        closeCurrentSession();
        logger.info(String.format("%s object pulled from database by id = %d.",
                entityObj.getEntityName(),
                obj.getId()));
        return obj;
    }

    public void delete(PK id) {
        openCurrentSessionWithTransaction();
        dao.delete(id);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object deleted from database by id = %d.", entityObj.getEntityName(), id));
    }

    public void delete(T obj) {
        openCurrentSessionWithTransaction();
        dao.delete(obj);
        closeCurrentSessionWithTransaction();
        logger.info(String.format("%s object with id = %d deleted from database.",
                entityObj.getEntityName(),
                obj.getId()));
    }
}
