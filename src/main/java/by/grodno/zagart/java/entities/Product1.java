package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.interfaces.IdentifiableEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a product.
 */
@Entity
@Table(name = "shop_product")
public class Product1 implements IdentifiableEntity<Long> {

    private static String entityName = "Product1";

    private Long id;
    private String name;
    private String description;
    private Long cost;
    private List<OrderProduct1> orderProduct = new ArrayList<OrderProduct1>();

    public static void setEntityName(String entityName) { Product1.entityName = entityName; }
    public String getEntityName() { return entityName; }

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<OrderProduct1> getOrderProduct() { return orderProduct; }
    public void setOrderProduct(List<OrderProduct1> orderProduct) { this.orderProduct = orderProduct; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product1 product = (Product1) o;
        return new EqualsBuilder()
                .append(getId(), product.getId())
                .append(getName(), product.getName())
                .append(getDescription(), product.getDescription())
                .append(getCost(), product.getCost())
                .append(getOrderProduct(), product.getOrderProduct())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }

}
