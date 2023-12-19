package com.bikkadit.electoronic.store.repository;

import com.bikkadit.electoronic.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByEmail(String email);

    List<User> findByNameContaining(String keyword);

    Optional<User> findByEmailAndPassword(String email,String password);

}
