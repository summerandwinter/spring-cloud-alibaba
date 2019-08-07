package io.summer.serviceconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author summerandwinter
 * @date 2019/8/7
 */
@RestController
public class EchoController {
  @Autowired
  private LoadBalancerClient loadBalancerClient;
  @Autowired
  private RestTemplate restTemplate;

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping("/echo/app-name")
  public String echoAppName(){
    //使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
    ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
    String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
    System.out.println("request url:" + url);
    return restTemplate.getForObject(url, String.class);
  }
}
