package by.grodno.zagart.java.dao.impl;

import by.grodno.zagart.java.dao.GenericDao;
import by.grodno.zagart.java.entities.Product;
import org.hibernate.Criteria;

import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getCurrentSession;

/**
 * Created by Zagart on 11.07.2016.
 */
public class ProductDaoImpl implements GenericDao<Product, Long> {

    public Long save(Product obj) {
        getCurrentSession().save(obj);
        return obj.getId();
    }

    public void update(Product obj) {
        getCurrentSession().update(obj);
    }

    public List<Product> getAll() {
        Criteria allRecords = getCurrentSession().createCriteria(Product.class);
        return allRecords.list();
    }

    public List<Product> getByCriteria(Criteria criteria) {
        return criteria.list();
    }

    public Product getById(Long id) {
        return getCurrentSession().load(Product.class, id);
    }

    public void delete(Long id) {
        Product product = getCurrentSession().load(Product.class, id);
        getCurrentSession().delete(product);
    }

    public void delete(Product persistentObject) {
        getCurrentSession().delete(persistentObject);
    }

}
