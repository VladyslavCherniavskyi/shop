package com.cherniavskyi.shop.entity.order;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDetailKey implements Serializable {

    private Long orderId;
    private Long productId;

}
