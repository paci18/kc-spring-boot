package com.tesi.kcspringboot.controller;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    //
    @Value("${keycloak.auth-server-url}")
    private String serverUrl;
    //
    @Value("${keycloak.resource}")
    private String resource;
    //
    @Value("${keycloak.credentials.secret}")
    private String credentials_secret;
    //
    @Value(("${keycloak.realm}"))
    private String realm;

    //TODO da rimuovere

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("Hi!", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('administrator')")
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)

    // this method can be accessed by user whose  role is admin
    public ResponseEntity<?> getUsers(HttpServletRequest request) {

        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String a = principal.getName();
        String username = accessToken.getPreferredUsername();
        String email = accessToken.getEmail();
        String lastName = accessToken.getFamilyName();
        String firstName = accessToken.getGivenName();
        String realmNane = accessToken.getIssuer();
        AccessToken.Access realmAccess = accessToken.getRealmAccess();
        String role = realmAccess.getRoles().toString();

        //to manage the string also null
        // if ((StringUtils.equals(role, match))) {
        //    Keycloak keycloak = Keycloak.getInstance(serverUrl,realmNane, username, resource, credentials_secret );
        //   List<UserRepresentation> listUsers = keycloak.realm(realmNane).users().list();
        //    RealmRepresentation realmRepr = (RealmRepresentation) keycloak.realm(realmNane).toRepresentation().getUsers();
        //   List<UserRepresentation> realmRepresentation =  keycloak.realm(realm).toRepresentation().getUsers();
        // list.add(response)
//
        //  listUsers.add((UserRepresentation) keycloak.realm(realm).toRepresentation().getUsers());

//            UserDTO user1 = new UserDTO("Simone", "Pacileo", "simone.pacileo@piksel.com", "", "");
//            UserDTO user2 = new UserDTO("Noemi", "Santamaria", "noemi.santamaria@piksel.com", "", "");
//            UserDTO user3 = new UserDTO("Marco", "Amenta", "marco.amenta@piksel.com", "", "");
//            listUser.add(user1);
//            listUser.add(user2);
//            listUser.add(user3);

        //       return new ResponseEntity<>("Users", HttpStatus.OK);


        return new ResponseEntity<>("Ciao utente: \n " +
                " username: " + username + "\n" +
                " email:  " + email + "\n" +
                " role:  " + role + "\n", HttpStatus.OK);


    }


}

