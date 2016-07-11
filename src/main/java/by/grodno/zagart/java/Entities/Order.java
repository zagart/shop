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
    private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    @Temporal(TemporalType.DATE)
    public Date getDateOfOrder() { return dateOfOrder; }
    public void setDateOfOrder(Date dateOfOrder) { this.dateOfOrder = dateOfOrder; }

    @OneToMany(mappedBy = "order")
    public List<OrderProduct> getOrderProduct() { return orderProduct; }
    public void setOrderProduct(List<OrderProduct> orderProduct) { this.orderProduct = orderProduct; }

}
