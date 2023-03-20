package com.example.divinapopinabackend.Authenticator;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthentificationRepository extends JpaRepository<Authentification, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM authentification  "
            +  " WHERE password = :key "
            , nativeQuery = true)
    List<Authentification> findByAuthentificationPassword(String key);
}
