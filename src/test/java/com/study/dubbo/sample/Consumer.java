package com.study.dubbo.sample;
import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */

/** 
 * Description: TODO {一句话描述类是干什么的}<br/>
 *
 * @author zhanglong
 * @date: 2015年6月2日 下午2:52:33
 * @version 1.0
 * @since JDK 1.7
 */
public class Consumer {

    
    private static final String CONSUMER_XML = "/consumer.xml";

	public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext  = new ClassPathXmlApplicationContext(new String[]{CONSUMER_XML});
        applicationContext.start();
//        DemoService bean = (DemoService) applicationContext.getBean("demoService",DemoService.class);
//        bean.sayHello();
        System.in.read();
    }
}
