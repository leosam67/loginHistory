package ru.leosam.task4.loginhistory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Component
@Table(name="logins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
        private static int cnt = 0;
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "access_date")
        private Date accessDate;
        @Column(name = "user_id")
        private Integer userId;
        @Column(name = "application")
        private String application;

        public Login(Date accessDate, Integer userId, String application) {
            this.accessDate = accessDate;
            this.userId = userId;
            this.application = application;
        }
}
