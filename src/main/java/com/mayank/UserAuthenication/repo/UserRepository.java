package com.mayank.UserAuthenication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayank.UserAuthenication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByEmail(String email);

    User findByMobile(String mobile);
    
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);

    // Other custom query methods if needed
}

