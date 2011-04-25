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
 * @date 4/25/11
 */
public class TestLoopPerformance
{

   public static void main(String[] args)
   {
      long start_1 = System.currentTimeMillis();

      String variableWithWideScope;
      for(int i=0; i < 100000000; i++)
      {
         variableWithWideScope = "" + i;
      }

      long end_1 = System.currentTimeMillis();

      System.out.println("Test with wide-scope variable: " + (end_1 - start_1));

      long start_2 = System.currentTimeMillis();

      for(int i =0; i < 100000000; i++)
      {
         String variableWithNarrowScope = "" + i;
      }

      long end_2 = System.currentTimeMillis();

      System.out.println("Test with narrow-scope variable: " + (end_2 - start_2));
   }

}
