package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PCTrack.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
