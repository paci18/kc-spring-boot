package com.tesi.kcspringboot.model;


import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String firstName;

    private String lastName;
    private String email;
    private String userName;
    private String password;
}
