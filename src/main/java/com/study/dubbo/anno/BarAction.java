package com.study.dubbo.anno;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.dubbo.annotation.FooService;
/**
 * 注解配置引用服务
 * @author Lenovo
 *
 */
@Service("barAction")
public class BarAction {
 
	/**
	 * 注解引用dubbo服务
	 */
    @Reference(group="foo",version="1.0")
    private FooService fooService;
    
    public void say(){
    	fooService.say();
    }
 
}