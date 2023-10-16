package com.businesscenterservices.businesscenterservices.services;

import com.businesscenterservices.businesscenterservices.entities.Roles;
import com.businesscenterservices.businesscenterservices.entities.Users;

import java.util.List;


public interface UserServices {
    Users  addNewUser(Users users);
    Roles addNewRole(Roles roles);
    List<Roles>  getAllRole();
    void AddRoleToUsers(String nomUser, String roleName);
    void addAllRole(List<Roles> roles);
    Users loadUserByNomUser(String nomUser);
    List<Users> getUsersByRole(String roleName);
    Roles findRolesByNom(String roleName);
    List<Users> listUsers();
    Users getUserByID(Long id);

}
