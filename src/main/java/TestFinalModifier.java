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
public class TestFinalModifier
{

   public void declareFinalVariable()
   {
      final String s = "hi!";
   }

   public void declareOrdinaryVariableAndAssignItToNULL()
   {
      String s = "hi!";
      s = null; //Expect that the garbage collector free memory faster than the case of final variable
   }

   public static void main(String[] args) throws Exception
   {

      TestFinalModifier testFinalModifier = new TestFinalModifier();

      long memoryBeforeTest = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

      System.out.println("Memory before test: " + memoryBeforeTest);

      long start_1 = System.currentTimeMillis();
      for(int i =0; i< 1000000000; i++)
      {
         testFinalModifier.declareFinalVariable();
      }
      long end_1 = System.currentTimeMillis();

      long memoryAfterFinalVariableTest = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

      System.out.println("Time to run test on final variable: " + (end_1 - start_1));
      System.out.println("Memory after final variable test: " + memoryAfterFinalVariableTest);

      //System.gc();//To avoid side effect

      //System.out.println("Memory after call to gc(): " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));

      long start_2 = System.currentTimeMillis();

      for(int i =0; i< 1000000000; i++)
      {
         testFinalModifier.declareOrdinaryVariableAndAssignItToNULL();
      }

      long end_2 = System.currentTimeMillis();
      long memoryAfterOrdinaryVariableTest = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

      System.out.println("Time to run test on ordinary variable: " + (end_2 -start_2));
      System.out.println("Memory after ordinary variable test: " + memoryAfterOrdinaryVariableTest);

   }
}
