/*
 * Copyright (C) 2011 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 1/17/11
 */
public class TestThreadLocal
{
   private static ThreadLocal<String> threadLocalVariable = new ThreadLocal<String>();

   public static void main(String[] args)
   {

      threadLocalVariable.set("hoanglovesTrang");

      System.out.println("Value in main Thread: " + threadLocalVariable.get());

      Runnable runnable = new Runnable()
      {
         public void run()
         {
            threadLocalVariable.set("HoangLovesTrang");
            System.out.println("Value in child Thread: " + threadLocalVariable.get());
            try{
             Thread.currentThread().sleep(5000);
            }catch(InterruptedException ex)
            {
               ex.printStackTrace();
            }

            System.out.println("Value in child Thread this time is: " + threadLocalVariable.get());
         }
      };

      Thread t = new Thread(runnable);
      t.start();
      t.yield();

      threadLocalVariable.set("HoanglovesTrangInMainThread");
      System.out.println("Value in main thread this time is: " + threadLocalVariable.get());
   }
}
