package com.lalyns.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lalyns.board.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public boolean existsByUsername(String username);

    public Optional<User> findByUsername(String username);

}
