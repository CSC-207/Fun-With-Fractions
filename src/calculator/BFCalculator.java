package calculator;

import java.util.Dictionary; // Use the Dictionary library
import java.util.Hashtable;  // Use the Hastable library

/** The primary workhorse.
 * 
 * @implNote The last computed value is by default set to null, so calling a
 * register that has not been set will usually result in an error message.
 * 
 * @author Albert-Kenneth Okine
 */
public class BFCalculator {
    // Create a dictionary containing String-Value pairs
    Dictionary<Character, BigFraction> registerDict = new Hashtable<>();

    // Keep track of the last computed value from the evaluate method
    BigFraction lastValue = null;

    /** Evaluate an expression, ignoring priority */
    public BigFraction evaluate(String expr) {

        // Split the expression by spaces, if possible
        String[] expressions = expr.split(" ");

        // Get the BigFraction value of the first expression to 
        // iteratively change with each expression
        BigFraction result = getFraction(expressions[0]);

        // Cumulatively evaluate, from left to right
        for (int i = 1; i < expressions.length; i += 2) {
            
            // Get the BigFraction value of the next expression
            BigFraction nextFraction = getFraction(expressions[i + 1]);
            
            // Change result according to the input operation
            // If not a valid operation, throw an exception
            switch (expressions[i]) {
                case "+": result = result.add(nextFraction);
                        break;
                case "-": result = result.subtract(nextFraction);
                        break;
                case "*": result = result.multiply(nextFraction);
                        break;
                case "/": result = result.divide(nextFraction);
                        break;
                default: throw new RuntimeException();
            } // switch (expressions[i])
        } // for (int i = 1; i < expressions.length; i ++)

        return lastValue = result; // set last value and return
    } // evaluate(String)

    /** Store the last value computed in the register */
    public void store(char register) {
        // Check if the register is a valid character
        if (lastValue == null 
           || !(Character.isLowerCase(register)))
        {
            // Throw an error, resulting in an error message
            throw new RuntimeException();
        } // if
        
        // Otherwise, store the last computed value in the register
        else registerDict.put(register, lastValue);
    } // store(char)

    /** Get the fraction given from the input String expressions */
    private BigFraction getFraction(String expressions) {
        // Check if the first character is alphabetic
        if ((Character.isLowerCase(expressions.charAt(0)))) {     
            //  Get the associated BigFraction value and return it
            return registerDict.get(expressions.charAt(0));
        }  // if
        
        // Otherwise, convert the expression and return it
        else return new BigFraction(expressions);
    } // getFraction(String)
} // class BFCalculator