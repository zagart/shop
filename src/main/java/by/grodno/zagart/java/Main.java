package by.grodno.zagart.java;

import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
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
        createOrders(10);
        getOrderInfo(orderService.getById(1L));
        getOrderNumberByQuantityBySum(4L,2L);
        getOrderByProduct(productService.getById(7L));
        getOrderNotContainProductOfTheDay(productService.getById(5L));
        createNewOrderOfTheDay();
        testHql();
        closeFactory();
    }

    private static void testHql() {
        Order order = new Order();
        order.setDateOfOrder(new Date());
        order.setNumber("hql_update_3190");
        orderService.save(order);
        OrderProduct orderProduct = new OrderProduct();
        orderProductService.save(orderProduct);
        String hql = "select p from Product p " +
                "join p.orderProduct op " +
                "join op.order o " +
                "where p = op.product " +
                "and o = op.order " +
                "and o.dateOfOrder = current_date";
        String hqlUpdate = "";
        ArrayList<Product> listByQuery = (ArrayList<Product>) productService.getListByQuery(hql);
//        openCurrentSession();
//        Query q = getCurrentSession().createQuery(hqlUpdate);
        for (Product p : listByQuery) {
            orderProduct.addOrderProduct(order, p, 1L);
            orderService.update(order);
            productService.update(p);
            orderProductService.update(orderProduct);
        }

        System.out.println("#test ");
//        closeCurrentSession();
    }

}
