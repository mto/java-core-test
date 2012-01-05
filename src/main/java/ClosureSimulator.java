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

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 1/5/12
 */
public class ClosureSimulator
{
   /**
    * Function wrapped in returned DifferentialOperator object has access to two variables defined in environment
    * creating DifferentialOperator object.
    *
    * @param f
    * @param x
    * @return
    */
   public static DifferentialOperator generateDifferentialOperator(final RealFunction f, final double x)
   {
      return new DifferentialOperator()
      {
         @Override
         double takeApproximateDerivative(double dx)
         {
            return (f.valueAt(x + dx) - f.valueAt(x))/dx;
         }
      };
   }

   public static void main(String[] args)
   {
      RealFunction cubic = new RealFunction()
      {
         @Override
         public double valueAt(double x)
         {
            return x * x * x;
         }
      };

      DifferentialOperator diffOprAt2 = generateDifferentialOperator(cubic, 2);
      DifferentialOperator diffOprAt3 = generateDifferentialOperator(cubic, 3);

      //diffOprAt2 and diffOprAt3 simulates two closures consist of takeApproximateDefivative function
      //and (2, 3) - variables defined in closures 's creating environment.

      // (x*x*x)' = 3 * x * x;
      //
      //Let 's see how the two real numbers approaches 12 and 27
      System.out.println(diffOprAt2.takeApproximateDerivative(0.001));
      System.out.println(diffOprAt3.takeApproximateDerivative(0.001));
   }
}

abstract class DifferentialOperator
{
   abstract double takeApproximateDerivative(double dx);
}

abstract class RealFunction
{
   public abstract double valueAt(double x);
}
