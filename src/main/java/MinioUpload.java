import io.minio.MinioClient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.xmlpull.v1.XmlPullParserException;

public class MinioUpload {

  public static void main(String[] args)
    throws IOException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {

    try {
      /* play.min.io for test and development. */
      MinioClient minioClient = new MinioClient("https://play.min.io:9000", "Q3AM3UQ867SPQQA43P2F",
        "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
      Employee emp = new Employee("credit ", "One");

      //**** Put Object***///
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(emp);
      oos.flush();
      oos.close();
      InputStream is = new ByteArrayInputStream(baos.toByteArray());

      minioClient.putObject("sinha", "emp", is , baos.toByteArray().length, "");



      //**** Get Object***///
      InputStream stream = minioClient.getObject("sinha", "emp");

      ObjectInputStream ois = new ObjectInputStream(stream);
      Employee object = (Employee)ois.readObject();

      System.out.println(" Name  "+object.name);
      System.out.println(" Id  "+object.id);

    } catch (Exception e) {
    System.out.println(e);
    }
  }
}


class Employee implements Serializable {


  String name;
  String id ;

  public Employee( String name , String id){
    this.name=name;
    this.id=id;
  }
}