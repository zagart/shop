package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.interfaces.IdentifiableEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents a product.
 */
@Entity
@Table(name = "shop_order")
public class Order implements IdentifiableEntity<Long> {

    private static String entityName = "Order";

    private Long id;
    private String number;
    private Date dateOfOrder;
    private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();

    @Transient
    public String getEntityName() { return entityName; }
    public static void setEntityName(String entityName) { Order.entityName = entityName; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    @Temporal(TemporalType.DATE)
    public Date getDateOfOrder() { return dateOfOrder; }
    public void setDateOfOrder(Date dateOfOrder) { this.dateOfOrder = dateOfOrder; }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public List<OrderProduct> getOrderProduct() { return orderProduct; }
    public void setOrderProduct(List<OrderProduct> orderProduct) { this.orderProduct = orderProduct; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return new EqualsBuilder()
                .append(getId(), order.getId())
                .append(getNumber(), order.getNumber())
                .append(getDateOfOrder(), order.getDateOfOrder())
                .append(getOrderProduct(), order.getOrderProduct())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }

}