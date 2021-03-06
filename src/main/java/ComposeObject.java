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
//import io.minio.ComposeSource;
//import io.minio.MinioClient;
//import io.minio.ComposeSource;
//import io.minio.ServerSideEncryption;
//import io.minio.errors.MinioException;
//import java.security.KeyManagementException;
//import javax.crypto.KeyGenerator;
//import javax.crypto.spec.SecretKeySpec;
//import org.xmlpull.v1.XmlPullParserException;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ComposeObject {
//  /**
//   * MinioClient.composeObject() example.
//   */
//  public static void main(String[] args)
//    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
//    try {
//      /* play.minio.io for test and development. */
////      MinioClient minioClient = new MinioClient("https://localhost:9000", "minio",
////        "minio123");
//
//      MinioClient minioClient = new MinioClient("https://play.min.io:9000", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG", true);
//     minioClient.traceOn(System.out);
//
//
//
//
//     ComposeSource s1 = new ComposeSource("sinhaminio", "15Lakh.csv" );
//      ComposeSource s2 = new ComposeSource("sinhaminio", "10000.csv" );
//
//
//      //ComposeSource s3 = new ComposeSource("my-bucketname-three", "my-objectname-three");
//
//      // Adding the SourceInfo to an ArrayList
//      List<ComposeSource> sourceObjectList = new ArrayList<ComposeSource>();
//      sourceObjectList.add(s1);
//      sourceObjectList.add(s2);
//      //sourceObjectList.add(s3);
//
//      minioClient.composeObject("dest", "single+object", sourceObjectList, null, null) ;
//      System.out.println("Object Composed successfully");
//    } catch (MinioException  e) {
//      System.out.println("Error occurred: " + e);
//    }
//  }
//}
