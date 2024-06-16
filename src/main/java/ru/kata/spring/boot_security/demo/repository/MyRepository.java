package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

public interface MyRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id")
//    Optional<User> findById(@Param("id") Long id);
}
