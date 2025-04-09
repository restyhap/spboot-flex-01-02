package top.resty.spboot.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.*;
import jakarta.servlet.http.HttpServletRequest;
import okhttp3.*;
import org.apache.logging.log4j.util.Base64Util;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.resty.spboot.config.ResultEnum;
import top.resty.spboot.entity.Order;
import top.resty.spboot.exception.BaseException;
import top.resty.spboot.utils.InsertUtil;
import top.resty.spboot.utils.SSLUtils;
import top.resty.spboot.vo.ResultVO;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Enumeration;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年11月18 - 11:18
 */
@RestController
@RequestMapping("/files")
public class FileUploadController {

  //绑定文件上传路径到uploadPath
  @Value("${config.upload-path}")
  private String uploadPath;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

  @PostMapping("/upload")
  public ResultVO upload(@RequestParam("file") MultipartFile uploadFile,
                       HttpServletRequest request) {
    Order order = new Order() ;


    // 在 uploadPath 文件夹中通过日期对上传的文件归类保存
    // 比如：/2019/06/06/cf13891e-4b95-4000-81eb-b6d70ae44930.png
    String format = sdf.format(new Date());
    File folder = new File(uploadPath + format);
    System.out.println("folder = " + folder);
    if (!folder.isDirectory()) {
      folder.mkdirs();
    }

    // 对上传的文件重命名，避免文件重名
    String oldName = uploadFile.getOriginalFilename();
    String newName = InsertUtil.id() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
    try {
      // 文件保存
      // 1.获取文件的保存路径
      File file = new File(folder, newName);
      uploadFile.transferTo(file);

      // 返回上传文件的访问路径
      String filePath = request.getScheme() + "://" + request.getServerName()
          + ":" + request.getServerPort()  +"/"+ format + newName;

      order.setImagePath(filePath);
      SSLUtils.ignoreSsl();
      String convert = convert(file);
      JSONObject entries = JSONUtil.parseObj(convert);
      JSONArray wordsResult = (JSONArray) entries.get("words_result");
      wordsResult.stream().iterator().forEachRemaining(item -> {
        JSONObject jsonObject = (JSONObject) item;
        String words = (String) jsonObject.get("words");

        if(words.contains("应用号")){
          order.setAppId(words.substring(words.indexOf("应用号")+4));
        }

      });

      return ResultVO.success(order);
    } catch (IOException e) {
      throw new BaseException(ResultEnum._204,ResultEnum._204.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public String convert (File file) {
    // 请求url
    String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/webimage";
    try {
      // 本地文件路径
      byte[] imgData = top.resty.spboot.utils.FileUtil.readFileByBytes(file);
      String imgStr = top.resty.spboot.utils.Base64Util.encode(imgData);
      String imgParam = URLEncoder.encode(imgStr, "UTF-8");

      String param = "image=" + imgParam;

      // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
      String accessToken = getToken();
      System.out.println("accessToken = " + accessToken);
      String result = top.resty.spboot.utils.HttpUtil.post(url, accessToken, param);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getToken() throws IOException {
    OkHttpClient build = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "");
    Request request = new Request.Builder()
        .url("https://aip.baidubce.com/oauth/2.0/token?client_id=lQgWZQzRKlsh94DSLdJiWbUE&client_secret=leA2B7EFbcVyixt9yZyOsBHMQqR8px2a&grant_type=client_credentials")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .build();
    Response response = build.newCall(request).execute();
    String str = response.body().string();

    String accessToken = JSONUtil.parseObj(str).getStr("access_token");
    return accessToken;
  }


}
