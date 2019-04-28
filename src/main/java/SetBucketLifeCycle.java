
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

public class SetBucketLifeCycle {
  /**
   * MinioClient.SetBucketLifeCycle() example.
   */
  public static void main(String[] args)
    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
    try {
      /* Amazon S3: */
      /*MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
        "YOUR-SECRETACCESSKEY");*/
      MinioClient minioClient = new MinioClient("http://localhost:9000", "minio",
        "minio123");
      String lifeCycle = "<LifecycleConfiguration><Rule><ID>expire-bucket</ID><Prefix></Prefix>"
        + "<Status>Enabled</Status><Expiration><Days>315</Days></Expiration>"
        + "</Rule></LifecycleConfiguration>";


      minioClient.setBucketLifeCycle("kumar", lifeCycle);
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}