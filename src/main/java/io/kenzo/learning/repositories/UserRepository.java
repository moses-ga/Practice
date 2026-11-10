package io.kenzo.learning.repositories;

import io.kenzo.learning.model.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends DataTablesRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    long countByEmail(String email);

    long countByEmailAndIdNot(String email, UUID id);

    long countByPhone(String phone);

    long countByPhoneAndIdNot(String phone, UUID id);

    long countByIsActive(boolean isActive);

    long countByCreatedAtGreaterThanEqual(LocalDateTime from);

    List<User> findTop5ByOrderByCreatedAtDesc();
}
