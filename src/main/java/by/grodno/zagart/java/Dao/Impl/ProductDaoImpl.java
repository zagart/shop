package by.grodno.zagart.java.Dao.Impl;

import by.grodno.zagart.java.Dao.AbstractHibernateDao;
import by.grodno.zagart.java.Entities.Product;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by Zagart on 11.07.2016.
 */
public class ProductDaoImpl extends AbstractHibernateDao<Product, Long> {

    @Override
    public Long save(Product obj) {
        return super.save(obj);
    }

    @Override
    public void update(Product o) {
        super.update(o);
    }

    @Override
    public List<Product> getAll() {
        return super.getAll();
    }

    @Override
    public Long getLength() {
        return super.getLength();
    }

    @Override
    public List<Product> getByCriteria(Criterion criterion) {
        return super.getByCriteria(criterion);
    }

    @Override
    public Long getLength(Criterion criterion) {
        return super.getLength(criterion);
    }

    @Override
    public List<Product> getByCriteria(Criterion criterion, int begin, int count) {
        return super.getByCriteria(criterion, begin, count);
    }

    @Override
    public Product getById(Long id) {
        return super.getById(id);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public void delete(Product persistentObject) {
        super.delete(persistentObject);
    }

}
