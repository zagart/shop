package by.grodno.zagart.java;

import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;

import static by.grodno.zagart.java.util.CommonUtil.createOrders;
import static by.grodno.zagart.java.util.HibernateUtil.closeFactory;
import static by.grodno.zagart.java.util.MainUtil.getOrderInfo;

/**
 * Main class with entry point.
 */
public class Main {

    private static OrderServiceImpl orderService = new OrderServiceImpl();
    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
    private static ProductServiceImpl productService = new ProductServiceImpl();

    public static void main(String[] args ) {
        createOrders(10);
        getOrderInfo(orderService.getById(1L));
//        getOrderNumberByQuantityBySum(4L,6L);
//        getOrderByProduct(productDao.getById(7L));
//        getOrderWithoutProductByDate(productDao.getById(7L), orderDao.getById(1L).getDateOfOrder());
//        createNewOrderByDay(orderDao.getById(8L).getDateOfOrder());
//        closeSession();
        closeFactory();
    }

}
