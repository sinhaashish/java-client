import io.minio.MinioClient;
class PutObjectRunnable implements Runnable {
    MinioClient client;
    String bucketName;
    String filename;

    public PutObjectRunnable(MinioClient client, String bucketName, String filename) {
        this.client = client;
        this.bucketName = bucketName;
        this.filename = filename;
    }

    public void run() {
        StringBuffer traceBuffer = new StringBuffer();

        try {

            client.putObject(bucketName, filename, filename);

        } catch (Exception e) {
            System.err.print(traceBuffer.toString());
            e.printStackTrace();
        }
    }
}
