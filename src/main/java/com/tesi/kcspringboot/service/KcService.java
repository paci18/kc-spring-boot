package com.tesi.kcspringboot.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KcService {


         private final  Keycloak keycloakService ;

    public KcService(Keycloak keycloakService) {
        this.keycloakService = keycloakService;
    }

//    public  List<String> getUsersFromToken( String realm) {
//        List<String> listOfUsers = new ArrayList<>();
//        listOfUsers.add("Simone Pacileo");
//        listOfUsers.add("Marco Amenta");
//        listOfUsers.add("Noemi Santamaria");
   public  List<UserRepresentation> getUsersFromToken( String realm) {
      List<UserRepresentation> listOfUsersWithManyInformation= keycloakService.realm(realm).users().list();
//      List<String> listOfUsers = new ArrayList<>();
//
//      for (int i = 0; i<listOfUsersWithManyInformation.size(); i++){
//            listOfUsers.add(listOfUsersWithManyInformation.get(i).getUsername());
//          listOfUsers.add(listOfUsersWithManyInformation.get(i).getFirstName());
//          listOfUsers.add(listOfUsersWithManyInformation.get(i).getLastName());
//          listOfUsers.add(listOfUsersWithManyInformation.get(i).getEmail());
//      }
//
//       List<UserRepresentation> prova = new ArrayList<>();
//       for (int i = 0; i<listOfUsers.size(); i++){
//           prova.subList(i, prova.size());
//
//
       return  listOfUsersWithManyInformation;
}

//            for (int i=0; i<listUsers.size(); i++) {
//                System.out.println(listUsers.get(i));
//            }
//
            //return listOfUsersWithManyInformation;
        //}

}
