package by.grodno.zagart.java.util;

import by.grodno.zagart.java.entities.Order1;
import by.grodno.zagart.java.entities.OrderProduct1;
import by.grodno.zagart.java.entities.Product1;
import by.grodno.zagart.java.services.impl.OrderProductServiceImpl;
import by.grodno.zagart.java.services.impl.OrderServiceImpl;
import by.grodno.zagart.java.services.impl.ProductServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Utility class.
 */
public class CommonUtil1 {

    private static OrderServiceImpl orderService = new OrderServiceImpl();
    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
    private static ProductServiceImpl productService = new ProductServiceImpl();

    public static void createOrders(int quantity) {
        for (int i = 0; i < quantity; i++) {
            Random rnd = new Random();
            Product1 product = new Product1();
            product.setDescription(RandomStringUtils.randomAlphabetic(20));
            product.setName(RandomStringUtils.randomAlphabetic(10));
            product.setCost((long)rnd.nextInt(100));
            productService.save(product);
            Order1 order = new Order1();
            order.setNumber(RandomStringUtils.randomNumeric(6));
            order.setDateOfOrder(randomDate());
            OrderProduct1 orderProduct = new OrderProduct1();
            orderProduct.addOrderProduct(order, product, (long)rnd.nextInt(100));
            orderService.save(order);
            orderProductService.save(orderProduct);
            product = new Product1();
            product.setDescription(RandomStringUtils.randomAlphabetic(20));
            product.setName(RandomStringUtils.randomAlphabetic(10));
            product.setCost(317L);
            productService.save(product);
            orderProduct = new OrderProduct1();
            orderProduct.addOrderProduct(order, product,(long)rnd.nextInt(10));
            orderService.update(order);
            orderProductService.save(orderProduct);
        }
    }

    public static List<OrderProduct1> randomOrderProductList(Long size) {
        List<OrderProduct1> list = new ArrayList<OrderProduct1>();
        for (int i = 0; i < size; i++) {
            list.add(new OrderProduct1());
        }
        return list;
    }

    public static Product1 randomProduct() {
        Random rnd = new Random();
        Product1 product = new Product1();
        Long id = rnd.nextLong();
        String name = RandomStringUtils.randomAlphabetic(100);
        String description = RandomStringUtils.randomAlphabetic(100);
        Long cost = rnd.nextLong();
        List<OrderProduct1> orderProducts = randomOrderProductList((long) rnd.nextInt(100));
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setCost(cost);
        product.setOrderProduct(orderProducts);
        return product;
    }

    public static Date randomDate() {
        Random rnd = new Random();
        Date date = DateUtils.setYears(new Date(), 2000 + rnd.nextInt(17));
        date = DateUtils.setMonths(date, 1 + rnd.nextInt(11));
        date = DateUtils.setDays(date, 1 + rnd.nextInt(28));
        return date;
    }

}
