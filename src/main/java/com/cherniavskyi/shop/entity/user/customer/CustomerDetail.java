package com.cherniavskyi.shop.entity.user.customer;

import com.cherniavskyi.shop.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer_detail", schema = "shop")
public class CustomerDetail {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "create_account")
    private Date createAccount;

}
