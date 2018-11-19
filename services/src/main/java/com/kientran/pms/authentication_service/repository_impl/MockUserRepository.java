package com.kientran.pms.authentication_service.repository_impl;

import com.kientran.pms.authentication_service.entities.User;
import com.kientran.pms.authentication_service.repository_interfaces.UserRepositoryInterface;

public class MockUserRepository implements UserRepositoryInterface {
    public static User DEFAULT_USER = new User(
            1L, "user", "24811cad4fd632353839d4b6c99181fdffc75ed39d4763fd56b0dad9faad03cf", "token"
    );

    @Override
    public User getUser(Long userId) {
        return DEFAULT_USER;
    }

    @Override
    public User getUser(String token) {
        return DEFAULT_USER;
    }

    @Override
    public User getUser(String username, String passwordHash) {
        Boolean isSuccess = DEFAULT_USER.getUsername().equals(username)
                && DEFAULT_USER.getPasswordHash().equals(passwordHash);

        if (isSuccess) {
            return DEFAULT_USER;
        } else {
            return null;
        }
    }
}
