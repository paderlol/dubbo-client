import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcInvocation;
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
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("192.168.44.155:2181");
     // 引用远程服务 
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface("com.study.dubbo.server.DemoService"); // 弱类型接口名 
        reference.setGeneric(true); // 声明为泛化接口 
        
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);
//        GenericService genericService = reference.get(); // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用 
         
        // 基本类型以及Date,List,Map等不需要转换，直接调用 
        Object result = genericService.$invoke("sayHello", null, null); 
         
       
    }
}
