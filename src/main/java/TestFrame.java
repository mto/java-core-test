import javax.swing.*;

/**
 * User: Minh Hoang TO - hoang281283@gmail.com
 * Date: 1/7/11
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFrame extends JFrame {

  public TestFrame(String title)
  {
    super(title);

    setSize(300, 300);
  }

  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new TestFrame("Hoang loves Trang").setVisible(true);
      }
    });
  }
}
