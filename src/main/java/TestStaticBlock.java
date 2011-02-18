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

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 1/19/11
 */
public class TestStaticBlock
{

   private static Map<String, String> sharedMap = new HashMap<String, String>();

   static{
     addEntry("Hoang", "Loves Trang");
   }

   //private static Map<String, String> sharedMap = new HashMap<String, String>();


   public static void addEntry(String key, String value)
   {
      sharedMap.put(key, value);
   }

   public static String getValue(String key)
   {
      return sharedMap.get(key);
   }

   public static void main(String[] args)
   {

   }
}
