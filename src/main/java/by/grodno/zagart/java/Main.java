package by.grodno.zagart.java;

import by.grodno.zagart.java.services.impl.OrderServiceImpl;

import static by.grodno.zagart.java.util.CommonUtil1.createOrders;
import static by.grodno.zagart.java.util.HibernateUtil1.closeFactory;

/**
 * Main class with entry point.
 */
public class Main {

    private static OrderServiceImpl service = new OrderServiceImpl();

    public static void main(String[] args ) {
        createOrders(10);
//        OrderDaoImpl1 orderDao = new OrderDaoImpl1();
//        ProductDaoImpl1 productDao = new ProductDaoImpl1();
//        getOrderInfo(orderDao.getById(1L));
//        getOrderNumberByQuantityBySum(4L,6L);
//        getOrderByProduct(productDao.getById(7L));
//        getOrderWithoutProductByDate(productDao.getById(7L), orderDao.getById(1L).getDateOfOrder());
//        createNewOrderByDay(orderDao.getById(8L).getDateOfOrder());
//        closeSession();
        closeFactory();
    }

}
