package top.resty.store.exception;

/**
 * <p>
 *    BaseException
 * </p>
 *
 * @author : resty
 * @since : 2024年03月22 - 11:35
 */
public class BaseException extends RuntimeException {
  private Enum baseEnum;

  public BaseException(Enum baseEnum, String errorMsg) {
    super(errorMsg);
    this.baseEnum = baseEnum;
  }
}
