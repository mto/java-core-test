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

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 1/21/11
 */
public class TestSoftReference
{

   public static class TestObject
   {
      private String state;

      public TestObject(String _state)
      {
         this.state = _state;
      }

      public String getState()
      {
         return this.state;
      }

      @Override
      protected void finalize() throws Throwable
      {
         System.out.println("Finalizing the object");
         super.finalize();

      }
   }
   public static void main(String[] args)
   {

      TestObject sharedTestObject = new TestObject("Hoang loves Trang");

      SoftReference<TestObject> softRef = new SoftReference<TestObject>(sharedTestObject);

      //WeakReference<TestObject> weakRef = new WeakReference<TestObject>(sharedTestObject);

      sharedTestObject = null;


      Runtime.getRuntime().gc();

      TestObject softlyReachedObject = softRef.get();

      System.out.println("Current value: " + softlyReachedObject.getState());

      //softRef = null;

      //Runtime.getRuntime().gc();

      WeakHashMap<TestObject, String> weakHashMap = new WeakHashMap<TestObject, String>();
      TestObject key = new TestObject("Hoang Trang");
      weakHashMap.put(key, "Hoang Trang Test");

      System.out.println("Current number of element: " + weakHashMap.keySet().size());

      key = null;

      System.out.println("Current number of element: " + weakHashMap.keySet().size());

   }
}
