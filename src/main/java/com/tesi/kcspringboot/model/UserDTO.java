package com.tesi.kcspringboot.model;


import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
