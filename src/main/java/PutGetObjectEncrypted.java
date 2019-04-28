import java.lang.StringBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.crypto.spec.SecretKeySpec;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.ServerSideEncryption;
import io.minio.errors.MinioException;

public class PutGetObjectEncrypted {
  /**
   * MinioClient.putObject() and MinioClient.getObject() example for SSE_C.
   */
  public static void main(String[] args)
    throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient = new MinioClient("https://172.17.0.1:9000", "minio",
        "minio123");

      /* Amazon S3: */
      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
      //                                           "YOUR-SECRETACCESSKEY");
      minioClient.ignoreCertCheck();
      // Create some content for the object.
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < 10; i++) {
        builder.append("Sphinx of black quartz, judge my vow: Used by Adobe InDesign to display font samples. ");
        builder.append("(29 letters)\n");
        builder.append("Jackdaws love my big sphinx of quartz: Similarly, used by Windows XP for some fonts. ");
        builder.append("(31 letters)\n");
        builder.append("Pack my box with five dozen liquor jugs: According to Wikipedia, this one is used on ");
        builder.append("NASAs Space Shuttle. (32 letters)\n");
        builder.append("The quick onyx goblin jumps over the lazy dwarf: Flavor text from an Unhinged Magic Card. ");
        builder.append("(39 letters)\n");
        builder.append("How razorback-jumping frogs can level six piqued gymnasts!: Not going to win any brevity ");
        builder.append("awards at 49 letters long, but old-time Mac users may recognize it.\n");
        builder.append("Cozy lummox gives smart squid who asks for job pen: A 41-letter tester sentence for Mac ");
        builder.append("computers after System 7.\n");
        builder.append("A few others we like: Amazingly few discotheques provide jukeboxes; Now fax quiz Jack! my ");
        builder.append("brave ghost pled; Watch Jeopardy!, Alex Trebeks fun TV quiz game.\n");
        builder.append("---\n");
      }


      // Create a InputStream for object upload.
      ByteArrayInputStream bais = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));

      // Generate a new 256 bit AES key - This key must be remembered by the client.
//      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//      keyGen.init(256);
//
//      // To test SSE-C
//      ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(keyGen.generateKey());

      byte[] key = "01234567890123456789012345678901".getBytes("UTF-8");
      SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

      ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(secretKeySpec);

      minioClient.putObject("sinha", "test", bais, Long.valueOf(bais.available()),null, sse, null);
      minioClient.putObject("sinha", "test1", bais, Long.valueOf(bais.available()),null, sse, null);

      bais.close();

      System.out.println("my-objectname is encrypted and uploaded successfully.");

      InputStream stream = minioClient.getObject("sinha", "test", sse);

      // Read the input stream and print to the console till EOF.
      byte[] buf = new byte[16384];
      int bytesRead;
      while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
        System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
      }

      // Close the input stream.
      stream.close();

    } catch (MinioException | KeyManagementException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}