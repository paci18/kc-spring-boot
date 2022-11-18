package com.tesi.kcspringboot.controller;

import com.tesi.kcspringboot.model.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    //TODO da rimuovere
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("Hi!", HttpStatus.OK);
    }

@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(){

        //UserRepresentation userRepresentation = new UserRepresentation();
        List<UserDTO> listUser = new ArrayList<>();
    UserDTO user1 = new UserDTO("Simone", "Pacileo", "simone.pacileo@piksel.com" , "", "" );
    UserDTO user2 = new UserDTO("Noemi", "Santamaria", "noemi.santamaria@piksel.com" , "", "");
    UserDTO user3 = new UserDTO("Marco", "Amenta", "marco.amenta@piksel.com", "", "");
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);



        return new ResponseEntity<>(listUser,HttpStatus.OK);

    }

}


