package ru.leosam.task4.loginhistory.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    @Modifying
    @Query(value = "insert into logins(access_date, user_id, application) values(:access_date, :user_id, :application)", nativeQuery = true)
    @Transactional
    Integer insertLogin(
            @Param("access_date") Date accessDate,
            @Param("user_id") Integer userId,
            @Param("application") String application);
}
