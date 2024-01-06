package com.cherniavskyi.shop.entity.user.customer;

import com.cherniavskyi.shop.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer_detail", schema = "shop")
@EntityListeners(AuditingEntityListener.class)
public class CustomerDetail {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "create_account", nullable = false, updatable = false)
    private Date createAccount;

}
