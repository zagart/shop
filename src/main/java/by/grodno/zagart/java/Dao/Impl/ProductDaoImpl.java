package by.grodno.zagart.java.Dao.Impl;

import by.grodno.zagart.java.Dao.GenericDao;
import by.grodno.zagart.java.Entities.Product;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static by.grodno.zagart.java.Util.HibernateUtil.getSessionFactory;

/**
 * Created by Zagart on 11.07.2016.
 */
public class ProductDaoImpl implements GenericDao<Product, Long> {

    final private static Logger LOGGER = Logger.getLogger(Logger.class);

    public Long save(Product obj) {
        Session session = getSessionFactory().openSession();
        session.save(obj);
        LOGGER.info("Product saved in database: \n" + obj.toString());
        return obj.getId();
    }

    public void update(Product obj) {
        Session session = getSessionFactory().openSession();
        session.update(obj);
        LOGGER.info("Product updated: \n" + obj.toString());
    }

    public List<Product> getAll() {
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        LOGGER.info("Product saved in database.");
        session.close();
        return list;
    }

    public List<Product> getByCriteria(Criteria criteria) {
        Session session = getSessionFactory().openSession();
        List<Product> list = criteria.list();
        LOGGER.info("Products received by criteria: \n" + criteria.toString());
        session.close();
        return list;
    }

    public Product getById(Long id) {
        Session session = getSessionFactory().openSession();
        Product product = session.load(Product.class, id);
        LOGGER.info("Product received by id: \n" + id);
        return product;
    }

    public void delete(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.load(Product.class, id);
        session.delete(product);
        transaction.commit();
        LOGGER.info("Product deleted by id: \n" + id);
        session.close();
    }

    public void delete(Product persistentObject) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(persistentObject);
        transaction.commit();
        LOGGER.info("Product deleted: \n" + persistentObject.toString());
        session.close();
    }
}
