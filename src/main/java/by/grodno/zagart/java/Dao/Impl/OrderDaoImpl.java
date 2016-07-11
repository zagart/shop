package by.grodno.zagart.java.Dao.Impl;

import by.grodno.zagart.java.Dao.AbstractHibernateDao;
import by.grodno.zagart.java.Entities.Order;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by Zagart on 11.07.2016.
 */
public class OrderDaoImpl extends AbstractHibernateDao<Order, Long> {

    @Override
    public Long save(Order obj) {
        return super.save(obj);
    }

    @Override
    public void update(Order o) {
        super.update(o);
    }

    @Override
    public List<Order> getAll() {
        return super.getAll();
    }

    @Override
    public Long getLength() {
        return super.getLength();
    }

    @Override
    public List<Order> getByCriteria(Criterion criterion) {
        return super.getByCriteria(criterion);
    }

    @Override
    public Long getLength(Criterion criterion) {
        return super.getLength(criterion);
    }

    @Override
    public List<Order> getByCriteria(Criterion criterion, int begin, int count) {
        return super.getByCriteria(criterion, begin, count);
    }

    @Override
    public Order getById(Long id) {
        return super.getById(id);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public void delete(Order persistentObject) {
        super.delete(persistentObject);
    }

}
