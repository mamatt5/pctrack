package com.fdmgroup.PCTrack.dal;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.PCTrack.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
