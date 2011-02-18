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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 1/17/11
 */
public class TestSerialize implements Serializable
{

   private StateObject stateObject = StateObject.NORMAL;

   public TestSerialize()
   {

   }

   public void setState(StateObject _state)
   {
      this.stateObject = _state;
   }

   public StateObject getState()
   {
      return this.stateObject;
   }

   public static void main(String[] args) throws Exception
   {
      ByteArrayOutputStream outputArray = new ByteArrayOutputStream(1000);

      TestSerialize toSerializedObect = new TestSerialize();
      StateObject testStateObject = new StateObject("HoanglovesTrang");
      toSerializedObect.setState(testStateObject);

      ObjectOutputStream objectOutput = new ObjectOutputStream(outputArray);
      objectOutput.writeObject(toSerializedObect);
      objectOutput.flush();

      ByteArrayInputStream inputArray = new ByteArrayInputStream(outputArray.toByteArray());
      ObjectInputStream objectInput = new ObjectInputStream(inputArray);

      TestSerialize reconstructedObject = (TestSerialize)objectInput.readObject();

      System.out.println("Current state of reconstructed object: " + reconstructedObject.getState().getState());

   }
}
