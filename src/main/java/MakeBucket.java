import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

public class MakeBucket {
    /**
     * MinioClient.makeBucket() example.
     */
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
        try {
            /* play.minio.io for test and development. */
            MinioClient minioClient = new MinioClient("http://localhost:9000", "minio", "minio123");
            minioClient.traceOn(System.out);
            /* Amazon S3: */
            // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
            //                                           "YOUR-SECRETACCESSKEY");

            // Create bucket if it doesn't exist.
            boolean found = minioClient.bucketExists("my-bucketname");
            if (found) {
                System.out.println("my-bucketname already exists");
            } else {
                // Create bucket 'my-bucketname'.
                minioClient.makeBucket("pq.-r");
                System.out.println("my-bucketname is created successfully");
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}