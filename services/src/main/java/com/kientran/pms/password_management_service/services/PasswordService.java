package com.kientran.pms.password_management_service.services;

import com.kientran.pms.password_management_service.entities.Password;
import com.kientran.pms.password_management_service.repositories.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public List<Password> getAll(String username, String domain) {
        if (username == null) {
            throw new NullPointerException();
        }

        if (domain == null) {
            return passwordRepository.findByUsername(username);
        } else {
            return passwordRepository.findByUserNameAndDomain(username, domain);
        }
    }

    public Password getPersonById(Long id) {
        return passwordRepository.findById(id).get();
    }

    public void saveOrUpdate(Password password) {
        passwordRepository.save(password);
    }

    public void delete(Long id) {
        passwordRepository.deleteById(id);
    }
}