package by.grodno.zagart.java.services;

import by.grodno.zagart.java.dao.impl.OrderDaoImpl;
import by.grodno.zagart.java.dao.impl.OrderProductDaoImpl;
import by.grodno.zagart.java.dao.impl.ProductDaoImpl;
import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
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
public class MainService {

    public static void getOrderInfo(Order order) {
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

    public static void getOrderNumberByQuantityBySum(Long condition1, Long condition2) {
        OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();
        Criteria criteria = getSessionFactory().openSession().createCriteria(OrderProduct.class);
        criteria.add(Restrictions.le("quantity", condition1));
        List<OrderProduct> list = new ArrayList<OrderProduct>(orderProductDao
                .getByCriteria(criteria));
        for (OrderProduct op : list) {
            System.out.println(op.getOrder().getNumber() + " " + op.getQuantity());
        }
    }

    public static void getOrderByProduct(Product product) {
        Product localProduct = product;
        for (OrderProduct op : localProduct.getOrderProduct()) {
            System.out.println(op.getOrder().getNumber());
        }
    }

    public static void getOrderWithoutProductByDate(Product product, Date date) {
        OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();
        List<OrderProduct> list;
        Criterion criterion = Restrictions.not(Restrictions.eq("product", product));
        Criteria criteria = getSessionFactory().openSession()
                .createCriteria(OrderProduct.class)
                .add(criterion);
        list = new ArrayList<OrderProduct>(orderProductDao.getByCriteria(criteria));

        for (int i = 0; i < list.size(); i++) {
            Date date1 = list.get(i).getOrder().getDateOfOrder();
            if (!DateUtils.isSameDay(date, date1)) {
                list.remove(i);
                i--;
            }
        }

        for (OrderProduct op : list) {
            System.out.println(op.getProduct().getId());
        }

        getSessionFactory().getCurrentSession().close();
    }

    public static void createNewOrderByDay(Date date) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();
        Date dayStart = setHours(setMinutes(setSeconds(setMilliseconds(date, 0), 0), 0), 0);
        Date dayEnd = setHours(setMinutes(setSeconds(setMilliseconds(date, 999), 59), 59), 23);
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Order.class, "dateOfOrder")
                .add(Restrictions.between("dateOfOrder", dayStart, dayEnd));
        getSessionFactory().getCurrentSession().close();
        List<Order> list = orderDao.getByCriteria(criteria);

        Order newOrder = new Order();
        OrderProduct orderProduct = new OrderProduct();
        ProductDaoImpl productDao = new ProductDaoImpl();
        for (Order o : list) {
            for (OrderProduct op : o.getOrderProduct()) {
                orderProduct.addOrderProduct(newOrder, op.getProduct(), 1L);
                productDao.update(op.getProduct());
            }
        }
        orderDao.save(newOrder);
        orderProductDao.save(orderProduct);
    }

}
