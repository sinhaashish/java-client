
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

public class PresignedPutObject {
  /**
   * MinioClient.presignedPutObject() example.
   */
  public static void main(String[] args)
    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient = new MinioClient("https://play.min.io:9000", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
      //MinioClient minioClient = new MinioClient("http://localhost:9000", "minio",  "minio123");
      /* Amazon S3: */
      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
      //                                           "YOUR-SECRETACCESSKEY");

      // Get presigned URL string to upload 'my-objectname' in 'my-bucketname' and its life time is one day.
      String url = minioClient.presignedPutObject("sinha", "seven", 60 * 60 * 24);
      System.out.println(url);
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}
