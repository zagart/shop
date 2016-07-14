package by.grodno.zagart.java.util;

import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Utility class.
 */
public class CommonUtil {

    public static void createOrders(int quantity, Session session) {
        for (int i = 0; i < quantity; i++) {
            Random rnd = new Random();
            Product product = new Product();
            product.setDescription(RandomStringUtils.randomAlphabetic(20));
            product.setName(RandomStringUtils.randomAlphabetic(10));
            product.setCost((long)rnd.nextInt(100));
            session.save(product);
            Order order = new Order();
            order.setNumber(RandomStringUtils.randomNumeric(6));
            order.setDateOfOrder(new Date());
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.addOrderProduct(order,product,(long)rnd.nextInt(100));
            session.save(orderProduct);
            product = new Product();
            product.setDescription(RandomStringUtils.randomAlphabetic(20));
            product.setName(RandomStringUtils.randomAlphabetic(10));
            product.setCost(317L);
            session.save(product);
            orderProduct = new OrderProduct();
            orderProduct.addOrderProduct(order,product,(long)rnd.nextInt(10));
            session.save(order);
            session.save(orderProduct);
        }
    }

    public static List<OrderProduct> randomOrderProductList(Long size) {
        List<OrderProduct> list = new ArrayList<OrderProduct>();
        for (int i = 0; i < size; i++) {
            list.add(new OrderProduct());
        }
        return list;
    }

}
