package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.interfaces.IdentifiableEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * Class that provides connection between a product
 * and order.
 */
@Entity
@Table(name = "order_product")
public class OrderProduct1 implements IdentifiableEntity<Long> {

    private static String entityName = "OrderProduct1";

    private Long id;
    private Order1 order;
    private Product1 product;
    private Long quantity;

    public String getEntityName() { return entityName; }
    public static void setEntityName(String entityName) { OrderProduct1.entityName = entityName; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @ManyToOne
    public Order1 getOrder() { return order; }
    public void setOrder(Order1 order) { this.order = order; }

    @ManyToOne
    public Product1 getProduct() { return product; }
    public void setProduct(Product1 product) { this.product = product; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }

    public void addOrderProduct(Order1 order, Product1 product, Long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        List<OrderProduct1> orderProduct = order.getOrderProduct();
        orderProduct.add(this);
        order.setOrderProduct(orderProduct);
        orderProduct = product.getOrderProduct();
        orderProduct.add(this);
        product.setOrderProduct(orderProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct1 that = (OrderProduct1) o;
        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getOrder(), that.getOrder())
                .append(getProduct(), that.getProduct())
                .append(getQuantity(), that.getQuantity())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }

}
