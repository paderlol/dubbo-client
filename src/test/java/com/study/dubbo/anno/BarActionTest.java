package com.study.dubbo.anno;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 注解配置引用服务
 * @author Lenovo
 *
 */
public class BarActionTest {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext  = new ClassPathXmlApplicationContext(new String[]{"/consumer-anno.xml"});
        applicationContext.start();
        BarAction bar = (BarAction) applicationContext.getBean("barAction");
        bar.say();
	}

}
