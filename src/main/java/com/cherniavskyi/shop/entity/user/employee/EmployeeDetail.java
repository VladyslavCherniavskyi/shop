package com.cherniavskyi.shop.entity.user.employee;

import com.cherniavskyi.shop.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_detail", schema = "shop")
public class EmployeeDetail {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private EmployeePosition position;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date", nullable = false, updatable = false)
    private Date hireDate;

}
