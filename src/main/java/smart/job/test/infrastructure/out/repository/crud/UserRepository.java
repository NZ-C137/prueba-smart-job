package smart.job.test.infrastructure.out.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity getByEmail(String email);

}
