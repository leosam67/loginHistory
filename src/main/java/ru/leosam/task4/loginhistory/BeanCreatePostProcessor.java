package ru.leosam.task4.loginhistory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Date;

@Component
public class BeanCreatePostProcessor implements BeanPostProcessor {
    public String listParams(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (!sb.isEmpty()) sb.append(", ");
            sb.append(arg.toString());
        }
        return sb.toString();
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().isAnnotationPresent(LogTransformation.class)) return bean;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        String logFileName = bean.getClass().getAnnotation(LogTransformation.class).value();
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if (args.length > 0) {
                    try (PrintStream log = new PrintStream(new FileOutputStream(logFileName, true))) {
                        log.append(String.valueOf(new Date()))
                                .append(" ")
                                .append(bean.getClass().getName())
                                .append("\n");
                        log.append("\t").append(listParams(args)).append(") =>\n");
                    }
                }
                Object result = method.invoke(bean, args);
                if (args.length > 0) {
                    try (PrintStream log = new PrintStream(new FileOutputStream(logFileName, true))) {
                        log.append("\t").append(listParams(args)).append("\n");
                    }
                }
                return result;
            }
        });
        // Indeed it is a wrong method to choose constructor, but to way to make a choice...
        Constructor<?> cons = bean.getClass().getConstructors()[0];
        Object[] arr = new Object[cons.getParameterCount()];
        return enhancer.create(cons.getParameterTypes(), arr);
    }
}
