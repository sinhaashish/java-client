import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.errors.MinioException;

public class StatObject {
  /**
   * MinioClient.statObject() example.
   */
  public static void main(String[] args)
    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
    try {
      /* play.min.io for test and development. */
//      MinioClient minioClient = new MinioClient("https://play.min.io:9000", "Q3AM3UQ867SPQQA43P2F",
//        "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");

      MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "AKIAJHYT2ABXEXK6ULVQ",
        "rKTv5EjOLiOuX92At+ZH570c9nllzE/hr6lDkD3q");
      minioClient.traceOn(System.out);

      /* Amazon S3: */
      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
      //                                           "YOUR-SECRETACCESSKEY");

      // Get object stat information.
      ObjectStat objectStat = minioClient.statObject("ashish5", "yahoo//");
      System.out.println(objectStat);
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}