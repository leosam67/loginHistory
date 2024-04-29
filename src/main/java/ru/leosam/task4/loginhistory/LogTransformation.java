package ru.leosam.task4.loginhistory;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LogTransformation {
    // TODO: Does is work with @Value()?
    String value() default "C:/temp/LogTransformation.txt";
}
