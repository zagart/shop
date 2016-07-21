package by.grodno.zagart.java.dao.impl;

import by.grodno.zagart.java.dao.GenericDao;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.util.Loggable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getSessionFactory;

/**
 * Created by Zagart on 11.07.2016.
 */
public class ProductDaoImpl implements GenericDao<Product, Long>, Loggable {

    public ProductDaoImpl() {

    }

    public Long save(Product obj) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        logger.info("Product saved in database: \n" + obj.toString());
        return obj.getId();
    }

    public void update(Product obj) {
        Session session = getSessionFactory().openSession();
        session.update(obj);
        logger.info("Product updated: \n" + obj.toString());
    }

    public List<Product> getAll() {
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        logger.info("Product saved in database.");
        session.close();
        return list;
    }

    public List<Product> getByCriteria(Criteria criteria) {
        Session session = getSessionFactory().openSession();
        List<Product> list = criteria.list();
        logger.info("Products received by criteria: \n" + criteria.toString());
        session.close();
        return list;
    }

    public Product getById(Long id) {
        Session session = getSessionFactory().openSession();
        Product product = session.load(Product.class, id);
        logger.info("Product received by id: \n" + id);
        return product;
    }

    public void delete(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.load(Product.class, id);
        session.delete(product);
        transaction.commit();
        logger.info("Product deleted by id: \n" + id);
        session.close();
    }

    public void delete(Product persistentObject) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(persistentObject);
        transaction.commit();
        logger.info("Product deleted: \n" + persistentObject.toString());
        session.close();
    }

}
