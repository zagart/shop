package by.grodno.zagart.java.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();

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

    @OneToMany(mappedBy = "product")
    public List<OrderProduct> getOrderProduct() { return orderProduct; }
    public void setOrderProduct(List<OrderProduct> orderProduct) { this.orderProduct = orderProduct; }

}
