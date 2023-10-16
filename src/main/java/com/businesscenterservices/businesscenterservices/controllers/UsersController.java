package com.businesscenterservices.businesscenterservices.controllers;

import com.businesscenterservices.businesscenterservices.controllers.Exceptions.EntityNotFoundException;
import com.businesscenterservices.businesscenterservices.entities.Users;
import com.businesscenterservices.businesscenterservices.services.UserServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    private UserServicesImpl userServices;


    public UsersController(UserServicesImpl userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/api/AjoutUsers")
    public void ajoutUsers(@RequestBody Users users) {
        this.userServices.addNewUser(users);
    }

    @GetMapping("/api/listeUser")
    public List<Users> listeUsers() {
        return this.userServices.listUsers();
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long id) throws EntityNotFoundException {
        try {
            Users users = userServices.getUserByID(id);
            // Vérifiez si l'utilisateur existe
            if (users != null) {
                return ResponseEntity.ok().body(users);
            } else {
                throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID :: " + id);
            }
        } catch (Exception e) {
            // Gérez les exceptions ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
