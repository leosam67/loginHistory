package ru.leosam.task4.loginhistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.checkers.LoginChecker;
import ru.leosam.task4.loginhistory.checkers.LoginCheckerInt;
import ru.leosam.task4.loginhistory.checkers.UserChecker;
import ru.leosam.task4.loginhistory.checkers.UserCheckerInt;

@Component
@ComponentScan("ru.leosam.task4.loginhistory.checkers")
public class CheckersCollection implements BeanPostProcessor {
    private CheckerExecutor executor;
    @Autowired
    public void setExecutor(CheckerExecutor executor) {
        System.out.println("+ CheckerExecutor is set to " + executor.getClass().getName());
        this.executor = executor;
    }
    /*
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // System.out.println("+++ " + beanName);
        if (bean.getClass().isAnnotationPresent(UserChecker.class)) {
            System.out.println("+ Adding UserChecker: " + beanName);
            executor.addUserChecker((UserCheckerInt) bean);
        }
        if (bean.getClass().isAnnotationPresent(LoginChecker.class)) {
            System.out.println("+ Adding LoginChecker: " + beanName);
            executor.addLoginChecker((LoginCheckerInt) bean);
        }
        return bean;
    }
    */
}
