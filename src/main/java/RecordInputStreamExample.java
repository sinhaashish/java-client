
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CSVInput;
import com.amazonaws.services.s3.model.CSVOutput;
import com.amazonaws.services.s3.model.CompressionType;
import com.amazonaws.services.s3.model.ExpressionType;
import com.amazonaws.services.s3.model.InputSerialization;
import com.amazonaws.services.s3.model.OutputSerialization;
import com.amazonaws.services.s3.model.SelectObjectContentEvent;
import com.amazonaws.services.s3.model.SelectObjectContentEventVisitor;
import com.amazonaws.services.s3.model.SelectObjectContentRequest;
import com.amazonaws.services.s3.model.SelectObjectContentResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.amazonaws.util.IOUtils.copy;

/**
 * This example shows how to query data from S3Select and consume the response in the form of an
 * InputStream of records and write it to a file.
 */

public class RecordInputStreamExample {

  private static final String BUCKET_NAME = "rasia";
  private static final String CSV_OBJECT_KEY = "test.csv";
  private static final String S3_SELECT_RESULTS_PATH = "/home/ashish/aws";
  private static final String QUERY = "select * from S3Object s";

  public static void main(String[] args) throws Exception {
    final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

    SelectObjectContentRequest request = generateBaseCSVRequest(BUCKET_NAME, CSV_OBJECT_KEY, QUERY);
    final AtomicBoolean isResultComplete = new AtomicBoolean(false);

    try (OutputStream fileOutputStream = new FileOutputStream(new File (S3_SELECT_RESULTS_PATH));
      SelectObjectContentResult result = s3Client.selectObjectContent(request)) {
      InputStream resultInputStream = result.getPayload().getRecordsInputStream(
        new SelectObjectContentEventVisitor() {
          @Override
          public void visit(SelectObjectContentEvent.StatsEvent event)
          {
            System.out.println(
              "Received Stats, Bytes Scanned: " + event.getDetails().getBytesScanned()
                +  " Bytes Processed: " + event.getDetails().getBytesProcessed());
          }

          /*
           * An End Event informs that the request has finished successfully.
           */
          @Override
          public void visit(SelectObjectContentEvent.EndEvent event)
          {
            isResultComplete.set(true);
            System.out.println("Received End Event. Result is complete.");
          }
        }
      );

      copy(resultInputStream, fileOutputStream);
    }

    /*
     * The End Event indicates all matching records have been transmitted.
     * If the End Event is not received, the results may be incomplete.
     */
    if (!isResultComplete.get()) {
      throw new Exception("S3 Select request was incomplete as End Event was not received.");
    }
  }

  private static SelectObjectContentRequest generateBaseCSVRequest(String bucket, String key, String query) {
    SelectObjectContentRequest request = new SelectObjectContentRequest();
    request.setBucketName(bucket);
    request.setKey(key);
    request.setExpression(query);
    request.setExpressionType(ExpressionType.SQL);

    InputSerialization inputSerialization = new InputSerialization();
    CSVInput csvInput = new CSVInput();
    csvInput.setAllowQuotedRecordDelimiter(Boolean.FALSE);
    inputSerialization.setCsv(csvInput);
    inputSerialization.setCompressionType(CompressionType.NONE);
    request.setInputSerialization(inputSerialization);

    OutputSerialization outputSerialization = new OutputSerialization();
    outputSerialization.setCsv(new CSVOutput());
    request.setOutputSerialization(outputSerialization);

    return request;
  }
}