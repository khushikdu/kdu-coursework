package com.example.springjpa.service;

import com.example.springjpa.entity.User;
import com.example.springjpa.exception.custom.CustomException;
import com.example.springjpa.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * to save a user
     * @param user The user to be saved.
     * @throws CustomException If an error occurs during the user saving process.
     */
    @Transactional
    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException("Failed to save user.");
        }
    }

    /**
     * to retrieve all users belonging to a specific tenant
     * @param tenantId The ID of the tenant.
     * @return The list of users belonging to the specified tenant.
     */
    public List<User> getUsers(UUID tenantId) {
        // Assuming you have a method in UserRepository to fetch users by tenantId
        return userRepository.findByTenantId(tenantId);
    }

    /**
     * to retrieve a user by ID.
     * @param userId The ID of the user to retrieve.
     * @return The user with the specified ID, if found; otherwise, null.
     */
    public User getUserById(UUID userId){
        return userRepository.findById(userId).orElse(null);
    }

    /**
     * to update a user
     * @param userId The ID of the user to update.
     * @param user   The updated user object.
     * @throws CustomException If an error occurs during the user update process or if the user with the given ID does not exist.
     */
    @Transactional
    public void updateUser(UUID userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Update only if the user exists
            existingUser.setUsername(user.getUsername());
            existingUser.setTenant(user.getTenant());
            existingUser.setUpdatedBy(user.getUpdatedBy());
            existingUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(existingUser);
        } else {
            throw new CustomException("User with ID " + userId + " does not exist.");
        }
    }
    public int updateUserDetails(UUID userId, User user) {
        return userRepository.updateUserDetails(user.getUsername(),user.getTimeZone(),userId);
    }
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
