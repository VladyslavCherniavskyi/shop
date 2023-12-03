package com.cherniavskyi.shop.entity.order;

import com.cherniavskyi.shop.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(OrderDetailKey.class)
@Table(name = "order_detail", schema = "shop")
public class OrderDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderId;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    public OrderDetailKey getOrderDetailKey() {
        return new OrderDetailKey(orderId.getId(), productId.getId());
    }
}
