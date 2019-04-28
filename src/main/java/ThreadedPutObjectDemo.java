

public class ThreadedPutObjectDemo {
  public static void main(String args[]) throws Exception {
//    MinioClient client = new MinioClient("https://play.min.io:9000", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG", true);
//    //MinioClient client = new MinioClient("http://localhost:9000", "minio", "minio123",false);
//    client.ignoreCertCheck();
//    client.traceOn(System.out);
    long startTime = System.currentTimeMillis();
    try {
      Thread[] threads = new Thread[7];

      String[] location = new String[]{
        "curl -X PUT https://play.min.io:9000/sinha/one?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20190424%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190424T035508Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=b6f5eac6cc54e8f9a9a6a859552e27b41268b83a3c632b94140f6af217ef8304",
        "curl -X PUT https://play.min.io:9000/sinha/two?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20190424%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190424T035621Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=5bef4ab7c9e78d45c1979aa5f0652f75497d5f04474d45bd2c00f7bba0877537",
        "curl -X PUT https://play.min.io:9000/sinha/three?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20190424%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190424T035715Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=174e99b299f4fcae0ab19881546d0eb263bbfb5767db11b739f8a5fa86ba4d48",
        "curl -X PUT https://play.min.io:9000/sinha/four?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20190424%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190424T035749Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=45218a8f336e122d26ace6e9411e2727647693600e3852c16c690760c198cdd5",
        "curl -X PUT https://play.min.io:9000/sinha/five?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20190424%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190424T035822Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=f296de7e5fe17a190de3c7145b91a89d01043ec362782e9a0251be61af31ad06",
        "curl -X PUT https://play.min.io:9000/sinha/six?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20190424%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190424T035902Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=85285b3da1ecd6cca83c596cf25f1dfde3a6aa717eefd240fac1acc3e203fee4",
        "curl -X PUT https://play.min.io:9000/sinha/seven?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Q3AM3UQ867SPQQA43P2F%2F20190424%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190424T035946Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=5eb1767683a5c70ab13f2999918efea44347828b843e66dbb7d49eba6bbfc263"
      };


      //PutObjectRunnable pr = new PutObjectRunnable(client, "sinha", );
      for (int i = 0; i < 7; i++) {
        PutObjectThread pr = new PutObjectThread(location[i] );
        threads[i] = new Thread(pr);
      }
      for (int i = 0; i < 7; i++) {
        threads[i].start();
      }
      // Waiting for threads to complete.
      for (int i = 0; i < 7; i++) {
        threads[i].join();
        System.out.println(i);
      }
      // All threads are completed.

    } catch (Exception e) {
      throw e;
    }
    System.out.println("uploaded");

    // throw new Exception("hanging ");


  }
}

