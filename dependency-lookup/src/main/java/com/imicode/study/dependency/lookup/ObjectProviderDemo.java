package com.imicode.study.dependency.lookup;

import com.imicode.study.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 通过 ObjectProvider 查找依赖对象
//        lookupByObjectProvider(applicationContext);
        // 延迟加载
//        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider= applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterator = objectProvider;
//        for ( String str : stringIterator) {
//            System.out.println(str);
//        }
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider= applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(() -> User.createUser());
        System.out.println("当前 User 对象 : " + user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }


    @Bean
    @Primary
    public String helloWorld() {
        return "Hello, World";
    }

    @Bean
    public String message() {
        return "message";
    }
}
