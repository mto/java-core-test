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

import java.io.File;
import java.io.FileReader;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 3/19/12
 */
public class TestRhinoScripting
{
  public static void main(String[] args) throws Exception
  {
    if (args.length < 1)
    {
      return;
    }

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine rhino = manager.getEngineByExtension("js");

    Bindings bindings = rhino.createBindings();
    String fileName = null;

    for (String arg : args)
    {
      if (arg.startsWith("-D"))
      {
        int index = arg.indexOf("=");
        String paramName = arg.substring(2, index);
        String paramValue = arg.substring(index + 1);

        bindings.put(paramName, paramValue);
      }
      else if (fileName == null)
      {
        fileName = arg;
      }
    }

    if(fileName == null)
    {
      throw new IllegalArgumentException("Script file name is missing!");
    }
    else
    {
      Compilable compiler = (Compilable)rhino;
      FileReader reader = new FileReader(new File(fileName));

      CompiledScript compiledScript = compiler.compile(reader);
      System.out.println(compiledScript.toString());

      Object result = compiledScript.eval(bindings);
      System.out.println(result.toString());
    }


  }
}
