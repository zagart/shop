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
@Table(name = "SHOP_ORDER_PRODUCT")
public class OrderProduct implements Identifiable<Long> {

    private Long id;
    private Order order;
    private Product product;
    private Long quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRODUCT_ID")
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    @Column(name = "QUANTITY")
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
