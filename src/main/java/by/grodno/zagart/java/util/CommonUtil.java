package by.grodno.zagart.java.util;

import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.time.DateUtils.*;

/**
 * Utility class.
 */
public class CommonUtil {

    private static Random rnd = new Random();

    private static OrderServiceImpl orderService = new OrderServiceImpl();
    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
    private static ProductServiceImpl productService = new ProductServiceImpl();

    public static void createOrders(int quantity) {
        for (int i = 0; i < quantity - 1; i++) {
            Product product = randomProduct();
            productService.save(product);
            Order order = randomOrder();
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.addOrderProduct(order, product, 1 + (long)rnd.nextInt(100));
            orderService.save(order);
            orderProductService.save(orderProduct);
            productService.update(product);
            product = randomProduct();
            productService.save(product);
            orderProduct.addOrderProduct(order, product, 1 + (long)rnd.nextInt(10));
            orderService.update(order);
            orderProductService.save(orderProduct);
            productService.update(product);
        }
        createThisDayOrder(randomProduct());
    }

    public static Order createThisDayOrder(Product product) {
        Order order = new Order();
        order.setNumber("199426");
        order.setDateOfOrder(new Date());
        OrderProduct orderProduct = new OrderProduct();
        productService.save(product);
        orderService.save(order);
        orderProduct.addOrderProduct(order, product, 1L);
        orderProductService.save(orderProduct);
        productService.update(product);
        orderService.update(order);
        return order;
    }

    public static List<OrderProduct> randomOrderProductList(Long size) {
        List<OrderProduct> list = new ArrayList<OrderProduct>();
        for (int i = 0; i < size; i++) {
            list.add(new OrderProduct());
        }
        return list;
    }

    public static Product randomProduct() {
        Product product = new Product();
        String name = RandomStringUtils.randomAlphabetic(6);
        String description = RandomStringUtils.randomAlphabetic(10);
        Long cost = 1 + (long) rnd.nextInt(1000000);
        product.setName(name);
        product.setDescription(description);
        product.setCost(cost);
        return product;
    }

    public static Order randomOrder() {
        Order order = new Order();
        order.setNumber(RandomStringUtils.randomNumeric(6));
        order.setDateOfOrder(randomDate());
        return order;
    }

    public static Date randomDate() {
        Random rnd = new Random();
        Date date = DateUtils.setYears(new Date(), 2000 + rnd.nextInt(17));
        date = DateUtils.setMonths(date, 1 + rnd.nextInt(11));
        date = DateUtils.setDays(date, 1 + rnd.nextInt(28));
        return date;
    }

}
