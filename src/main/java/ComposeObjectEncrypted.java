///*
// * Minio Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2018 Minio, Inc.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//import io.minio.MinioClient;
//import io.minio.CopySource;
//import io.minio.ObjectStat;
//import io.minio.ServerSideEncryption;
//import io.minio.errors.MinioException;
//import java.security.KeyManagementException;
//import javax.crypto.KeyGenerator;
//import javax.crypto.spec.SecretKeySpec;
//import org.xmlpull.v1.XmlPullParserException;
//import java.nio.charset.StandardCharsets;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ComposeObjectEncrypted {
//  /**
//   * MinioClient.composeObject() example.
//   */
//  public static void main(String[] args)
//    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
//    try {
//      /* play.minio.io for test and development. */
////      MinioClient minioClient = new MinioClient("https://localhost:9000", "minio",
////        "minio123");
//      /* Amazon S3: */
//       MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "AKIAJHYT2ABXEXK6ULVQ",
//                                                 "rKTv5EjOLiOuX92At+ZH570c9nllzE/hr6lDkD3q");
//      minioClient.traceOn(System.out);
//
//
//      /* Amazon S3: */
//      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
//      //                                           "YOUR-SECRETACCESSKEY");
//      //minioClient.ignoreCertCheck();
//      //########################
//      byte[] key = "01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8);
//      SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
//
//      ServerSideEncryption ssePut = ServerSideEncryption.withCustomerKey(secretKeySpec);
//      ServerSideEncryption sseSource = ServerSideEncryption.copyWithCustomerKey(secretKeySpec);
//
//      byte[] keyTarget = "01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8);
//      SecretKeySpec secretKeySpecTarget = new SecretKeySpec(keyTarget, "AES");
//
//      ServerSideEncryption sseTarget = ServerSideEncryption.withCustomerKey(secretKeySpecTarget);
//      ServerSideEncryption ssePut = ServerSideEncryption.atRest();
//
//      String filename1= "/home/ashish/Downloads/zoom_amd64.deb";
//      String filename2 = "/home/ashish/Downloads/dhiraj.mp4";
//     // minioClient.putObject("sinhaminio", "filename1", filename1, null, null, ssePut, null);
//     // minioClient.putObject("sinhaminio", "filename2", filename2, null, null, ssePut, null);
//
//      //ObjectStat objectStat = minioClient.statObject("encrypt", filename1, ssePut);
//      CopySource s1 = new CopySource("sinhaminio","filename1", null, null, null, null, ssePut );
//      CopySource s2 = new CopySource("sinhaminio","filename2", null, null, null, null, ssePut );
//
//      List<CopySource> listSourceObjects = new ArrayList<CopySource>();
//      listSourceObjects.add(s1);
//      listSourceObjects.add(s2);
//
//      minioClient.composeObject("sinhaminio1","dest",listSourceObjects, null, null);
//
//      //##############
//
//          System.out.println("Object Composed successfully");
//    } catch (MinioException  e) {
//      System.out.println("Error occurred: " + e);
//    }
//  }
//}
