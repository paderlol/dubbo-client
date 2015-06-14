/**
 * 
 */
package com.study.dubbo.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * @author Lenovo
 *
 */
public class ApiConfigConsumerTest {
	/**
	 * 注册中心方式
	 */
	private static final String ZOOKEEPER = "zookeeper";
	/**
	 * 注册中心地址
	 */
	private static final String REG_ADDR = "120.25.250.61:2181";
	public static void main(String[] args) {
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("yyy");
		 
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress(REG_ADDR);
		registry.setProtocol(ZOOKEEPER);
		 
		// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
		 
		// 引用远程服务
		ReferenceConfig<ApiConfigService> reference = new ReferenceConfig<ApiConfigService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(ApiConfigService.class);
		reference.setVersion("1.0.0");
		reference.setGroup("api");
		// 和本地bean一样使用apiConfigService
//		ApiConfigService apiConfigService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		ApiConfigService apiConfigService = cache.get(reference);
		apiConfigService.say();
	}
}
