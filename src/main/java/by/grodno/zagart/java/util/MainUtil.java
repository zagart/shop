package by.grodno.zagart.java.util;

import by.grodno.zagart.java.dao.impl.OrderDaoImpl1;
import by.grodno.zagart.java.dao.impl.OrderProductDaoImpl1;
import by.grodno.zagart.java.dao.impl.ProductDaoImpl1;
import by.grodno.zagart.java.entities.Order1;
import by.grodno.zagart.java.entities.OrderProduct1;
import by.grodno.zagart.java.entities.Product1;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil1.getSessionFactory;
import static org.apache.commons.lang3.time.DateUtils.*;

/**
 * Created by Zagart on 14.07.2016.
 */
public class MainUtil {

    public static void getOrderInfo(Order1 order) {
        System.out.printf("Order1 id: %s; \n" +
                        "Number: %s; \n" +
                        "Date of order: %s; \n" +
                        "Order1-product table: \n",
                order.getId(),
                order.getNumber(),
                order.getDateOfOrder());
        for (OrderProduct1 op : order.getOrderProduct()) {
            System.out.println("Product1 name: " + op.getProduct().getName() + ", quantity in order: " + op.getQuantity());
        }
    }

    public static void getOrderNumberByQuantityBySum(Long condition1, Long condition2) {
        OrderProductDaoImpl1 orderProductDao = new OrderProductDaoImpl1();
        Criteria criteria = getSessionFactory().openSession().createCriteria(OrderProduct1.class);
        criteria.add(Restrictions.le("quantity", condition1));
        List<OrderProduct1> list = new ArrayList<OrderProduct1>(orderProductDao
                .getByCriteria(criteria));
        for (OrderProduct1 op : list) {
            System.out.println(op.getOrder().getNumber() + " " + op.getQuantity());
        }
    }

    public static void getOrderByProduct(Product1 product) {
        Product1 localProduct = product;
        for (OrderProduct1 op : localProduct.getOrderProduct()) {
            System.out.println(op.getOrder().getNumber());
        }
    }

    public static void getOrderWithoutProductByDate(Product1 product, Date date) {
        OrderProductDaoImpl1 orderProductDao = new OrderProductDaoImpl1();
        List<OrderProduct1> list;
        Criterion criterion = Restrictions.not(Restrictions.eq("product", product));
        Criteria criteria = getSessionFactory().openSession()
                .createCriteria(OrderProduct1.class)
                .add(criterion);
        list = new ArrayList<OrderProduct1>(orderProductDao.getByCriteria(criteria));

        for (int i = 0; i < list.size(); i++) {
            Date date1 = list.get(i).getOrder().getDateOfOrder();
            if (!DateUtils.isSameDay(date, date1)) {
                list.remove(i);
                i--;
            }
        }

        for (OrderProduct1 op : list) {
            System.out.println(op.getProduct().getId());
        }

        getSessionFactory().getCurrentSession().close();
    }

    public static void createNewOrderByDay(Date date) {
        OrderDaoImpl1 orderDao = new OrderDaoImpl1();
        OrderProductDaoImpl1 orderProductDao = new OrderProductDaoImpl1();
        Date dayStart = setHours(setMinutes(setSeconds(setMilliseconds(date, 0), 0), 0), 0);
        Date dayEnd = setHours(setMinutes(setSeconds(setMilliseconds(date, 999), 59), 59), 23);
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Order1.class, "dateOfOrder")
                .add(Restrictions.between("dateOfOrder", dayStart, dayEnd));
        getSessionFactory().getCurrentSession().close();
        List<Order1> list = orderDao.getByCriteria(criteria);

        Order1 newOrder = new Order1();
        OrderProduct1 orderProduct = new OrderProduct1();
        ProductDaoImpl1 productDao = new ProductDaoImpl1();
        for (Order1 o : list) {
            for (OrderProduct1 op : o.getOrderProduct()) {
                orderProduct.addOrderProduct(newOrder, op.getProduct(), 1L);
                productDao.update(op.getProduct());
            }
        }
        orderDao.save(newOrder);
        orderProductDao.save(orderProduct);
    }

}
