package com.businesscenterservices.businesscenterservices.fixtures;

import com.businesscenterservices.businesscenterservices.entities.Roles;
import com.businesscenterservices.businesscenterservices.entities.Users;
import com.businesscenterservices.businesscenterservices.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class UtilisateurFixtures {

    private UserServicesImpl userServices;

    @Autowired
    private PasswordEncoder bCrypPasswordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UtilisateurFixtures(@Lazy PasswordEncoder bCrypPasswordEncoder, UserServicesImpl userServices) {
        this.bCrypPasswordEncoder = bCrypPasswordEncoder;
        this.userServices = userServices;
    }

    public void addDefaultRoles() {
        List<Roles> rList = userServices.getAllRole();
        if (rList == null || rList.isEmpty()) {
            Roles[] roles = {
                    new Roles(null, "SUPER_ADMIN"),
                    new Roles(null, "ADMIN"),
                    new Roles(null, "AGENTS")
            };
            userServices.addAllRole(Arrays.asList(roles));
        }
    }

    public void addDefaultSuperAdmin() {
        // Parse the date string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date birthDate = null;
        try {
            birthDate = dateFormat.parse("12-02-1995");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Roles roles = userServices.findRolesByNom("SUPER_ADMIN");
        List<Users> users =  userServices.getUsersByRole("SUPER_ADMIN");
        if (roles != null && users.size() <= 0) {
            Users defaultUser = new
                    Users(null, "Admin",
                    "Admin", "THies/Mbour 1",
                    "778558952", birthDate,
                    "4586-5852-3689-2564", passwordEncoder().encode("passer@123"),
                    "admin@gmail.com", true,
                    false, true, roles);
            userServices.addNewUser(defaultUser);
            System.out.println("default admin added successfully");
        }
    }

}
