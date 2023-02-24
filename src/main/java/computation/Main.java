package computation;

import methods.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Numerical Integration methods. ");

        System.out.println();

        Function function = new Function(Automation.initialize());

        System.out.println(function);

//        System.out.println(function.printCoefficients());

        /* Calculation of f(x) Test */
        System.out.println(function.getValue());

        Trapezoidal trap = new Trapezoidal(function);
        trap.setBounds(Automation.lowerBound, Automation.upperBound);

        System.out.println(trap.formula());
        System.out.println(trap.printApproximate());
        System.out.println(trap.approximation());
        System.out.println(trap.errorFormula());
        trap.calculateError();
//        System.out.println(trap.calculateError());

//        Simpsons simpsons = new Simpsons(function);
//
//        System.out.println(simpsons.formula());
//        System.out.println(simpsons.approximate(Automation.lowerBound, Automation.upperBound));
//        System.out.println(simpsons.errorFormula());
//        System.out.println(simpsons.calculateError());
    }
}
