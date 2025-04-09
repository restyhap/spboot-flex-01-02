package top.resty.store.config;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import top.resty.store.exception.BaseException;
import top.resty.store.exception.BusinessException;
import top.resty.store.exception.DatabaseException;
import top.resty.store.exception.ParamException;
import top.resty.store.vo.ResultVO;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * valid 验证封装 结合自己定义的参数异常可以实现更好的展示
   *
   * @param request
   * @param e
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResultVO validHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.PARAM_EXCEPTION, e.getMessage());
  }

  /**
   * 前端请求方式与后端接收方式不匹配异常
   * @param request 请求
   * @param e 错误
   * @return  错误包装类
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResultVO<String> httpRequestMethodNotSupportedException(HttpServletRequest request, Exception e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.REQUEST_NOT_SUPPORTED_EXCEPTION, e.getMessage());
  }

  /**
   * 请求路径不存在异常
   * @param request 请求
   * @param e 错误
   * @return  错误包装类
   */
  @ExceptionHandler(NoResourceFoundException.class)
  public ResultVO<String> nullPointerException(HttpServletRequest request, Exception e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.NO_RESOURCE_FOUND_EXCEPTION, e.getMessage());
  }


  /**
   * 数据库异常
   *
   * @param request 用户的请求
   * @param e       数据库异常实例
   * @return ResultVO<String> 统一返回数据封装类
   *
   * @author resty
   * @since 2023-03-20 18:55
   **/
  @ExceptionHandler(DatabaseException.class)
  public ResultVO<String> databaseExceptionHandler(HttpServletRequest request, DatabaseException e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.DATABASE_EXCEPTION, e.getMessage());
  }

  /**
   * 自定义异常
   *
   * @param request 用户的请求
   * @param e       数据库异常实例
   * @return ResultVO<String> 统一返回数据封装类
   *
   * @author resty
   * @since 2023-03-20 08:56
   **/
  @ExceptionHandler(BaseException.class)
  public ResultVO<String> baseExceptionHandler(HttpServletRequest request, BaseException e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.BASE_EXCEPTION, e.getMessage());
  }

  /**
   * 参数异常
   *
   * @param request 用户的请求
   * @param e       数据库异常实例
   * @return ResultVO<String> 统一返回数据封装类
   *
   * @author resty
   * @since 2023-03-20 08:53
   **/
  @ExceptionHandler(ParamException.class)
  public ResultVO<String> paramExceptionHandler(HttpServletRequest request, Exception e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.PARAM_EXCEPTION, e.getMessage());
  }

  /**
   * 业务异常
   *
   * @param request 用户的请求
   * @param e       数据库异常实例
   * @return ResultVO<String> 统一返回数据封装类
   *
   * @author resty
   * @since 2023-03-20 08:54
   **/
  @ExceptionHandler(BusinessException.class)
  public ResultVO<String> businessExceptionHandler(HttpServletRequest request, Exception e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.BUSINESS_EXCEPTION, e.getMessage());
  }

  /**
   * 运行时异常
   *
   * @param request 用户的请求
   * @param e       数据库异常实例
   * @return ResultVO<String> 统一返回数据封装类
   *
   * @author resty
   * @since 2023-03-20 08:55
   **/
  @ExceptionHandler(RuntimeException.class)
  public ResultVO<String> runtimeExceptionHandler(HttpServletRequest request, Exception e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.UN_KNOW_ERROR, e.getMessage());
  }

  /**
   * 全局异常
   *
   * @param request 用户的请求
   * @param e       数据库异常实例
   * @return ResultVO<String> 统一返回数据封装类
   *
   * @author resty
   * @since 2023-03-20 08:55
   **/
  @ExceptionHandler(Exception.class)
  public ResultVO<String> exceptionHandler(HttpServletRequest request, Exception e) {
    execute(request, e);
    return ResultVO.error(ResultEnum.SYSTEM_ERROR, e.getMessage());
  }

  public void execute(HttpServletRequest request, Exception e) {
    if (Objects.nonNull(request)) {
      StringBuffer requestUrlBuffer = request.getRequestURL();
      String method = request.getMethod();
      String requestUrl = Objects.isNull(requestUrlBuffer) ? request.getRequestURI() : requestUrlBuffer.toString();
      log.error("Exception：{}, \n请求URL: [{}], \n请求方式: [{}]\n", e.getMessage(), requestUrl, method, e);
    }
  }
}
