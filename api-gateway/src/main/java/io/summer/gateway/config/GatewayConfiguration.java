package io.summer.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import io.summer.gateway.handler.ApiGatewayBlockRequestHandler;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/**
 * config gateway.
 * @author summerandwinter
 */
@Configuration
public class GatewayConfiguration {

  @PostConstruct
  public void init() {
    GatewayCallbackManager.setBlockHandler(new ApiGatewayBlockRequestHandler());
  }

}
