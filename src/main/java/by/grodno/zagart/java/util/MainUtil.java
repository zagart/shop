package by.grodno.zagart.java.util;

import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Created by Zagart on 14.07.2016.
 */
public class MainUtil {

    private static OrderServiceImpl orderService = new OrderServiceImpl();
    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
    private static ProductServiceImpl productService = new ProductServiceImpl();

    public static void getOrderInfo(Order order) {
        Order orderServiceById = orderService.getById(order.getId());
        System.out.print(String
                .format("#1 order id: %s; \n" +
                        "#1 number: %s; \n" +
                        "#1 date of order: %s; \n" +
                        "#1 products in order: \n",
                orderServiceById.getId(),
                orderServiceById.getNumber(),
                orderServiceById.getDateOfOrder()));
        List<OrderProduct> listByCriterion = orderProductService.getByCriterion(Restrictions.eq("order", order));
        if (!listByCriterion.isEmpty()) {
            for (OrderProduct op : listByCriterion) {
                System.out.println(String
                        .format("#1 product name: %s, product cost: %d, quantity in order: %d",
                                op.getProduct().getName(),
                                op.getProduct().getCost(),
                                op.getQuantity()));
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
                System.out.println(String.format("#2 order %s", o.getNumber()));
            }
        } else {
            System.out.println("#2 no records found.");
        }
    }

    public static void getOrderByProduct(Product product) {
        ArrayList<OrderProduct> listByQuery = (ArrayList<OrderProduct>) orderProductService
                .getListByQuery(String.format("from OrderProduct op where op.product.id = %d", product.getId()));
        if (!listByQuery.isEmpty()) {
            System.out.println(String.format("#3 orders which contain product with id = %d", product.getId()));
            for (OrderProduct op : listByQuery) {
                System.out.println(op.getOrder().getNumber());
            }
        } else {
            System.out.println("#3 no records found.");
        }
    }

    public static void getOrderOfTheDayNotContainProduct(Product product) {
        ArrayList<Order> listByQuery = (ArrayList<Order>) orderService.getListByQuery(String
                .format("select o from Order o " +
                        "join o.orderProduct op " +
                        "where o = op.order " +
                        "and o.dateOfOrder = current_date " +
                        "and op.product != %d",
                        product.getId()));
        Set<Order> setFromList = new HashSet<>();
        setFromList.addAll(listByQuery);
        if (!setFromList.isEmpty()) {
            for (Order o : setFromList) {
                System.out.println(String.format("#4 %s", o.getNumber()));
            }
        } else {
            System.out.println("#4 no records found.");
        }
    }

    public static void createNewOrderOfTheDay() {
        Order order = new Order();
        order.setDateOfOrder(new Date());
        order.setNumber("#5_generated");
        orderService.save(order);
        OrderProduct orderProduct = new OrderProduct();
        String hql = "select p from Product p " +
                "join p.orderProduct op " +
                "join op.order o " +
                "where p = op.product " +
                "and o = op.order " +
                "and o.dateOfOrder = current_date";
        ArrayList<Product> listByQuery = (ArrayList<Product>) productService.getListByQuery(hql);
        if (!listByQuery.isEmpty()) {
            for (Product p : listByQuery) {
                orderProduct.addOrderProduct(order, p, 1L);
                orderProductService.save(orderProduct);
            }
            System.out.println("#5 new order created.");
        } else {
            System.out.println("#5 no records found.");
        }
    }

    public static void deleteOrdersByProductQuantity(Long quantity) {
        Map<String, Object> parameters = new HashMap<>();
        String hql = String.format("select o.id from Order o " +
                "join o.orderProduct as op " +
                "where o = op.order " +
                "and op.quantity = %d", quantity);
        Set<Long> idSet = orderService.getPkSetByQuery(hql);
        parameters.put("idSet", idSet);
        hql = "delete from OrderProduct where order.id in :idSet";
        orderProductService.executeQuery(hql, parameters);
        hql = "delete from Order where id in :idSet";
        orderProductService.executeQuery(hql, parameters);
    }

}
