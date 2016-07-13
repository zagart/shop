package by.grodno.zagart.java.Dao.Impl;

import by.grodno.zagart.java.Dao.GenericDao;
import by.grodno.zagart.java.Entities.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import java.util.List;

import static by.grodno.zagart.java.Util.HibernateUtil.getSessionFactory;

/**
 * Created by Zagart on 11.07.2016.
 */
public class ProductDaoImpl implements GenericDao<Product, Long> {

    public Long save(Product obj) {
        Session session = getSessionFactory().openSession();
        session.save(obj);
        return obj.getId();
    }

    public void update(Product obj) {
        Session session = getSessionFactory().openSession();
        session.update(obj);
    }

    public List<Product> getAll() {
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        session.close();
        return list;
    }

    public List<Product> getByCriteria(Criteria criteria) {
        Session session = getSessionFactory().openSession();
        List<Product> list = criteria.list();
        session.close();
        return list;
    }

    public Product getById(Long id) {
        Session session = getSessionFactory().openSession();
        Product product = session.load(Product.class, id);
        return product;
    }

    public void delete(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.load(Product.class, id);
        session.delete(product);
        transaction.commit();
        session.close();
    }

    public void delete(Product persistentObject) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(persistentObject);
        transaction.commit();
        session.close();
    }
}
