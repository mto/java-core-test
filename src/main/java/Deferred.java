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
package org.exoplatform.sample.portal.web;

import java.util.HashMap;
import java.util.Map;

/**
 * I have just read the JQuery doc on deferred object, don't know exactly how they chain the callback. By the way, i guess
 * their mechanism is somewhat analogous to my Deferred prototype!
 *
 * Callback chaining with Finite State Machine model.
 *
 * Deferred object keeps a map of <State, Callback>. For each State, executing associated Callback makes State transitions
 *  of Deferred object.
 *
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 2/15/12
 */
public class Deferred
{
   State currentSt;

   Map<State, Callback> stCbMap;

   public Deferred()
   {
      stCbMap = new HashMap<State, Callback>();
      initAllCallbacks();

      triggerCallbackChain();
   }

   private void initAllCallbacks()
   {
      //Init callbacks;
   }

   private void triggerCallbackChain()
   {
      //
   }

}

abstract class State
{
   public abstract String getID();

   public abstract boolean isFinal();

   @Override
   public boolean equals(Object obj)
   {
      return (obj != null) && (obj instanceof State) && (this.getID().equals(((State)obj).getID()));
   }
}

abstract class Callback
{
   public final void execute(final Deferred deferred)
   {
      State newSt = _execute(deferred);
      if(deferred.currentSt != newSt)
      {
         deferred.currentSt = newSt;
         if (!newSt.isFinal())
         {
            Callback cb = deferred.stCbMap.get(newSt);
            if (cb != null)
            {
               cb.execute(deferred);
            }
         }
      }
   }

   public abstract State _execute(Deferred deferred);
}
