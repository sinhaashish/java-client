


import java.io.IOException;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
  import java.security.InvalidKeyException;


  import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.xmlpull.v1.XmlPullParserException;

  import io.minio.MinioClient;
  import io.minio.ServerSideEncryption;
  import io.minio.errors.MinioException;


public class PutGetEncrpytedFile {
  /**
   * MinioClient.putObject() and MinioClient.getObject() to a file example for  SSE_C.
   */
  public static void main(String[] args)
    throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient = new MinioClient("https://172.17.0.1:9000", "minio",
        "minio123");
      minioClient.ignoreCertCheck();
      //* Amazon S3: */
      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
      //                                           "YOUR-SECRETACCESSKEY");
      minioClient.traceOn(System.out);
      String objectName = "zoom";
      String bucketName = "sinha";
      String inputfile  = "/home/ashish/Downloads/zoom_amd64.deb";
      String outputfile = "/ome/ashish/my-outputfile";

      // Generate a new 256 bit AES key - This key must be remembered by the client.
//      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//      keyGen.init(256);
//
//      // To test SSE-C
//      ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(keyGen.generateKey());
      byte[] key = "01234567890123456789012345678901".getBytes("UTF-8");
      SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

      ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(secretKeySpec);
      minioClient.putObject(bucketName, objectName, inputfile, null, null, sse, null);
      System.out.println("my-objectname is encrypted and uploaded successfully.");

      minioClient.getObject(bucketName, objectName, sse, outputfile);
      System.out.println("Content of my-objectname saved to my-outputfile ");
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }

  }
}