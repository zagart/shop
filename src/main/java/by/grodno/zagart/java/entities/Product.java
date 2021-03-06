package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.interfaces.Identifiable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity that represents a product.
 */
@Entity
@Table(name = "SHOP_PRODUCT")
public class Product implements Identifiable<Long> {

    private Long id;
    private String name;
    private String description;
    private Long cost;
    private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Column(name = "NAME")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name = "DESCRIPTION")
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Column(name = "COST")
    public Long getCost() { return cost; }
    public void setCost(Long cost) { this.cost = cost; }

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public List<OrderProduct> getOrderProduct() { return orderProduct; }
    public void setOrderProduct(List<OrderProduct> orderProduct) { this.orderProduct = orderProduct; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
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
