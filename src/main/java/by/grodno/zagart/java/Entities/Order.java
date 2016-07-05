package by.grodno.zagart.java.Entities;

import javax.persistence.*;
import java.util.*;

/**
 * Class that represents a product.
 */
@Entity
@Table(name = "shop_order")
public class Order {

    private Long id;
    private String number;
    private Date dateOfOrder;
    private List<Product> products = new ArrayList<Product>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    @Temporal(TemporalType.DATE)
    public Date getDateOfOrder() { return dateOfOrder; }
    public void setDateOfOrder(Date dateOfOrder) { this.dateOfOrder = dateOfOrder; }

    @ElementCollection
    @ManyToMany(targetEntity = Product.class, mappedBy = "orders")
    @JoinTable(name = "order_product",
               joinColumns = @JoinColumn(name = "order_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public void addProduct(Product product) {
        products.add(product);
        Map<Order, Long> orders = product.getOrders();
        orders.put(this, product.getQuantity());
        product.setOrders(orders);
    }

}
