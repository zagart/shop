package by.grodno.zagart.java;

import by.grodno.zagart.java.Dao.Impl.OrderDaoImpl;
import by.grodno.zagart.java.Dao.Impl.OrderProductDaoImpl;
import by.grodno.zagart.java.Dao.Impl.ProductDaoImpl;
import by.grodno.zagart.java.Entities.Order;
import by.grodno.zagart.java.Entities.OrderProduct;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

import static by.grodno.zagart.java.Util.HibernateUtil.closeFactory;
import static by.grodno.zagart.java.Util.HibernateUtil.getSessionFactory;

/**
 * Main class with entry point.
 */
public class Main {

    public static void main(String[] args ) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        ProductDaoImpl productDao = new ProductDaoImpl();
        task1(orderDao.getById(1L));
        task2(4L,6L);
        getSessionFactory().getCurrentSession().close();
        closeFactory();
    }

    private static void task1(Order order) {
        System.out.printf("Order id: %s; \n" +
                "Number: %s; \n" +
                "Date of order: %s; \n" +
                "Order-product table: \n",
                order.getId(),
                order.getNumber(),
                order.getDateOfOrder());
        for (OrderProduct op : order.getOrderProduct()) {
            System.out.println("Product name: " + op.getProduct().getName() + ", quantity in order: " + op.getQuantity());
        }
    }

    private static void task2(Long condition1, Long condition2) {
        OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();
        Criteria criteria = getSessionFactory().openSession().createCriteria(OrderProduct.class);
        criteria.add(Restrictions.le("quantity", condition1));
        List<OrderProduct> list = new ArrayList<OrderProduct>(orderProductDao
                .getByCriteria(criteria));
        for (OrderProduct op : list) {
            System.out.println(op.getOrder().getNumber() + " " + op.getQuantity());
        }
    }

}
