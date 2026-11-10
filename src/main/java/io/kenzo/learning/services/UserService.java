package io.kenzo.learning.services;

import io.kenzo.learning.exception.ModelNotFoundException;
import io.kenzo.learning.model.User;
import io.kenzo.learning.repositories.UserRepository;
import io.kenzo.learning.viewModels.DashboardStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("User not found"));
    }

    public User store(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        User record = findById(user.getId());
        record.setName(user.getName());
        record.setEmail(user.getEmail());
        record.setPhone(user.getPhone());
        record.setAddress(user.getAddress());
        record.setIsActive(user.getIsActive());
        return userRepository.save(record);
    }

    public void delete(UUID id) {
        User record = findById(id);
        userRepository.delete(record);
    }

    /** True if another user (other than {@code excludeId}, may be null) already uses this email. */
    public boolean emailExists(String email, UUID excludeId) {
        if (email == null || email.isBlank()) return false;
        return excludeId == null
                ? userRepository.countByEmail(email) > 0
                : userRepository.countByEmailAndIdNot(email, excludeId) > 0;
    }

    public boolean phoneExists(String phone, UUID excludeId) {
        if (phone == null || phone.isBlank()) return false;
        return excludeId == null
                ? userRepository.countByPhone(phone) > 0
                : userRepository.countByPhoneAndIdNot(phone, excludeId) > 0;
    }

    public DashboardStats dashboardStats() {
        return new DashboardStats(
                userRepository.count(),
                userRepository.countByIsActive(true),
                userRepository.countByIsActive(false),
                userRepository.countByCreatedAtGreaterThanEqual(LocalDate.now().atStartOfDay())
        );
    }

    public List<User> recentUsers() {
        return userRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
