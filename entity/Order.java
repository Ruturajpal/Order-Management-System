package com.ordermanagement.OM.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @Column(name = "order_code", length = 50, nullable = false)
    private String orderGeneratedCode;

    @Column(name = "is_a_registered_customer", length = 8)
    private boolean isARegisteredCustomer;

    @Column(name = "customer_id", length = 8)
    private String customerRegId;

    @Column(name = "total_purchase", length = 15, nullable = false)
    private float totalPurchase;

    @Column(name = "date", length = 15, nullable = false)
    private String date;

    @Column(name = "time", length = 15, nullable = false)
    private String time;

    @OneToMany(mappedBy="orderCode")
    private Set<OrderDetails> orderDetails;

    public Order(String orderGeneratedCode, boolean isARegisteredCustomer, String customerRegId, float totalPurchase, String date, String time) {
        this.orderGeneratedCode = orderGeneratedCode;
        this.isARegisteredCustomer = isARegisteredCustomer;
        this.customerRegId = customerRegId;
        this.totalPurchase = totalPurchase;
        this.date = date;
        this.time = time;
    }

}
