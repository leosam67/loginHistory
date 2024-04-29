
package ru.leosam.task4.loginhistory.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u.id from users u where u.user_name = :user_name", nativeQuery = true)
    @Transactional
    Optional<Integer> findByUserId(@Param("user_name") String userId);
    @Modifying
    @Query(value = "insert into users(user_name, fio) values(:user_name, :fio)", nativeQuery = true)
    @Transactional
    void insertUser(@Param("user_name") String userName, @Param("fio") String fio);
}
