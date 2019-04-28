
class PutObjectThread implements Runnable {
  String presignedUrl;

  public PutObjectThread(String presignedUrl) {
    this.presignedUrl = presignedUrl;
  }

  public void run() {
    StringBuffer traceBuffer = new StringBuffer();

    try {
      Process process = Runtime.getRuntime().exec(presignedUrl);

    } catch (Exception e) {
      System.err.print(traceBuffer.toString());
      e.printStackTrace();
    }
  }
}
