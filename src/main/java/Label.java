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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 3/2/11
 */
public class Label extends ResourceBundle
{

   final static Map<String, String> keyValueMapping = new HashMap<String, String>();

   static{
      keyValueMapping.put("firstElement", "firstValue");
      keyValueMapping.put("secondElement", "secondValue");
      keyValueMapping.put("thirdElement", "thirdValue");
   }

   @Override
   protected Object handleGetObject(String key)
   {
      return keyValueMapping.get(key);
   }

   @Override
   public Enumeration<String> getKeys()
   {
      Enumeration<String> enumeration = new Enumeration<String>()
      {
         final String[] elements = new String[]{"firstElement", "secondElement", "thirdElement"};

         private int counter = 0;

         public boolean hasMoreElements()
         {
            return counter < 3;
         }

         public String nextElement()
         {
            String element = elements[counter];
            counter++;
            return element;  //To change body of implemented methods use File | Settings | File Templates.
         }
      };

      return enumeration;
   }
}
