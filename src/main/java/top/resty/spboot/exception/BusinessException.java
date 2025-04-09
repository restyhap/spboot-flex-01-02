package top.resty.store.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年03月22 - 11:37
 */
public class BusinessException extends BaseException {
  public BusinessException(Enum baseEnum, String errorMsg) {
    super(baseEnum, errorMsg);
  }
}
