package by.grodno.zagart.java.dao.impl;

import by.grodno.zagart.java.dao.GenericDao;
import by.grodno.zagart.java.entities.OrderProduct;
import org.hibernate.Criteria;

import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getCurrentSession;

/**
 * Created by Zagart on 11.07.2016.
 */
public class OrderProductDaoImpl implements GenericDao<OrderProduct, Long> {

    public Long save(OrderProduct obj) {
        getCurrentSession().save(obj);
        return obj.getId();
    }

    public void update(OrderProduct obj) {
        getCurrentSession().update(obj);
    }

    public List<OrderProduct> getAll() {
        Criteria allRecords = getCurrentSession().createCriteria(OrderProduct.class);
        return allRecords.list();
    }

    public List<OrderProduct> getByCriteria(Criteria criteria) {
        return criteria.list();
    }

    public OrderProduct getById(Long id) {
        return getCurrentSession().load(OrderProduct.class, id);
    }

    public void delete(Long id) {
        OrderProduct loadedProduct = getCurrentSession().load(OrderProduct.class, id);
        getCurrentSession().delete(loadedProduct);
    }

    public void delete(OrderProduct persistentObject) {
        getCurrentSession().delete(persistentObject);
    }

}
