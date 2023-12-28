package com.cherniavskyi.shop.entity.user.employee;

import com.cherniavskyi.shop.entity.user.UserGender;
import com.cherniavskyi.shop.entity.user.UserPhoto;
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
@Table(name = "employee", schema = "shop")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_generator", sequenceName = "employee_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false, updatable = false)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private UserGender gender;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private EmployeePosition position;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "hire_date", nullable = false, updatable = false)
    private Date hireDate;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private UserPhoto photo;
}
