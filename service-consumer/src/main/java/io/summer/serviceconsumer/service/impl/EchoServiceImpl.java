package io.summer.serviceconsumer.service.impl;

import io.summer.common.enums.ApiResponseStatus;
import io.summer.common.vo.ApiResponse;
import io.summer.serviceconsumer.service.EchoService;
import org.springframework.stereotype.Component;

/**
 * @author summerandwinter
 */
@Component
public class EchoServiceImpl implements EchoService {

  @Override
  public ApiResponse echo(String name) {
    return new ApiResponse(ApiResponseStatus.SERVICE_IS_BUSY);
  }
}
