import java.lang.StringBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.MinioException;


public class PutObjectOfSize6MB {


  /**
   * MinioClient.putObject() example.
   */
  public static void main(String[] args)
    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient = new MinioClient("http://localhost:9000", "minio",
        "minio123");
      // Create object 'my-objectname' in 'my-bucketname' with content from the input stream.
      minioClient.putObject("sinhaminio", "65MB","/home/ashish/Downloads/ideaIC-2019.1.tar.gz");
      System.out.println("my-objectname is uploaded successfully");
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}