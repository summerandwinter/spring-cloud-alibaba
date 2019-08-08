package io.summer.serviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author summerandwinter
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceProviderApplication.class, args);
  }



}
