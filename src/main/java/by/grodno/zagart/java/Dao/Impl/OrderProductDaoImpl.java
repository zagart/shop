package by.grodno.zagart.java.Dao.Impl;

import by.grodno.zagart.java.Dao.AbstractHibernateDao;
import by.grodno.zagart.java.Entities.OrderProduct;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by Zagart on 11.07.2016.
 */
public class OrderProductDaoImpl extends AbstractHibernateDao<OrderProduct, Long> {

    @Override
    public Long save(OrderProduct obj) {
        return super.save(obj);
    }

    @Override
    public void update(OrderProduct o) {
        super.update(o);
    }

    @Override
    public List<OrderProduct> getAll() {
        return super.getAll();
    }

    @Override
    public Long getLength() {
        return super.getLength();
    }

    @Override
    public List<OrderProduct> getByCriteria(Criterion criterion) {
        return super.getByCriteria(criterion);
    }

    @Override
    public Long getLength(Criterion criterion) {
        return super.getLength(criterion);
    }

    @Override
    public List<OrderProduct> getByCriteria(Criterion criterion, int begin, int count) {
        return super.getByCriteria(criterion, begin, count);
    }

    @Override
    public OrderProduct getById(Long id) {
        return super.getById(id);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public void delete(OrderProduct persistentObject) {
        super.delete(persistentObject);
    }
}
