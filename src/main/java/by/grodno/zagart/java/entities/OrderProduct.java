package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.interfaces.Identifiable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * Entity that provides connection between a product
 * and order.
 */
@Entity
@Table(name = "order_product")
public class OrderProduct implements Identifiable<Long> {

    private static String entityName = "OrderProduct";

    private Long id;
    private Order order;
    private Product product;
    private Long quantity;

    @Transient
    public String getEntityName() { return entityName; }
    public static void setEntityName(String entityName) { OrderProduct.entityName = entityName; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }

    public void addOrderProduct(Order order, Product product, Long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        List<OrderProduct> list = order.getOrderProduct();
        list.add(this);
        order.setOrderProduct(list);
        list = product.getOrderProduct();
        list.add(this);
        product.setOrderProduct(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
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
