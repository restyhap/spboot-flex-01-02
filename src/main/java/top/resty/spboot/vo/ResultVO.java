package top.resty.store.vo;

import lombok.Builder;
import lombok.Data;
import top.resty.store.config.ResultEnum;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年03月20 - 14:32
 */
@Data
@Builder
public class ResultVO<T> implements Serializable {
  /**
   * 状态码
   *
   * @author resty
   * @since 2022-07-05
   **/
  private String code;
  /**
   * 返回消息
   *
   * @author resty
   * @since 2022-07-05
   **/
  private String message;
  /**
   * 返回的数据
   *
   * @author resty
   * @since 2022-07-05
   **/
  private Object data;

  /**
   * 不允许直接实例化返回数据封装类
   *
   * @author resty
   * @since 2022-07-05
   **/
  private ResultVO() {
  }

  private ResultVO(String code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  /**
   * 成功不需要返回数据
   *
   * @return ResponseVO
   * @author resty
   * @since 2022-07-05
   **/
  public static ResultVO success() {
    return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
  }

  /**
   * 成功需要返回数据
   *
   * @param obj
   * @return ResponseVO
   * @author resty
   * @since 2022-07-05
   **/
  public static ResultVO success(Object obj) {
    return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), obj);
  }

  /**
   * 请求的内容不存在
   *
   * @return ResponseVO
   * @author resty
   * @since 2022-07-05
   **/
  public static ResultVO error() {
    return new ResultVO(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage(), null);
  }

  /**
   * 提供失败状态码及信息的返回
   *
   * @param resultEnum
   * @return ResponseVO
   * @author resty
   * @since 2022-07-05
   **/
  public static ResultVO error(ResultEnum resultEnum, String message) {
    return new ResultVO(resultEnum.getCode(), resultEnum.getMessage(), message);
  }

}
