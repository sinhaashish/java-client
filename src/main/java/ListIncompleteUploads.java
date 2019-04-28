import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.Upload;

public class ListIncompleteUploads {
  /**
   * MinioClient.listIncompleteUploads() example.
   */
  public static void main(String[] args)
    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient = new MinioClient("http://localhost:9000", "minio",
        "minio123");
      minioClient.traceOn(System.out);

      /* Amazon S3: */
      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
      //                                           "YOUR-SECRETACCESSKEY");

      // Check whether 'my-bucketname' exist or not.
      boolean found = minioClient.bucketExists("test");
      if (found) {
        // List all incomplete multipart upload of objects in 'my-bucketname'
        Iterable<Result<Upload>> myObjects = minioClient.listIncompleteUploads("test");
        for (Result<Upload> result : myObjects) {
          Upload upload = result.get();
          System.out.println(upload.uploadId() + ", " + upload.objectName());
        }
      } else {
        System.out.println("my-bucketname does not exist");
      }
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}