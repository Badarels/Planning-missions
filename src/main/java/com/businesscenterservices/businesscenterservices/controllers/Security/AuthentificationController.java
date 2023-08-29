package com.businesscenterservices.businesscenterservices.controllers.Security;


import com.businesscenterservices.businesscenterservices.Jwtutils.JwtRequestModel;
import com.businesscenterservices.businesscenterservices.Jwtutils.JwtResponsModel;
import com.businesscenterservices.businesscenterservices.Jwtutils.JwtUserDetailsService;
import com.businesscenterservices.businesscenterservices.Jwtutils.TokenManager;
import com.businesscenterservices.businesscenterservices.controllers.Exceptions.BadRequestException;
import com.businesscenterservices.businesscenterservices.entities.Users;
import com.businesscenterservices.businesscenterservices.services.UserServicesImpl;
import com.businesscenterservices.businesscenterservices.services.Utilitaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthentificationController {

    private  JwtUserDetailsService usersDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private TokenManager tokenManager;

    private UserServicesImpl utilisateurService;

    private Utilitaire utilitaire;


        public AuthentificationController(JwtUserDetailsService usersDetailsService, TokenManager tokenManager, UserServicesImpl utilisateurService, Utilitaire utilitaire) {
            this.usersDetailsService = usersDetailsService;
            this.tokenManager = tokenManager;
            this.utilisateurService = utilisateurService;
            this.utilitaire = utilitaire;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new BadRequestException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new BadRequestException("INVALID_CREDENTIALS");
        }
        final UserDetails userDetails = usersDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponsModel(jwtToken));
    }

        @GetMapping("/api/connected-user")
    public MappingJacksonValue connectedUser() {
        try {
            Users utilisateur = utilisateurService.connectedUser();
            return utilitaire.getFilter(utilisateur, "passwordFilter", "password");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
