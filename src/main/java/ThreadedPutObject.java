import io.minio.*;

public class ThreadedPutObject {
    public static void main(String args[]) throws Exception {
        //MinioClient client = new MinioClient("https://play.min.io:9000", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG", true);
        MinioClient client = new MinioClient("http://localhost:9000", "minio", "minio123",false);
        client.ignoreCertCheck();
        client.traceOn(System.out);
        long startTime = System.currentTimeMillis();
        try {
            Thread[] threads = new Thread[7];

            String[] location = new String[]{"/home/ashish/Sample/book.txt", "/home/ashish/Sample/copy.txt","/home/ashish/Sample/book.txt_1", "/home/ashish/Sample/copy.txt_1","/home/ashish/Sample/empty", "/home/ashish/Sample/empty_1","/home/ashish/Sample/empty_2"};


          //PutObjectRunnable pr = new PutObjectRunnable(client, "sinha", );
            for (int i = 0; i < 7; i++) {
                PutObjectRunnable pr = new PutObjectRunnable(client, "sinha",location[i] );
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


