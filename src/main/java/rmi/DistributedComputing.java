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

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 5/24/12
 */
public class DistributedComputing
{
   private Registry reg;

   public DistributedComputing() throws Exception
   {
      reg = LocateRegistry.getRegistry();
   }

   public int requestComputing(String stubName, int start, int end) throws Exception
   {
      Computer stubObj = (Computer)reg.lookup(stubName);
      return stubObj.rangeProduct(start, end);
   }

   public static void main(String[] args) throws Exception
   {
      DistributedComputing dc = new DistributedComputing();
      int N = Integer.parseInt(args[0]);
      int serverNb = args.length - 1;

      int finalResult = 1;
      for(int i = 0; i < serverNb; i++)
      {
         int rangeProduct = dc.requestComputing(args[i + 1], N*i + 1, N*(i+1));
         System.out.println("Result computed by stub " + args[i+1] + " is: " + rangeProduct);

         finalResult *= rangeProduct;
      }

      System.out.println("Final result is: " + finalResult);
   }

}
