package com.imicode.study.bean.definition;

import com.imicode.study.bean.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        //1. 通过BeanDefinitionBuilder创建BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
//        beanDefinitionBuilder.addPropertyValue("id", "1");
//        beanDefinitionBuilder.addPropertyValue("name", "李四");
        beanDefinitionBuilder
                .addPropertyValue("id", "1")
                .addPropertyValue("name", "李四");

        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println("方法一: " + beanDefinition);

        //2. 通过 AbstractBeanDefinition 以及派生类创建BeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        mutablePropertyValues
//                .add("id", "1")
//                .add("name", "王五");
        mutablePropertyValues.addPropertyValue("id", "1");
        mutablePropertyValues.addPropertyValue("name", "王五");

        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

        System.out.println("方法二: " + genericBeanDefinition);
    }
}
