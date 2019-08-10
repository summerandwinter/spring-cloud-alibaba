package io.summer.common.enums;

/**
 * api response status enums.
 * @author summerandwinter
 */
public enum ApiResponseStatus {

  /**
   * success code.
   */
  SUCCESS(0, "ok"),
  TOO_MANY_REQUESTS(429, "Too Many Requests"),
  SERVICE_IS_BUSY(1404, "service is busy");

  private final int value;
  private final String reasonPhrase;
  ApiResponseStatus(int value, String reasonPhrase) {
    this.value = value;
    this.reasonPhrase = reasonPhrase;
  }

  public int value() {
    return this.value;
  }

  public String getReasonPhrase() {
    return this.reasonPhrase;
  }
}
