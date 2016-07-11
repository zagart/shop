package by.grodno.zagart.java.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * Class that provides connection between a product
 * and order.
 */
@Entity
@Table(name = "order_product")
public class OrderProduct {

    private Long id;
    private Order order;
    private Product product;
    private Long quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @ManyToOne
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    @ManyToOne
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }

    public void addOrderProduct(Order order, Product product, Long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        List<OrderProduct> orderProduct = order.getOrderProduct();
        orderProduct.add(this);
        order.setOrderProduct(orderProduct);
        orderProduct = product.getOrderProduct();
        orderProduct.add(this);
        product.setOrderProduct(orderProduct);
    }

}
