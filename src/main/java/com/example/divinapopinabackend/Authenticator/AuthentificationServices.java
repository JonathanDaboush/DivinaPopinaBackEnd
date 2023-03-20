package com.example.divinapopinabackend.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthentificationServices {
    @Autowired
    AuthentificationRepository authentificationRepository;

    public Authentification getauthentificationById(long id){
        return authentificationRepository.getReferenceById(id);
    }

    public Authentification getauthentificationByName(String password){
        return authentificationRepository.findByAuthentificationPassword(password).get(0);
    }
    public void saveauthentification(Authentification authentification){
        authentificationRepository.save(authentification);
    }
    public void removeauthentification(long id){
        authentificationRepository.deleteById(id);
    }
    public List<Authentification> getauthentifications(){
        return authentificationRepository.findAll();
    }
}
