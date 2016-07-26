package by.grodno.zagart.java;

import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static by.grodno.zagart.java.util.CommonUtil.createOrders;
import static by.grodno.zagart.java.util.HibernateUtil.*;
import static by.grodno.zagart.java.util.MainUtil.*;

/**
 * Main class with entry point.
 */
public class Main {

    private static OrderServiceImpl orderService = new OrderServiceImpl();
    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
    private static ProductServiceImpl productService = new ProductServiceImpl();

    public static void main(String[] args ) {
//        createOrders(10);
        getOrderInfo(orderService.getById(1L));
        getOrderNumberByQuantityBySum(4L,2L);
        getOrderByProduct(productService.getById(7L));
        getOrderNotContainProductByDate(productService.getById(20L));
//        createNewOrderByDay(orderService.getById(8L).getDateOfOrder());
//        testHql();
        closeFactory();
    }

    private static void testHql() {
        openCurrentSession();
        List<Order> orders = (List<Order>) getCurrentSession()
                .createQuery("").getResultList();
        for (Order o : orders) {
            System.out.println("#test " + o);
        }
        closeCurrentSession();
    }

}
