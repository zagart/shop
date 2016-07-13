package by.grodno.zagart.java.Dao.Impl;

import by.grodno.zagart.java.Dao.GenericDao;
import by.grodno.zagart.java.Entities.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import java.util.List;

import static by.grodno.zagart.java.Util.HibernateUtil.getSessionFactory;

/**
 * Created by Zagart on 11.07.2016.
 */
public class OrderDaoImpl implements GenericDao<Order, Long> {

    public Long save(Order obj) {
        Session session = getSessionFactory().openSession();
        session.save(obj);
        return obj.getId();
    }

    public void update(Order obj) {
        Session session = getSessionFactory().openSession();
        session.update(obj);
    }

    public List<Order> getAll() {
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Order.class);
        List<Order> list = criteria.list();
        session.close();
        return list;
    }

    public List<Order> getByCriteria(Criteria criteria) {
        Session session = getSessionFactory().openSession();
        List<Order> list = criteria.list();
        session.close();
        return list;
    }

    public Order getById(Long id) {
        Session session = getSessionFactory().openSession();
        Order order = session.load(Order.class, id);
        return order;
    }

    public void delete(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.load(Order.class,id);
        session.delete(order);
        transaction.commit();
        session.close();
    }

    public void delete(Order persistentObject) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(persistentObject);
        transaction.commit();
        session.close();
    }

}
