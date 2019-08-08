package io.summer.common.enums;

/**
 * @author yi.liu@bmsoft.com.cn
 * @date 2019/5/23
 */
public enum ApiResponseStatus {

  /**
   * success
   */
  SUCCESS(0, "ok"),
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
