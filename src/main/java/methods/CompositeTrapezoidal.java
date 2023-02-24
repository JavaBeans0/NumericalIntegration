package methods;

import computation.Function;

public class CompositeTrapezoidal extends Trapezoidal {
    /* Private Data Fields */
    private int segment;
    /* Constructor */
    public CompositeTrapezoidal(Function function) { super(function); }
    /* Getter Setter Methods */
    public void setSegment(int segment) { this.segment = segment; }
    public int getSegment() { return this.segment; }
    /* Overriden Computational Methods */
    @Override public String formula() { return "I = (b-a) * [f(a) + 2 * sigma(f(x_i)) + f(b)] / 2\nFor i = 1 to (n-1)"; }
    @Override public double approximate() { return (super.upperBound - super.lowerBound ) / ( super.function.getValue(super.lowerBound) + super.function.getValue(super.upperBound) ) / 2.0; }
    @Override public String errorFormula() { return ""; }
    public String error() { return "Error: E = " + calculateError(); }
    @Override public double calculateError() {
        super.function.derivative(2);
        Function error = new Function(super.function.getDerivative());
        error.integral();

        return error.integrateValue(super.upperBound) - error.integrateValue(super.lowerBound);
    }
}
