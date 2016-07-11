package by.grodno.zagart.java;

import by.grodno.zagart.java.Dao.Impl.OrderDaoImpl;
import by.grodno.zagart.java.Entities.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static by.grodno.zagart.java.Util.CommonUtil.createOrders;
import static by.grodno.zagart.java.Util.HibernateUtil.closeFactory;
import static by.grodno.zagart.java.Util.HibernateUtil.getSession;

/**
 * Main class with entry point.
 */
public class Main {

    public static void main(String[] args ) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        createOrders(10, session);
        transaction.commit();
        Order order = orderDao.getById(1L);
        orderDao.delete(2L);
        session.close();
        closeFactory();
    }

}
