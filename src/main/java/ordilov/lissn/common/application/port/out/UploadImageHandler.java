package ordilov.lissn.common.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageHandler {

  String upload(Long id, MultipartFile file);
}
