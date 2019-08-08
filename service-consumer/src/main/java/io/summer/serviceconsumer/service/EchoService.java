package io.summer.serviceconsumer.service;

import io.summer.common.vo.ApiResponse;
import io.summer.serviceconsumer.service.impl.EchoServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author summerandwinter
 */
@Service
@FeignClient(name = "service-provider", fallback = EchoServiceImpl.class)
public interface EchoService {

  /**
   * Demo for feign.
   * @param name name to show
   * @return final words
   */
  @GetMapping("/echo/{name}")
  ApiResponse echo(@PathVariable("name") String name);
}
