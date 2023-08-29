package com.businesscenterservices.businesscenterservices.repositories;

import com.businesscenterservices.businesscenterservices.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
   Users findByEmailUser(String emailUser);
   Users findByNomUser(String nomUser);

   @Query("SELECT u FROM Users u WHERE u.archive=false AND u.status=true AND u.roles.nomRoles=:roles")
   Optional<List<Users>> findByRole(@Param("roles") String roles);

}
