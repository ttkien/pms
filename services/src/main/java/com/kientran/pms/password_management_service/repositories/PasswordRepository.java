package com.kientran.pms.password_management_service.repositories;

import com.kientran.pms.password_management_service.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
    List<Password> findByUsername(@Param("username") String username);
    List<Password> findByUserNameAndDomain(@Param("username") String username,
                                           @Param("domain") String domain);
}
