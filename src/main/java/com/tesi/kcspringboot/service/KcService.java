package com.tesi.kcspringboot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tesi.kcspringboot.DTO.UserDTO;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class KcService {


    private final Keycloak keycloakService;
    private  RealmResource keycloakRealmResource;

    public KcService(Keycloak keycloakService) {
        this.keycloakService = keycloakService;
    }

    public List<UserRepresentation> getUsersFromToken(String realm) {

      return   keycloakService.realm(realm).users().list();

        //return keycloakService.realm(realm).users().list();
    }



    public List<UserDTO> ConvertUserReprToUSerDTO(List<UserRepresentation> userRepresentation) {

        TypeReference<List<UserDTO>> typeReference = new TypeReference<>() {
        };
        return JsonMapper.builder().build().convertValue(userRepresentation, typeReference);

    }
}
