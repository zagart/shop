package by.grodno.zagart.java.Entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a product.
 */
@Entity
@Table(name = "shop_product")
public class Product {

    private Long id;
    private String name;
    private String description;
    private Long cost;
    private Long quantity;
    private Map<Order, Long> orders = new HashMap<Order, Long>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getCost() { return cost; }
    public void setCost(Long cost) { this.cost = cost; }

    @MapKeyJoinColumn(name = "quantity")
    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }

    @ElementCollection
    public Map<Order, Long>  getOrders() { return orders; }
    public void setOrders(Map<Order, Long>  orders) { this.orders = orders; }

}
