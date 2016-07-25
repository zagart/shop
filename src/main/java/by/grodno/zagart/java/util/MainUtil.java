package by.grodno.zagart.java.util;

import by.grodno.zagart.java.dao.impl.OrderDaoImpl;
import by.grodno.zagart.java.dao.impl.OrderProductDaoImpl;
import by.grodno.zagart.java.dao.impl.ProductDaoImpl;
import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getSessionFactory;
import static org.apache.commons.lang3.time.DateUtils.*;

/**
 * Created by Zagart on 14.07.2016.
 */
public class MainUtil {

    private static OrderServiceImpl orderService = new OrderServiceImpl();
    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
    private static ProductServiceImpl productService = new ProductServiceImpl();

    public static void getOrderInfo(Order order) {
        Order pulledOrder = orderService.getById(order.getId());
        System.out.printf("Order id: %s; \n" +
                        "Number: %s; \n" +
                        "Date of order: %s; \n" +
                        "Products in order: \n",
                pulledOrder.getId(),
                pulledOrder.getNumber(),
                pulledOrder.getDateOfOrder());
        List<OrderProduct> list = orderProductService.getByCriterion(Restrictions.eq("order", order));
        for (OrderProduct op : list) {
            System.out.println("Product name: " + op.getProduct().getName() + ", quantity in order: " + op.getQuantity());
        }
    }

    public static void getOrderNumberByQuantityBySum(Long condition1, Long condition2) {
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.sqlRestriction("{alias}.id = " + "select id from {alias} where count(*) = " + condition2));
        List<OrderProduct> list = orderProductService.getByCriterion(criterions);
        for (OrderProduct op : list) {
            System.out.println(op.getOrder().getNumber() + " " + op.getQuantity());
        }
    }

    public static void getOrderByProduct(Product product) {

    }

    public static void getOrderWithoutProductByDate(Product product, Date date) {

    }

    public static void createNewOrderByDay(Date date) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();
        Date dayStart = setHours(setMinutes(setSeconds(setMilliseconds(date, 0), 0), 0), 0);
        Date dayEnd = setHours(setMinutes(setSeconds(setMilliseconds(date, 999), 59), 59), 23);
    }

}
