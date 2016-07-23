package by.grodno.zagart.java;

import by.grodno.zagart.java.dao.impl.OrderDaoImpl;
import by.grodno.zagart.java.dao.impl.ProductDaoImpl;
import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.grodno.zagart.java.services.MainService.*;
import static by.grodno.zagart.java.util.CommonUtil.createOrders;
import static by.grodno.zagart.java.util.CommonUtil.randomProduct;
import static by.grodno.zagart.java.util.HibernateUtil.closeFactory;
import static by.grodno.zagart.java.util.HibernateUtil.getSessionFactory;

/**
 * Main class with entry point.
 */
public class Main {

    private static OrderServiceImpl service = new OrderServiceImpl();

    public static void main(String[] args ) {
        createOrders(10);
//        OrderDaoImpl orderDao = new OrderDaoImpl();
//        ProductDaoImpl productDao = new ProductDaoImpl();
//        getOrderInfo(orderDao.getById(1L));
//        getOrderNumberByQuantityBySum(4L,6L);
//        getOrderByProduct(productDao.getById(7L));
//        getOrderWithoutProductByDate(productDao.getById(7L), orderDao.getById(1L).getDateOfOrder());
//        createNewOrderByDay(orderDao.getById(8L).getDateOfOrder());
//        closeSession();
        closeFactory();
    }

}
