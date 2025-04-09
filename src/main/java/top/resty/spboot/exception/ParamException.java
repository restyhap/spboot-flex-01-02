package top.resty.spboot.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年03月22 - 11:38
 */
public class ParamException extends BaseException {
  public ParamException(Enum baseEnum, String errorMsg) {
    super(baseEnum, errorMsg);
  }
}
