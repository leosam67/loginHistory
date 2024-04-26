package ru.leosam.task4.loginhistory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Component
@Table(name="logins")
@Data
@AllArgsConstructor
// @NoArgsConstructor
public class Login {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "Access_Date")
        private Date accessDate;
        @Column(name = "User_ID")
        private Integer userId;
        @Column(name = "Application")
        private String application;

        public Login(Date accessDate, Integer userId, String application) {
            this.accessDate = accessDate;
            this.userId = userId;
            this.application = application;
        }
        public Login() {
            System.out.println("+ ! Login is created");
            for(StackTraceElement el : Thread.currentThread().getStackTrace()) {
                System.out.println("\t" + el.toString());
            }
        }
}
