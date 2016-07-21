package by.grodno.zagart.java;

import by.grodno.zagart.java.dao.impl.OrderDaoImpl;
import by.grodno.zagart.java.dao.impl.ProductDaoImpl;

import java.util.Date;

import static by.grodno.zagart.java.services.MainService.*;
import static by.grodno.zagart.java.util.CommonUtil.createOrders;
import static by.grodno.zagart.java.util.HibernateUtil.closeFactory;
import static by.grodno.zagart.java.util.HibernateUtil.getSessionFactory;

/**
 * Main class with entry point.
 */
public class Main {

    public static void main(String[] args ) {
        createOrders(10,getSessionFactory().openSession());
        OrderDaoImpl orderDao = new OrderDaoImpl();
        ProductDaoImpl productDao = new ProductDaoImpl();
        getOrderInfo(orderDao.getById(1L));
        getOrderNumberByQuantityBySum(4L,6L);
        getOrderByProduct(productDao.getById(7L));
        getOrderWithoutProductByDate(productDao.getById(7L), orderDao.getById(1L).getDateOfOrder());
        closeSession();
        closeFactory();
    }

    private static void closeSession() {
        getSessionFactory().getCurrentSession().close();
    }

}
