/**
 * User: Minh Hoang TO - hoang281283@gmail.com
 * Date: 1/7/11
 * Time: 8:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestMultiThread {

  public static void main(String[] args)
  {
    for(int i = 0; i < 100; i++)
    {
        final int tempValue = i;

        Thread t = new Thread(new Runnable(){
          public void run() {
            System.out.println(tempValue);
          }
        });
        t.start();
    }

    new Thread(new Runnable()
    {
       public void run()
       {
          while(true)
          {
             try{
                Thread.sleep(5000);
             }catch(InterruptedException ex)
             {
               ex.printStackTrace();
             }
          }
       }
    }).start();
  }
}
