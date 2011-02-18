import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * User: Minh Hoang TO - hoang281283@gmail.com
 * Date: 1/12/11
 * Time: 9:43 AM
 */
public class TestFutureTask {

  public static void main(String[] args) throws InterruptedException, ExecutionException
  {
    FutureTask<TestObject> testTask = new FutureTask<TestObject>(new Callable<TestObject>() {
      public TestObject call() throws Exception {
        TestObject testObject = new TestObject();
        testObject.setObjectName("Hoang loves Trang very much");
        return testObject;
      }
    });

    testTask.run();

    TestObject result = testTask.get();

    System.out.println(result.getObjectName());
  }
}
