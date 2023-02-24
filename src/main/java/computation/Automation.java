package computation;

import methods.Trapezoidal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties; 

import java.io.InputStream;
import java.io.FileInputStream; 
import java.io.IOException;

public class Automation {
    /* Global Field */
    public static ArrayList<Double> coefficients = new ArrayList<Double>();
    public static Function function;
    public static double upperBound;
    public static double lowerBound;
    public static int derivativeOrder = 2;
    public static InputStream input;
    public static Properties prop = new Properties();
    
    /* Initialization before Test */
    public static ArrayList<Double> initialize() {
        try {
            input = new FileInputStream("/Users/naimul7/JavaProjects/NumericalIntegration/src/main/java/config.properties");
            prop.load(input);

            for(int i = 0; i < Integer.parseInt(prop.getProperty("coefficients")); i++)
                coefficients.add(Double.parseDouble(prop.getProperty(Integer.toString(i+1))));

            upperBound = Double.parseDouble(prop.getProperty("upperBound"));
            lowerBound = Double.parseDouble(prop.getProperty("lowerBound"));

        } catch(FileNotFoundException fnfex) { fnfex.printStackTrace();
        } catch(IOException ioex) { ioex.printStackTrace(); }

//        coefficients.add(0.2);
//        coefficients.add(25.0);
//        coefficients.add(-200.0);
//        coefficients.add(675.0);
//        coefficients.add(-900.0);
//        coefficients.add(400.0);
        function = new Function(coefficients);

        return coefficients;
    }

    /* Testable Functional Units */
    public static void printFunction() { System.out.println(function); }
    public static void functionValue() { System.out.println("f(0.8) = " + function.getValue(0.8)) ; }
    public static void functionDerivative() { System.out.println(function.derivative(derivativeOrder)); }
    public static void functionIntegral() { System.out.println(function.integral()); } // Integral Order for the requirement to compute 1st order integral
    public static void printDerivative() { function.derivative(derivativeOrder); System.out.println(function.printDerivative()); }
    public static void printIntegral() { function.integral(); System.out.println(function.printIntegral()); }
    public static void derivativeValue() { System.out.println(function.getDerivativeValue(upperBound)); }
    public static void integralValue() { System.out.println(function.getIntegralValue(lowerBound)); }
    /* Completion after Test */
    public static void thanks() { System.out.println("\nThank you for running this program!"); }
    public static void closeEverything() { try { input.close(); } catch(IOException ioex) { ioex.printStackTrace(); } }
    /* Testable Trapezoidal Units */
    public static void errorDetermination() {
        function.formDerivative(2);
        Function error = new Function(function.getDerivative());

        error.formIntegral(1);
        System.out.println(error.printIntegral());
        System.out.println(error.getIntegralValue(0.8)); // 819.2 - 1843.2 + 1296 - 320
    }
//    public static void errorDetermination() {
//        function.formDerivative(2);
//        Function error
//    }
    
    
}
