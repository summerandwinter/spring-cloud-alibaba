package io.summer.serviceprovider.controller;

import io.summer.common.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author summerandwinter
 */
@RestController
@RefreshScope
public class EchoController {
  @Value("${user.name:}")
  private String name;

  @GetMapping(value = "/echo/{string}")
  @ResponseBody
  public ApiResponse echo(@PathVariable String string) {
    return new ApiResponse<>("Hello " + name + ", I'm  " + string);
  }
}
