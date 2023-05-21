package com.ordermanagement.OM.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    @Id
    @Column(name = "order_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @ManyToOne
    @JoinColumn(name="order_code", nullable=false)
    private Order orderCode;

    @Column(name = "item_code", length = 50, nullable = false)
    private String itemGeneratedCode;

    @Column(name = "item_purchased_price", length = 10, nullable = false)
    private float itemPurchasedPrice;

    @Column(name = "quantity", length = 10, nullable = false)
    private float quantity;

}
