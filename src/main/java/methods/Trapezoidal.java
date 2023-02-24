package methods;

import computation.Function;

public class Trapezoidal extends Method {

    /* Overloaded Constructors */
    public Trapezoidal() { super.function = new Function(true);  }
    public Trapezoidal(Function function) {
        super.function = function;
    }
    /* Getter Setter Methods */
    public Function getFunction() { return super.function; }
    public void setFunction(Function function) { super.function = function; }

    /* Mathematical Computation */
    public String formula() { return "I = (b-a) * (f(a) + f(b)) / 2"; }
    public String approximation() { return "The area is: I = " + approximate(); }
    public String printApproximate() { return "I = (" + super.upperBound + " - " + super.lowerBound + ") * (" + super.function.getValue(super.lowerBound) + " + " + super.function.getValue(super.upperBound) + ") / 2.0"; }
    public double approximate() { return (super.upperBound - super.lowerBound ) * ( super.function.getValue(super.lowerBound) + super.function.getValue(super.upperBound) ) / 2.0; }
    public String errorFormula() { return "E = -1 / 12 * erf(b-a) * (b-a)^3"; }
    public double calculateError() {
        function.formDerivative(4);
        Function error = new Function(function.getDerivative());

        error.formIntegral(1);
        System.out.println(error.printIntegral());
        System.out.println("Using above integral, compute f(upperBound) = f(lowerBound) == f(0.8) - f(0.0)");
//        System.out.println(error.getIntegralValue(0.8));

        return -1 / 12 * error.integrateValue(super.upperBound) - error.integrateValue(super.lowerBound) * Math.pow(super.upperBound - super.lowerBound, 3);
    }
    public String error() { return "Error: E = " + calculateError(); }

    public void setBounds(double lowerBound, double upperBound) {//15360
        super.lowerBound = lowerBound;
        super.upperBound = upperBound;
    }
    @Override public String toString() { return super.function.toString(); }
}
