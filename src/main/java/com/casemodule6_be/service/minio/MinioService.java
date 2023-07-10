package com.casemodule6_be.service.minio;

import com.casemodule6_be.dto.file.FileDto;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MinioService {
    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Value("${minio.url}")
    private String url;

    public String getPreSignedUrl(String filename) {
        return url + bucketName.concat("/" + filename);
    }

    public List<String>  uploadFile(FileDto request) {
        List<String> lst = new ArrayList<>();

            for (MultipartFile x : request.getFileList()) {
                String fileName = System.currentTimeMillis() + x.getOriginalFilename().replace(" ", "_");
                try {
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(x.getInputStream(), x.getSize(), -1)
                        .contentType(x.getContentType())
                        .build());
                } catch (Exception e) {
                    log.error("Happened error when upload file: ", e);
                }
                lst.add(getPreSignedUrl(fileName));
            }
        return lst;
    }
}
