package com.tesi.kcspringboot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tesi.kcspringboot.DTO.UserDTO;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class KcService {


    private final Keycloak keycloakService;

    public KcService(Keycloak keycloakService) {
        this.keycloakService = keycloakService;
    }

    //    public  List<String> getUsersFromToken( String realm) {
//        List<String> listOfUsers = new ArrayList<>();
//        listOfUsers.add("Simone Pacileo");
//        listOfUsers.add("Marco Amenta");
//        listOfUsers.add("Noemi Santamaria");
    public List<UserRepresentation> getUsersFromToken(String realm) {
        List<UserRepresentation> listOfUsersWithManyInformation = keycloakService.realm(realm).users().list();


//
////
//        for (int i =0; i<listOfUsers.size(); i++){
//            System.out.println(listOfUsers.get(i));
//        }
//
//
        return listOfUsersWithManyInformation;
    }

//            for (int i=0; i<listUsers.size(); i++) {
//                System.out.println(listUsers.get(i));
//            }
//
    //return listOfUsersWithManyInformation;
    //}


    public List<String> listaStringheUsers(List<UserRepresentation> listUsers) {
        List<String> listOfUsers = new ArrayList<>();
        for (UserRepresentation listUser : listUsers) {
            listOfUsers.add(listUser.getUsername());
            listOfUsers.add(listUser.getFirstName());
            listOfUsers.add(listUser.getLastName());
            listOfUsers.add(listUser.getEmail());
        }

        return listOfUsers;
    }


    public List<UserDTO> ConvertUserReprToUSerDTO(List<UserRepresentation> userRepresentation) {
        TypeReference<List<UserDTO>> typeReference = new TypeReference<>() {
        };
        return JsonMapper.builder().build().convertValue(userRepresentation, typeReference);

    }
}
