package top.resty.spboot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年10月28 - 15:59
 */
public class InsertUtil {
  public static String id() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
    return now.format(formatter);
  }
}
