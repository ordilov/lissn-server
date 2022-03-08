package ordilov.lissn.common.interfaces;

import lombok.Builder;
import lombok.Getter;
import ordilov.lissn.common.exception.ErrorCode;

@Getter
@Builder
public class CommonResponse<T> {

  private Result result;
  private T data;
  private String message;
  private String errorCode;

  public static <T> CommonResponse<T> success(T data, String message) {
    return CommonResponse.<T>builder()
        .result(Result.SUCCESS)
        .data(data)
        .message(message)
        .build();
  }

  public static <T> CommonResponse<T> success(T data) {
    return success(data, null);
  }

  public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
    return CommonResponse.<T>builder()
        .result(Result.FAIL)
        .message(errorCode.getMessage())
        .errorCode(errorCode.name())
        .build();
  }

  public enum Result {
    SUCCESS,
    FAIL
  }
}
