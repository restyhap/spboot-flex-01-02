package top.resty.spboot.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年03月22 - 11:38
 */
public class DatabaseException extends BaseException {
  public DatabaseException(Enum baseEnum, String errorMsg) {
    super(baseEnum, errorMsg);
  }
}
