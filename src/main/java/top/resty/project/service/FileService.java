package top.resty.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Value("${config.upload-path}")
    private String uploadPath;

    /**
     * 处理文件上传
     * @param files 上传的文件列表
     * @return 上传后的文件URL列表
     * @throws IOException 如果文件上传失败
     */
    public List<String> handleFileUpload(List<MultipartFile> files) throws IOException {
        List<String> fileUrls = new ArrayList<>();

        // 确保上传目录存在
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                // 生成唯一的文件名
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFilename = UUID.randomUUID().toString() + extension;

                // 保存文件
                Path filePath = uploadDir.resolve(newFilename);
                Files.copy(file.getInputStream(), filePath);

                // 添加文件URL到列表
                fileUrls.add("/uploads/" + newFilename);
            }
        }

        return fileUrls;
    }
}
