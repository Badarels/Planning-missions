package com.businesscenterservices.businesscenterservices.controllers;

import com.businesscenterservices.businesscenterservices.entities.Users;
import com.businesscenterservices.businesscenterservices.services.UserServicesImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController{

    private UserServicesImpl userServices;


    public UsersController(UserServicesImpl userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/api/AjoutUsers")
    public void ajoutUsers(@RequestBody Users users){
        this.userServices.addNewUser(users);
    }

    @GetMapping("/api/listeUser")
    public List<Users> listeUsers(){
        return this.userServices.listUsers();
    }
}
