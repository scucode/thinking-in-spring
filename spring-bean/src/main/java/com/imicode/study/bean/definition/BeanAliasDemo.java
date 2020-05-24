package com.imicode.study.bean.definition;

import com.imicode.study.bean.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Bean 别名示例
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        // 通过name与别名分别获取user实例
        User user = beanFactory.getBean("user", User.class);
        User user2 = beanFactory.getBean("alias-user", User.class);

        System.out.println("user2 是否与 user Bean 相同：" + (user == user2));
    }
}
