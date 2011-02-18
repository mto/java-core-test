/**
 * User: Minh Hoang TO - hoang281283@gmail.com
 * Date: 1/10/11
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassOverriding {

  public static class SimpleTestObject
  {
    public SimpleTestObject()
    {
      init();
    }

    private void init()
    {
      System.out.println("SimpleTestObject");
    }
  }

  public static class RealTestObject extends SimpleTestObject
  {
    public RealTestObject()
    {
      super();
    }

    public void init()
    {
      System.out.println("RealTestObject");
    }
  }

  public static void main(String[] args)
  {
    new RealTestObject();
  }
}
