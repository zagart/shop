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
import java.util.Collections;
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
        Order orderServiceById = orderService.getById(order.getId());
        System.out.printf("#1 order id: %s; \n" +
                        "#1 number: %s; \n" +
                        "#1 date of order: %s; \n" +
                        "#1 products in order: \n",
                orderServiceById.getId(),
                orderServiceById.getNumber(),
                orderServiceById.getDateOfOrder());
        List<OrderProduct> listByCriterion = orderProductService.getByCriterion(Restrictions.eq("order", order));
        for (OrderProduct op : listByCriterion) {
            System.out.println("#1 product name: " + op.getProduct().getName() + ", quantity in order: " + op.getQuantity());
        }
    }

    public static void getOrderNumberByQuantityBySum(Long condition1, Long condition2) {
        String hql = String.format("select o from Order o where size(o.orderProduct) = %d", condition2);
        ArrayList<Order> listByQuery = (ArrayList<Order>) orderService.getListByQuery(hql);
        for (Order o : listByQuery) {
            System.out.println("#2 order " + o.getNumber());
        }
    }

    public static void getOrderByProduct(Product product) {
        ArrayList<OrderProduct> listByQuery = (ArrayList<OrderProduct>) orderProductService
                .getListByQuery(String.format("from OrderProduct op where op.product.id = %d", product.getId()));
        System.out.println("#3 orders which contain product with id = " + product.getId());
        for (OrderProduct op : listByQuery) {
            System.out.println(op.getOrder().getNumber());
        }
    }

    public static void getOrderNotContainProductByDate(Product product, Date date) {
        Date dayStart = CommonUtil.setDayStart(new Date());
        Date dayEnd = CommonUtil.setDayEnd(new Date());
        Order order = new Order();
        order.setNumber("199426");
        order.setDateOfOrder(dayEnd);
        orderService.save(order);
        ArrayList<Order> orderServiceListByQuery = (ArrayList<Order>) orderService.getListByQuery(String
                .format("select order from Order order " +
                        "where order.id > 0 " +
                        "and order.id > 0",
//                        "and current_date < all elements(order.dateOfOrder)",
                        product.getId()));
        for (Order o : orderServiceListByQuery) {
            System.out.println("#4 " + o.getNumber());
        }
    }

    public static void createNewOrderByDay(Date date) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();
        Date dayStart = CommonUtil.setDayStart(date);
        Date dayEnd = CommonUtil.setDayEnd(date);
    }

}
