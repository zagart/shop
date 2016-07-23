package by.grodno.zagart.java.dao.impl;

import by.grodno.zagart.java.dao.GenericDao;
import by.grodno.zagart.java.entities.Order;
import org.hibernate.Criteria;

import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getCurrentSession;

/**
 * Created by Zagart on 11.07.2016.
 */
public class OrderDaoImpl implements GenericDao<Order, Long> {

    public Long save(Order obj) {
        getCurrentSession().save(obj);
        return obj.getId();
    }

    public void update(Order obj) {
        getCurrentSession().update(obj);
    }

    public List<Order> getAll() {
        Criteria allRecords = getCurrentSession().createCriteria(Order.class);
        return allRecords.list();
    }

    public List<Order> getByCriteria(Criteria criteria) {
        return criteria.list();
    }

    public Order getById(Long id) {
        return getCurrentSession().load(Order.class, id);
    }

    public void delete(Long id) {
        Order order = getCurrentSession().load(Order.class, id);
        getCurrentSession().delete(order);
    }

    public void delete(Order persistentObject) {
        getCurrentSession().delete(persistentObject);
    }

}
