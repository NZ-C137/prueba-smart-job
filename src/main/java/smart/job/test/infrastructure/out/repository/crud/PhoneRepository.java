package smart.job.test.infrastructure.out.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.job.test.infrastructure.out.repository.entity.PhoneEntity;

public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {
}
