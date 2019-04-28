import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import org.xmlpull.v1.XmlPullParserException;
import io.minio.MinioClient;
import io.minio.errors.MinioException;

public class GetBucketLifeCycle {
  /**
   * MinioClient.getBucketLifecycle() example.
   */
  public static void main(String[] args)
    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
    try {
      /* Amazon S3: */
      MinioClient minioClient = new MinioClient("http://localhost:9000", "minio",
        "minio123");
      String lifecycle = minioClient.getBucketLifeCycle("kumar" );
      System.out.println(" Life Cycle is : " + lifecycle );
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}