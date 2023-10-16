package com.businesscenterservices.businesscenterservices.services;

import com.businesscenterservices.businesscenterservices.entities.Roles;
import com.businesscenterservices.businesscenterservices.entities.Users;
import com.businesscenterservices.businesscenterservices.repositories.RoleRepository;
import com.businesscenterservices.businesscenterservices.repositories.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log
public class UserServicesImpl implements UserServices {
    private UsersRepository usersRepository;
    private RoleRepository roleRepository;

    public UserServicesImpl(UsersRepository usersRepository, RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Users addNewUser(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public Roles addNewRole(Roles roles) {
        return roleRepository.save(roles);
    }

    @Override
    public List<Roles>  getAllRole() {
        return  roleRepository.findAll();
    }

    @Override
    public void AddRoleToUsers(String nomUser, String roleName) {
         Users user=usersRepository.findByNomUser(nomUser);
         Roles role=roleRepository.findByNomRoles(roleName);
            user.setRoles(role);
    }

    @Override
    public void addAllRole(List<Roles> roles) {
        roleRepository.saveAll(roles);
    }

    @Override
    public Users loadUserByNomUser(String nomUser) {
            return usersRepository.findByNomUser(nomUser);
    }

    @Override
    public List<Users> getUsersByRole(String roleName) {
        return  usersRepository.findByRole(roleName).orElse(new ArrayList<>());
    }

    @Override
    public Roles findRolesByNom(String roleName) {
        return roleRepository.findByNomRoles(roleName);
    }

    @Override
    public List<Users> listUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserByID(Long id) {
        return usersRepository.findByIdUsers(id);
    }


    public Users connectedUser() {
            try {
                SecurityContext context = SecurityContextHolder.getContext();
                Authentication authentication = context.getAuthentication();
                String login = "";
                if (authentication.getPrincipal() instanceof UserDetails) {
                    UserDetails user = (UserDetails) authentication.getPrincipal();
                    login = user.getUsername();
                }
                if (authentication.getPrincipal() instanceof String)
                    login = (String) authentication.getPrincipal();
                return usersRepository
                        .findByEmailUser(login);
                        //.orElse(null);
            } catch (Exception e) {
                log.severe(e.getLocalizedMessage());
                throw e;
            }
    }
}
