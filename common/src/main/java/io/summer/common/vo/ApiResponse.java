package io.summer.common.vo;

import com.sun.istack.internal.Nullable;
import io.summer.common.enums.ApiResponseStatus;
import java.io.Serializable;
import lombok.Data;

/**
 * common api response entity.
 * @author summerandwinter
 */
@Data
public class ApiResponse<T> implements Serializable {

  private int code;
  private long timestamp;
  private String message;
  private T data;

  public ApiResponse() {
    this(ApiResponseStatus.SUCCESS);
  }

  /**
   * Instantiates ApiResponse with ApiResponseStatus.
   * @param status ApiResponseStatus
   */
  public ApiResponse(ApiResponseStatus status) {
    this.code = status.value();
    this.message = status.getReasonPhrase();
    this.timestamp = System.currentTimeMillis();
  }

  public ApiResponse(@Nullable T body) {
    this(ApiResponseStatus.SUCCESS);
    this.data = body;
  }

  public ApiResponse(ApiResponseStatus status, @Nullable T body) {
    this(status);
    this.data = body;
  }

}
