package ru.leosam.task4.loginhistory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "users")
@Data
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "UserName")
    String userId;
    @Column(name = "FIO")
    String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
