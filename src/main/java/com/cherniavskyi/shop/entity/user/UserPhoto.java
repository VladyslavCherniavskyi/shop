package com.cherniavskyi.shop.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_photo", schema = "shop")
public class UserPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "size", nullable = false)
    private Long size;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

}
