package io.summer.serviceconsumer.controller;

import io.summer.common.vo.ApiResponse;
import io.summer.serviceconsumer.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author summerandwinter
 */
@RestController
public class EchoController {
  private final LoadBalancerClient loadBalancerClient;
  private final RestTemplate restTemplate;
  private final EchoService echoService;

  @Value("${spring.application.name}")
  private String appName;

  @Autowired
  public EchoController(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate, EchoService echoService) {
    this.loadBalancerClient = loadBalancerClient;
    this.restTemplate = restTemplate;
    this.echoService = echoService;
  }

  @GetMapping("/echo/app-name")
  public ApiResponse echoAppName() {
    // 使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
    ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
    String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
    System.out.println("request url:" + url);
    return restTemplate.getForObject(url, ApiResponse.class);
  }

  @GetMapping("/hello")
  //@SentinelResource("hello")
  @ResponseBody
  public ApiResponse sayHello() {
    return echoService.echo(appName);
  }
}
