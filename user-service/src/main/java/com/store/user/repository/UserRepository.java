package com.store.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.user.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByUserId(Long userId);

}
