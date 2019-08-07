package io.summer.serviceconsumer.service.impl;

import io.summer.serviceconsumer.service.EchoService;
import org.springframework.stereotype.Component;

/**
 * @author summerandwinter
 */
@Component
public class EchoServiceImpl implements EchoService {

  @Override
  public String echo(String name) {
    return "something bad happened to " + name;
  }
}
