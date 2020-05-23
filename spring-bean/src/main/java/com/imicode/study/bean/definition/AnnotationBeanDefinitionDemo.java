package com.imicode.study.bean.definition;

import com.imicode.study.bean.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//      注册配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
//      启动 spring 应用上下文
        applicationContext.refresh();

//      通过xml方式注册  －－ 省略
//      通过anotation的方式注册
//      1. @Bean 方式
//      2. @Component方式
//      3. @Import方式

//      通过java api方式注册
//      1. 命名方式
        registerUserBeanDefinition(applicationContext, "iot-user");
//      2. 非命名方式
        registerUserBeanDefinition(applicationContext);

//      依赖查找
        System.out.println("" + applicationContext.getBeansOfType(Config.class));
        System.out.println("" + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder
                .addPropertyValue("id", "1")
                .addPropertyValue("name","iot");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    @Component
    public static class Config {

        @Bean(name = {"user", "imi-user"})
        public User user () {
            User user = new User();
            user.setId(1);
            user.setName("Anotation");
            return user;
        }
    }

}
