import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * User: Minh Hoang TO - hoang281283@gmail.com
 * Date: 1/6/11
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class TestObject {

  @XmlTransient
  private String objectName;

  public TestObject()
  {
    this.objectName = "HoangloveTrang";
  }

  public void setObjectName(String _objectName)
  {
    this.objectName = _objectName;
  }

  public String getObjectName()
  {
    return this.objectName;
  }
}
