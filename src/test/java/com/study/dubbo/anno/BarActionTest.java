package com.study.dubbo.anno;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 注解配置引用服务
 * @author Lenovo
 *
 */
public class BarActionTest {
	
	private static final String CONSUMER_ANNO_XML = "/consumer-anno.xml";

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext  = new ClassPathXmlApplicationContext(new String[]{CONSUMER_ANNO_XML});
        applicationContext.start();
        BarAction bar = (BarAction) applicationContext.getBean("barAction");
        bar.say();
	}

}
