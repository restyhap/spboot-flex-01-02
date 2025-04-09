package top.resty.store.utils;

import lombok.SneakyThrows;
import org.junit.Test;

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

  public static String saveMoney ( String money) {
    return (int) (Double.valueOf(money) * 100)  + "";
  }

  public static String loadMoney ( String money) {
    return Double.valueOf(money) / 100  + "";
  }

  public static String id () {
    StringBuilder idstr = new StringBuilder();
    Calendar calendar = Calendar.getInstance();

    idstr.append(calendar.get(Calendar.YEAR))
          .append(change(calendar.get(Calendar.MONTH)+1))
        .append(change(calendar.get(Calendar.DAY_OF_MONTH)))
        .append(calendar.get(Calendar.HOUR_OF_DAY))
        .append(calendar.get(Calendar.MINUTE))
        .append(change(String.valueOf(calendar.get(Calendar.MILLISECOND))))
    ;

    return idstr.toString() ;
  }

  public static String change(int i) {
    String str = String.valueOf(i);
    if (str.length() == 1){
      str = "0"+str;
    }
    return str ;
  }
  public static String change(String str) {
    if (str.length() == 1){
      str = "00"+str;
    } else if (str.length() == 2) {
      str = "0"+str ;
    }
    return str ;
  }

  @SneakyThrows
  @Test
  public void test1(){
    for (int i = 0; i < 1000; i++) {
      System.out.println("id = " + id());
      Thread.sleep(10);
    }
  }
}
