package io.summer.gateway.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import io.summer.common.enums.ApiResponseStatus;
import io.summer.common.vo.ApiResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * custom block request handler.
 * @author summerandwinter
 */
public class ApiGatewayBlockRequestHandler  implements BlockRequestHandler {

  private static final String DEFAULT_BLOCK_MSG_PREFIX = "Blocked by Sentinel: ";

  @Override
  public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {
    if (acceptsHtml(exchange)) {
      return htmlErrorResponse(ex);
    }
    // JSON result by default.
    return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(fromObject(buildErrorResult(ex)));
  }

  private Mono<ServerResponse> htmlErrorResponse(Throwable ex) {
    return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
        .contentType(MediaType.TEXT_PLAIN)
        .syncBody(DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName());
  }

  private ApiResponse buildErrorResult(Throwable ex) {
    return new ApiResponse<>(ApiResponseStatus.TOO_MANY_REQUESTS,
        DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName());
  }

  /**
   * Reference from {@code DefaultErrorWebExceptionHandler} of Spring Boot.
   */
  private boolean acceptsHtml(ServerWebExchange exchange) {
    try {
      List<MediaType> acceptedMediaTypes = exchange.getRequest().getHeaders().getAccept();
      acceptedMediaTypes.remove(MediaType.ALL);
      MediaType.sortBySpecificityAndQuality(acceptedMediaTypes);
      return acceptedMediaTypes.stream()
          .anyMatch(MediaType.TEXT_HTML::isCompatibleWith);
    } catch (InvalidMediaTypeException ex) {
      return false;
    }
  }

}
