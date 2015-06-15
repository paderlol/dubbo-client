/**
 * 
 */
package com.study.dubbo.echo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.study.dubbo.server.DemoService;

/**
 *	回声测试：用于判断服务是否正常
 * @author Lenovo
 *
 */
public class EchoTest {

	private static final String CONSUMER_XML = "/consumer.xml";
	private static final String OK = "OK";

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext  = new ClassPathXmlApplicationContext(new String[]{CONSUMER_XML});
        applicationContext.start();
        DemoService demoService = (DemoService) applicationContext.getBean("demoService");
        EchoService echoService =(EchoService) demoService;
        String status = (String) echoService.$echo(OK);
        if(OK.equals(status)){
        	System.out.println("测试成功");
        }
	}
}
