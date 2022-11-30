package com.tesi.kcspringboot.controller;

import com.tesi.kcspringboot.service.KcService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

     private  final KcService kcService;

    public UserController(KcService kcService) {
        this.kcService = kcService;
    }
    //
//    @Value("${keycloak.auth-server-url}")
//    private String serverUrl;
//    //
//    @Value("${keycloak.resource}")
//    private String resource;
//    //
//    @Value("${keycloak.credentials.secret}")
//    private String credentials_secret;
//    //
//    @Value(("${keycloak.realm}"))
//    private String realm;

    //TODO da rimuovere

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("Hi!", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('administrator')")
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)


    // this method can be accessed by user whose  role is admin
    public ResponseEntity<?> getUsers(HttpServletRequest request) {


        KeycloakAuthenticationToken AuthenticationToken = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) AuthenticationToken.getPrincipal();
        AccessToken Accesstoken = principal.getKeycloakSecurityContext().getToken();
        String realmRepresentation = principal.getKeycloakSecurityContext().getRealm();

        List<UserRepresentation> listUsers = kcService.getUsersFromToken(realmRepresentation);

        //System.out.println("ciao");
//            UserDTO user1 = new UserDTO("Simone", "Pacileo", "simone.pacileo@piksel.com", "", "");
//            UserDTO user2 = new UserDTO("Noemi", "Santamaria", "noemi.santamaria@piksel.com", "", "");
//            UserDTO user3 = new UserDTO("Marco", "Amenta", "marco.amenta@piksel.com", "", "");
//            listUser.add(user1);
//            listUser.add(user2);
//            listUser.add(user3);

        //       return new ResponseEntity<>("Users", HttpStatus.OK);


        //return new ResponseEntity<>("Ciao utente admin" ,HttpStatus.OK);
     //   return  new ResponseEntity<>( listUser, HttpStatus.OK);
      return new ResponseEntity<>(listUsers, HttpStatus.OK);


    }


}

