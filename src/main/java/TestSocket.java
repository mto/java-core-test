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

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 1/14/11
 */
public class TestSocket
{

   public static class ServerThread implements Runnable
   {
      public void run()
      {
         try{
            ServerSocket server = new ServerSocket(8080, 8081, InetAddress.getLocalHost());
            int counter = 0;

            while(true)
            {
               Socket client = server.accept();
               counter++;
               System.out.println("Receving request from client: " + counter + " content: " + client.getInputStream().read());
            }
         }catch(Exception ex)
         {
            ex.printStackTrace();
         }
      }
   }

   public static class ClientThread implements Runnable
   {
      public void run()
      {
         try{
            Socket socket = new Socket(InetAddress.getLocalHost(), 8080);
            socket.getOutputStream().write(1998);
         }catch(Exception ex)
         {
            ex.printStackTrace();
         }
      }
   }

   public static void main(String[] args) throws Exception
   {

      new Thread(new ServerThread()).start();

      for(int i =0; i< 5; i++){


       Thread t = new Thread(new ClientThread());
        t.start();
        t.join();
      }
   }
}
