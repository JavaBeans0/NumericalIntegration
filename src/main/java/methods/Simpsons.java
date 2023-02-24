package methods;

import computation.Function;

public class Simpsons extends Method {

    /* Overloaded Constructors */
    public Simpsons() { super.function = new Function(true); }
    public Simpsons(Function function) { super.function = function; }
    /* Getter Setter Methods */

    /* Mathematical Formulas */
    public String formula() {
        return "where?";
    }
    public double approximate() { return 1.0; }
    public String errorFormula() { return "what"; }
    public double calculateError() { return 1.0; }

    @Override public String toString() { return super.function.toString(); }
}
