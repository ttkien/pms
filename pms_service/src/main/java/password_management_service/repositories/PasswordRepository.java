package password_management_service.repositories;

import password_management_service.entities.Password;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
    List<Password> findByUsername(@Param("username") String username);
    List<Password> findByUsernameAndDomain(@Param("username") String username,
                                           @Param("domain") String domain);
}
