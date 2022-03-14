package ordilov.lissn.common.adapter.out.persistence;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.common.application.port.out.UploadImageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader implements UploadImageHandler {

  private final AmazonS3Client amazonS3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Override
  public String upload(Long id, MultipartFile multipartFile) {
    File uploadFile = convert(multipartFile);
    return upload(id, uploadFile);
  }

  private String upload(Long id, File uploadFile) {
    String fileName = createUniqueFileName(id, uploadFile.getName());
    String uploadImageUrl = putS3(uploadFile, fileName);
    removeNewFile(uploadFile);
    return uploadImageUrl;
  }

  private String putS3(File uploadFile, String fileName) {
    amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
        .withCannedAcl(CannedAccessControlList.PublicRead));
    return amazonS3Client.getUrl(bucket, fileName).toString();
  }

  private void removeNewFile(File targetFile) {
    if (targetFile.delete()) {
      log.info("파일이 삭제되었습니다.");
    } else {
      log.info("파일이 삭제되지 못했습니다.");
    }
  }

  private File convert(MultipartFile file) {
    File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

    try {
      if (!convertFile.createNewFile()) {
        throw new IllegalArgumentException("파일을 생성할 수 없습니다.");
      }

      FileOutputStream fos = new FileOutputStream(convertFile);
      fos.write(file.getBytes());
      fos.close();

    } catch (IOException | IllegalArgumentException e) {
      log.error("파일 저장 실패", e);
    }

    return convertFile;
  }

  private String createUniqueFileName(Long id, String fileName) {
    String dirName = "profiles/";
    String file_delimiter = "-";
    return dirName
        + id + file_delimiter
        + LocalDateTime.now() + file_delimiter
        + fileName;
  }
}