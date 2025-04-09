package top.resty.spboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.resty.spboot.config.ResultEnum;
import top.resty.spboot.exception.BaseException;
import top.resty.spboot.utils.InsertUtil;
import top.resty.spboot.vo.ResultVO;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
      String filePath = "https://" + request.getServerName()
          +"/upload/"+ format + newName;
      return ResultVO.success(filePath);
    } catch (IOException e) {
      throw new BaseException(ResultEnum._204,ResultEnum._204.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("remove")
  public ResultVO removeFile(String filePath,HttpServletRequest request){
    boolean flag = false;
    String s = "https://" + request.getServerName() +"/upload/";
    filePath = filePath.replace(s, "");
    // 假设字段名为attachmentPath
    if (filePath != null && !filePath.isEmpty()) {
      File file = new File(uploadPath + filePath);
      if (file.exists()) {
        System.out.println("File exists: " + file.getAbsolutePath());
        flag = file.delete();
      }
    }
    return ResultVO.success(flag);
  }


}
