package methods;

import computation.Function;
public abstract class Method {

    public Function function;
    public double upperBound;
    public double lowerBound;

    public abstract String formula();
    public abstract double approximate();
    public abstract String errorFormula();
    public abstract double calculateError();
    public abstract String toString();
}
