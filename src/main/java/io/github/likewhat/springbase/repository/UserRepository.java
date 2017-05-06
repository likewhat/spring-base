package io.github.likewhat.springbase.repository;

import io.github.likewhat.springbase.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * Find a user with given username
     *
     * @param email email used to find user
     * @return The user which email is given email
     */
    User findByEmailAndIsDeletedFalse(String email);

    /**
     * Select all users.
     *
     * @return all users.
     */
    @Query("SELECT u FROM User u WHERE u.isDeleted=0")
    List<User> findAllUsers();
}
