package ordilov.lissn.common.adapter.out.persistence

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import ordilov.lissn.common.application.port.out.UploadImageHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDateTime

@Component
class S3Uploader(
    val amazonS3Client: AmazonS3Client,
) : UploadImageHandler {

    @Value("\${cloud.aws.s3.bucket}") var bucket: String = ""


    private fun upload(id: Long, uploadFile: File): String {
        val fileName = createUniqueFileName (id, uploadFile.name)
        val uploadImageUrl = putS3(uploadFile, fileName)
        removeNewFile(uploadFile)
        return uploadImageUrl
    }

    private fun putS3(uploadFile: File, fileName: String): String {
        amazonS3Client.putObject(PutObjectRequest (bucket, fileName, uploadFile)
            .withCannedAcl(CannedAccessControlList.PublicRead))
        return amazonS3Client.getUrl(bucket, fileName).toString()
    }

    private fun removeNewFile(targetFile: File) {
        if (targetFile.delete()) {
            println("File deleted : " + targetFile.name)
        } else {
            println("File not deleted : " + targetFile.name)
        }
    }

    fun convert(file: MultipartFile): File {
        val convertFile = File(file.originalFilename!!)

        try {
            if (!convertFile.createNewFile()) {
                throw IllegalArgumentException ("파일을 생성할 수 없습니다.")
            }

            val fos = FileOutputStream(convertFile)
            fos.write(file.bytes)
            fos.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return convertFile
    }

    private fun createUniqueFileName(id: Long, fileName: String): String {
        val dirName = "profiles/"
        val fileDelimiter = "-"
        return "$dirName$fileDelimiter$id$fileDelimiter${LocalDateTime.now()}$fileDelimiter$fileName"
    }

    override fun upload(id: Long, multipartFile: MultipartFile): String {
        val uploadFile = convert(multipartFile)
        return upload(id, uploadFile)
    }
}