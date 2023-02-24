package computation;

import java.util.Scanner;
import java.util.ArrayList;

public class Function {
    /* Private Fields */
    private int degree;
    private ArrayList<Double> coefficients;
    private double value;
    private Function derivative;
    private Function integral;
    private int integralOrder;
    private int derivativeOrder;
    private boolean derived;
    /* Overloaded Constructors */
    public Function(boolean scan) {
        if(scan) {
            Scanner input = new Scanner(System.in);

            System.out.print("What is the degree of the polynomial: ");
            this.degree = input.nextInt();
            coefficients = new ArrayList<Double>(this.degree + 1);

            System.out.print("Enter constant value: ");
            this.coefficients.add(input.nextDouble());
            for (int i = 1; i < degree; i++) {
                System.out.print("Enter coefficient for x" + (i == 1 ? "" : "^" + i) + ": ");
                this.coefficients.add(input.nextDouble());
            }
        }
    }

    public Function(ArrayList<Double> coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.size()-1;
        this.derived = true;
    }

    public Function(Function function) {
        this.degree = function.degree;
        this.coefficients = function.coefficients;
        this.value = function.value;
        this.derivative = function.derivative;
        this.derivativeOrder = function.derivativeOrder;
        this.integral = function.integral;
        this.integralOrder = function.integralOrder;
        this.derived = function.derived;
    }

    public void addCoefficient(double coefficient) {
        if(coefficients == null) {
            this.coefficients = new ArrayList<Double>();
            this.degree = -1;
        }

        this.coefficients.add(coefficient);
        this.degree++;
    }

    /* Getter Setter Methods */
    public int getDegree() { return degree; }
    public void setDegree(int degree) { this.degree = degree; }
    public ArrayList<Double> getCoefficients() { return coefficients; }
    public void setCoefficients(ArrayList<Double> coefficients) { this.coefficients = coefficients; }
    public Function getDerivative() { return this.derivative; }
    public Function getIntegral() { return this.integral; }
    /* Critical Methods */
    public String getValue() {
        System.out.print("Enter a value you wish to compute f(x): ");
        this.value = (new Scanner(System.in)).nextDouble();
        return "f(" + this.value + ") = " + getValue(this.value);
    }

    public double getValue(double value) {
        return getTerm(this.degree, 0, value, (derived ? 0 : 1));
    }

    public String printValue(double value) {
        this.value = value;
        return "f(" + value + ") = " + getValue(this.value);
    }

    public String getDerivativeValue(double value) { this.value = value; return "f(" + value + ") = " + deriveValue(value); }
    public String getIntegralValue(double value) { this.value = value; return "f(" + value + ") = " + integrateValue(value); }
    public double deriveValue(double value) { return this.derivative.getValue(value); }
    public double integrateValue(double value) { return this.integral.getValue(value); }
    public void integrate() { this.derived = false; }
    public void derive() { this.derived = true; }

    public String derivative(int order) { formDerivative(order); return "f(x) = " + getDerivativeTerm(this.degree, order); }
    public String integral() { formIntegral(1); return "f(x) = " + getIntegralTerm(this.degree); }
    @Override public String toString() { return "f(x) = " + getDerivativeTerm(this.degree, 0); }
    public String toString(boolean derived) { return "f(x) = " + (derived ? getDerivativeTerm(this.degree, 0) : getIntegralTerm(this.degree)); }
    /* Set the coefficients for the derivative of the function to the nth order */
    public Function formDerivative(int order) {
        if(this.derived)
            this.derived = true;

        this.derivativeOrder = order;
        this.derivative = new Function(false);
        for(int i = order; i < this.degree+1; i++)
            this.derivative.addCoefficient(coefficients.get(i) * factorial(i, i - order));

        return this.derivative;
    }
    /* Set the coefficients for the integral of the function to the nth order */
    public Function formIntegral(int order) {
        this.derived = false;
        this.integralOrder = order;
        this.integral = new Function(false);
        for(int i = 0; i < this.degree+1; i++)
            this.integral.addCoefficient(this.coefficients.get(i));

        return this.integral;
    }

    private double getTerm(int cursor, int reach, double value, int exponentUp) {
        if(cursor == reach)
            return this.coefficients.get(cursor) * Math.pow(value, cursor+exponentUp);

        return (this.coefficients.get(cursor) * Math.pow(value, cursor+exponentUp)) + getTerm(cursor-1, reach, value, exponentUp);
    }

    public String getDerived() { return (this.derived ? "Derivative" : "Integral"); }
    private String getDerivativeTerm(int cursor, int reach) {
        if(cursor == reach)
            return Double.toString(factorial(cursor, cursor - reach) * this.coefficients.get(cursor));

        return (this.coefficients.get(cursor) * (reach == 0 ? 1: factorial(cursor, cursor - reach))) + "x" + (cursor - reach == 1 ? "": "^" + (cursor - reach)) + (this.coefficients.get(cursor-1) > 0 ? " +": " ") + getDerivativeTerm(cursor-1, reach);
    }

    /* Implementation of 1st Order Integral */
    private String getIntegralTerm(int cursor) {
        if(cursor == 0)
            return this.coefficients.get(cursor) + "x";

        return this.coefficients.get(cursor) / (cursor + 1) + "x^" + (cursor+1) + (this.coefficients.get(cursor-1) > 0 ? " +" : " ") + getIntegralTerm(cursor-1);
    }

    private int factorial(int n, int reach) {
        if(n == reach)
            return 1;

        return n * factorial(n-1, reach);
    }

    public String printDerivative() { return this.derivative.toString(derived); }
    public String printIntegral() { return this.integral.toString(derived); }

    public String printCoefficients() {
        String coefficiencies = "";

        for(double coefficient: coefficients)
            coefficiencies += ("\n" + coefficient);

        return coefficiencies;
    }
}


