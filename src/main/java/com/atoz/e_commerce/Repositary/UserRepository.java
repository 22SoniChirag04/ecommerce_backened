package com.atoz.e_commerce.Repositary;

import com.atoz.e_commerce.Dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    // JpaRepository provides default implementations for common CRUD operations
}
