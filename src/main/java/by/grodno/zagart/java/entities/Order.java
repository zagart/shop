package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.interfaces.Identifiable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity that represents a product.
 */
@Entity
@Table(name = "SHOP_ORDER")
public class Order implements Identifiable<Long> {

    private Long id;
    private String number;
    private Date dateOfOrder;
    private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Column(name = "NUMBER")
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_ORDER")
    public Date getDateOfOrder() { return dateOfOrder; }
    public void setDateOfOrder(Date dateOfOrder) { this.dateOfOrder = dateOfOrder; }

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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
