package by.grodno.zagart.java.services;

import by.grodno.zagart.java.dao.impl.OrderProductDaoImpl;
import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.grodno.zagart.java.util.HibernateUtil.getSessionFactory;

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
        for (OrderProduct op : product.getOrderProduct()) {
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
    }

}
