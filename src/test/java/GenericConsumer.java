import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.support.AbstractRegistryFactory;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: bubugao </p>
 */

/** 
 * Description: TODO {泛化引用}<br/>
 *
 * @author zhanglong
 * @date: 2015年6月2日 下午5:54:59
 * @version 1.0
 * @since JDK 1.7
 */
public class GenericConsumer {

    
    public static void main(String[] args) throws IOException {
        
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer-of-helloworld-app");
         
        // 连接注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("192.168.44.155:2181");
     // 引用远程服务 
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        reference.setApplication(application);
        reference.setRegistry(registryConfig);
        reference.setInterface("com.study.dubbo.server.DemoService"); // 弱类型接口名 
        reference.setGeneric(true); // 声明为泛化接口 
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);
//        GenericService genericService = reference.get(); // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用 
        // 基本类型以及Date,List,Map等不需要转换，直接调用 
        Map<String, Object> person = new HashMap<String, Object>(); 
        person.put("name", "xxx"); 
        person.put("age", 111); 
        Object result = genericService.$invoke("doSomething", new String[]{"com.study.dubbo.model.Person"}, new Object[]{person}); 
        System.out.println(result);
        Collection<Registry> registries = AbstractRegistryFactory.getRegistries();
        for (Registry registry : registries) {
            ZookeeperRegistry zookeeperRegistry=(ZookeeperRegistry) registry;
            Set<URL> registered = zookeeperRegistry.getRegistered();
            for (URL url : registered) {
                System.out.println(url);
            }
        }
        
         
       
    }
}
