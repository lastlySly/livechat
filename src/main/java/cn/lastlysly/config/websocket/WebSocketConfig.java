package cn.lastlysly.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 10:24
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册端点，发布或者订阅消息的时候需要连接此端点
     * setAllowedOrigins 非必须，*表示允许其他域进行连接
     * withSockJS  表示开启sockejs支持
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpoint-websocket").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置消息代理(中介)
     * enableSimpleBroker 服务端推送给客户端的路径前缀
     * setApplicationDestinationPrefixes  客户端发送数据给服务器端的一个前缀
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/chat","/mysystem");
        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
//        registry.setUserDestinationPrefix("/user/");
    }
}
