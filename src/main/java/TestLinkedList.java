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

import java.util.LinkedList;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 1/18/11
 */
public class TestLinkedList
{

   public static void main(String[] args)
   {
      LinkedList<String> linkedList = new LinkedList<String>();

      linkedList.addFirst("Hoang");
      linkedList.addFirst("Loves");
      linkedList.addFirst("Trang");
      linkedList.addLast("Very");

      System.out.println(linkedList.get(0));
      System.out.println(linkedList.get(1));
      System.out.println(linkedList.get(2));
      System.out.println(linkedList.get(3));

   }
}
