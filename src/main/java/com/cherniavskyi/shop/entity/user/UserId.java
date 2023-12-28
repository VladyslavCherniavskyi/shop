package com.cherniavskyi.shop.entity.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserId implements Serializable {

    private Long id;
    private String email;

}
