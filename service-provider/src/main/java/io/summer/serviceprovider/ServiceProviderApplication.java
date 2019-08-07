package io.summer.serviceprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceProviderApplication.class, args);
  }


  @RestController
  @RefreshScope
  public class EchoController {
    @Value("${user.name:}")
    private String name;

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
      return "Hello " + name + ", I'm  " + string;
    }
  }
}
