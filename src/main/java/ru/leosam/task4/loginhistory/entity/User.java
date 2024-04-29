package ru.leosam.task4.loginhistory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "users")
@Data
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "user_name")
    String userId;
    @Column(name = "fio")
    String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
