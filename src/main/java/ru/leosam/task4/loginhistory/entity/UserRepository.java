package ru.leosam.task4.loginhistory.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u from users u where u.UserName = :userId", nativeQuery = true)
    Optional<User> findByUserId(String userId);
    @Query(value = "insert into users(username, fio) values(:userName, :FIO)", nativeQuery = true)
    void insertUser(@Param("userName") String userName, @Param("FIO") String fio);
}
