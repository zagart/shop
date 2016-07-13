package by.grodno.zagart.java.Dao.Impl;

import by.grodno.zagart.java.Dao.GenericDao;
import by.grodno.zagart.java.Entities.Order;
import by.grodno.zagart.java.Entities.OrderProduct;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import java.util.List;

import static by.grodno.zagart.java.Util.HibernateUtil.getSessionFactory;

/**
 * Created by Zagart on 11.07.2016.
 */
public class OrderProductDaoImpl implements GenericDao<OrderProduct, Long> {

    public Long save(OrderProduct obj) {
        Session session = getSessionFactory().openSession();
        session.save(obj);
        return obj.getId();
    }

    public void update(OrderProduct obj) {
        Session session = getSessionFactory().openSession();
        session.update(obj);
    }

    public List<OrderProduct> getAll() {
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(OrderProduct.class);
        List<OrderProduct> list = criteria.list();
        session.close();
        return list;
    }

    public List<OrderProduct> getByCriteria(Criteria criteria) {
        Session session = getSessionFactory().openSession();
        List<OrderProduct> list = criteria.list();
        session.close();
        return list;
    }

    public OrderProduct getById(Long id) {
        Session session = getSessionFactory().openSession();
        OrderProduct orderProduct = session.load(OrderProduct.class, id);
        return orderProduct;
    }

    public void delete(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        OrderProduct orderProduct = session.load(OrderProduct.class, id);
        session.delete(orderProduct);
        transaction.commit();
        session.close();
    }

    public void delete(OrderProduct persistentObject) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(persistentObject);
        transaction.commit();
        session.close();
    }

}
