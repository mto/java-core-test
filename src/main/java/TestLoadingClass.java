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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A simple programm illustrates dynamic bytecode loading, it does following job:
 *
 * 1. Load content of TestClass.class file into memory, as an array of bytes
 *
 * 2. Generate the object Class<TestClass> from this array of bytes
 *
 * 3. Instantiate an object of class TestClass generated in second step
 *
 * 4. Invoke method on the object
 *
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 4/26/11
 */
public class TestLoadingClass
{
   /**
    * Load content of a .class file into memory as an array of bytes
    *
    * @param relativePath
    * @return
    */
   private static byte[] loadClassFileToMemory(String relativePath)
   {
      FileInputStream inputStream = null;

      try
      {
         File file = new File(TestLoadingClass.class.getResource(relativePath).toURI());
         inputStream = new FileInputStream(file);

         int size = (int)inputStream.getChannel().size();

         byte[] bytes = new byte[size];
         inputStream.read(bytes);

         return bytes;
      }
      catch(Exception ex)
      {
         ex.printStackTrace();

         return null;
      }
      finally
      {
         try
         {
            inputStream.close();
         }
         catch(IOException IOEx)
         {

         }
      }
   }

   /**
    * Define the Class object from array of bytes. Reflection is used as defineXXX methods are protected
    *
    * @param className
    * @param classLoader
    * @param bytes
    * @return
    */
   private static Class defineClassFromBytes(String className, ClassLoader classLoader, byte[] bytes)
   {
      try{

         //Don't know why this does not work on ClassLoader class, that forces me to use ugly loop
         //Method defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, new Byte[0].getClass(), Integer.class, Integer.class);

         Method[] declaredMethods = ClassLoader.class.getDeclaredMethods();
         Method defineClassMethod = null;
         for(Method method : declaredMethods)
         {
            if(method.getName().equals("defineClass") && method.getParameterTypes().length == 4)
            {
               defineClassMethod = method;
               break;
            }
         }

         defineClassMethod.setAccessible(true);
         return (Class)defineClassMethod.invoke(classLoader, className, bytes, 0, bytes.length);

      }
      catch(IllegalAccessException IllegalAccesEx)
      {
         IllegalAccesEx.printStackTrace();
      }
      catch(InvocationTargetException InvocationEx)
      {
         InvocationEx.printStackTrace();
      }

      return null;
   }

   public static void main(String[] args) throws Exception
   {

      byte[] contentOfLoadedClass = TestLoadingClass.loadClassFileToMemory("./TestClass.class");

      ClassLoader cl = Thread.currentThread().getContextClassLoader();

      Class clazz = defineClassFromBytes("TestClass", cl, contentOfLoadedClass);

      if(clazz == null)
      {
         System.out.println("Could not convert array of bytes into Class object");
      }
      else
      {
         TestClass instance = (TestClass)clazz.newInstance();

         instance.printClassMetaData();
      }

   }
}

/**
 *  Put the TestClass here so test doers need not care about classpath
 */
class TestClass
{
   static
   {
      System.out.println("The static block is executing, the class loader is linking your TestClass class. Congrats!\n");
   }

   public void printClassMetaData()
   {
      System.out.println("Fully qualified name: " + this.getClass().getCanonicalName());
      System.out.println("Class loader info: " + this.getClass().getClassLoader().toString());
   }
}
