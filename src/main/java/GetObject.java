


import java.io.InputStream;
  import java.io.IOException;
  import java.security.NoSuchAlgorithmException;
  import java.security.InvalidKeyException;

  import java.nio.charset.StandardCharsets;

  import org.xmlpull.v1.XmlPullParserException;

  import io.minio.MinioClient;
  import io.minio.errors.MinioException;

public class GetObject {
  /**
   * MinioClient.getObject() example.
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

      // Check whether the object exists using statObject().  If the object is not found,
      // statObject() throws an exception.  It means that the object exists when statObject()
      // execution is successful.
      minioClient.statObject("sinha", "File003.txt");

      // Get input stream to have content of 'my-objectname' from 'my-bucketname'
      InputStream stream = minioClient.getObject("sinha", "File003.txt");

      // Read the input stream and print to the console till EOF.
      byte[] buf = new byte[16384];
      int bytesRead;
      while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
        System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
      }

      // Close the input stream.
      stream.close();
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}
