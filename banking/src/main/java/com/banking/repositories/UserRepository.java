package com.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
