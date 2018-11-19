package com.kientran.pms.authentication_service.repository_interfaces;

import com.kientran.pms.authentication_service.entities.User;

public interface UserRepositoryInterface {
    User getUser(Long userId);
    User getUser(String token);
    User getUser(String username, String passwordHash);
}
