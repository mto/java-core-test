/*
 * Copyright (C) 2012 eXo Platform SAS.
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

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 5/24/12
 */
public class ComputerServer
{

   public static void main(String[] args) throws Exception
   {
      if(args.length < 1)
      {
         throw new IllegalArgumentException("Argument length must be greater than 1");
      }

      Computer remoteObj = new ComputerImpl();
      Computer stubObj = (Computer)UnicastRemoteObject.exportObject(remoteObj, 0);

      Registry localReg = LocateRegistry.getRegistry();
      localReg.rebind(args[0], stubObj);
   }
}
