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

import java.net.JarURLConnection;
import java.net.URL;
import java.security.CodeSigner;
import java.util.jar.JarEntry;

/**
 * Check the code signer of a signed .jar file
 *
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 2/25/11
 */
public class TestSignedCode
{
   public static void main(String[] args) throws Exception
   {
      if(args.length < 1)
      {
         System.out.println("Not enough parameter");
         return;
      }

      String jarFile = args[0];

      URL url = Thread.currentThread().getContextClassLoader().getResource(jarFile);

      JarURLConnection jarConnection = (JarURLConnection)url.openConnection();
      jarConnection.connect();
      JarEntry jarEntry = jarConnection.getJarEntry();

      System.out.println(jarEntry.getCertificates()[0].getPublicKey().toString());

   }
}
