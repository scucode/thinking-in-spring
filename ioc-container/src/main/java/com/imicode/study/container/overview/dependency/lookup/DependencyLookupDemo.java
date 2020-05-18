package com.imicode.study.container.overview.dependency.lookup;

import com.imicode.study.container.overview.annotation.Super;
import com.imicode.study.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {

    public static void main(String[] args) {
//        上下文初始化
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

//        1. 实时查找
//        lookupInRealTime(beanFactory);
//        2. 延时查找
//        lookupInLazy(beanFactory);
//        3.通过类型查找集合对象
        lookupCollectionByType(beanFactory);
//        4. 通过annotation集合对象
        lookupByAnnotationType(beanFactory);

    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找所有标注了 @Super 的集合对象: " + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的User集合对象: " + users);
        }
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延时查找: " + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("实时查找: " + user);
    }
}
