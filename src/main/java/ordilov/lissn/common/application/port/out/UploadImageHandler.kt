package ordilov.lissn.common.application.port.out

import org.springframework.web.multipart.MultipartFile

interface UploadImageHandler {

    fun upload(id: Long, file: MultipartFile): String
}