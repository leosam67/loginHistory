package ru.leosam.task4.loginhistory.checkers;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LoginChecker {
}
