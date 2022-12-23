package com.tesi.kcspringboot.controller;

import com.tesi.kcspringboot.service.KcService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private final KcService kcService;

    public UserController(KcService kcService) {
        this.kcService = kcService;

    }

   @PreAuthorize("hasRole('administrator')")
    @GetMapping(value = "/getUsers")
    public ResponseEntity<?> getUsers(HttpServletRequest request) {
//       KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
//       AuthorizationContext authorizationContext = keycloakSecurityContext.getAuthorizationContext();
//      String realm = keycloakSecurityContext.getRealm();
//
          KeycloakAuthenticationToken AuthenticationToken = (KeycloakAuthenticationToken) request.getUserPrincipal();
         KeycloakPrincipal principal = (KeycloakPrincipal) AuthenticationToken.getPrincipal();
       String realmRepresentation = principal.getKeycloakSecurityContext().getRealm();
       List<UserRepresentation> listUsers = kcService.getUsersFromToken(realmRepresentation);

//          List<UserRepresentation> listUsers = kcService.getUsersFromToken(realm);
////
   return new ResponseEntity<>(kcService.ConvertUserReprToUSerDTO(listUsers), HttpStatus.OK);
     //  return new ResponseEntity<>(listUsers, HttpStatus.OK);
  //return new ResponseEntity<>(userRepresentations, HttpStatus.OK);



    }


}

