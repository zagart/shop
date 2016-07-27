package by.grodno.zagart.java.util;

import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if (!listByCriterion.isEmpty()) {
            for (OrderProduct op : listByCriterion) {
                System.out.println("#1 product name: " + op.getProduct().getName() + ", quantity in order: " + op.getQuantity());
            }
        } else {
            System.out.println("#1 no records found.");
        }
    }

    public static void getOrderNumberByQuantityBySum(Long condition1, Long condition2) {
        String hql = String.format("select o from Order o where size(o.orderProduct) = %d", condition2);
        ArrayList<Order> listByQuery = (ArrayList<Order>) orderService.getListByQuery(hql);
        if (!listByQuery.isEmpty()) {
            for (Order o : listByQuery) {
                System.out.println("#2 order " + o.getNumber());
            }
        } else {
            System.out.println("#2 no records found.");
        }
    }

    public static void getOrderByProduct(Product product) {
        ArrayList<OrderProduct> listByQuery = (ArrayList<OrderProduct>) orderProductService
                .getListByQuery(String.format("from OrderProduct op where op.product.id = %d", product.getId()));
        if (!listByQuery.isEmpty()) {
            System.out.println("#3 orders which contain product with id = " + product.getId());
            for (OrderProduct op : listByQuery) {
                System.out.println(op.getOrder().getNumber());
            }
        } else {
            System.out.println("#3 no records found.");
        }
    }

    public static void getOrderNotContainProductOfTheDay(Product product) {
        ArrayList<Order> listByQuery = (ArrayList<Order>) orderService.getListByQuery(String
                .format("select o from Order o " +
                        "join o.orderProduct op " +
                        "where o = op.order " +
                        "and o.dateOfOrder = current_date " +
                        "and op.product != %d",
                        product.getId()));
        if (!listByQuery.isEmpty()) {
            for (Order o : listByQuery) {
                System.out.println("#4 " + o.getNumber());
            }
        } else {
            System.out.println("#4 no records found.");
        }
    }

    public static void createNewOrderOfTheDay() {
        ArrayList<Product> listByQuery = (ArrayList<Product>) productService
                .getListByQuery("select p from Product p " +
                        "join p.orderProduct op " +
                        "join op.order o " +
                        "where p = op.product " +
                        "and o = op.order " +
                        "and o.dateOfOrder = current_date");
        if (!listByQuery.isEmpty()) {
            for (Product p : listByQuery) {
                System.out.println("#5 " + p.getName());
            }
//            OrderProduct orderProduct = new OrderProduct();
//            orderProductService.save(orderProduct);
//            Order order = new Order();
//            orderService.save(order);
//            order.setNumber(RandomStringUtils.randomNumeric(6));
//            order.setDateOfOrder(new Date());
//            for (int i = 0; i < listByQuery.size(); i++) {
//                orderProduct.addOrderProduct(order, listByQuery.get(i), 1L);
//            }
//            orderService.update(order);
//            orderProductService.update(orderProduct);
        } else {
            System.out.println("#5 no records found.");
        }
    }

}
