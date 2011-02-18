import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: hoang_to
 * Date: 1/6/11
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestXMLMarshalling {

  public static void main(String[] args) throws Exception
  {
    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(1000);

    JAXBContext context = JAXBContext.newInstance(TestObject.class);
    Marshaller marshaller = context.createMarshaller();

    TestObject marshalledObject = new TestObject();
    marshalledObject.setObjectName("Hoang loves Trang very much");

    marshaller.marshal(marshalledObject, arrayOutputStream);

    System.out.println(arrayOutputStream.toString());

    Unmarshaller unmarshaller = context.createUnmarshaller();

    ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());

    TestObject object = (TestObject)unmarshaller.unmarshal(arrayInputStream);

    System.out.println("Test object: " + object.getObjectName());
  }
}
