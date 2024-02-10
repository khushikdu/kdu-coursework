package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> getUsersByUsername(String username);
}
