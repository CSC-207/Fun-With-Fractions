package calculator;

import java.io.PrintWriter; // shorten calls to PrintWriter

/** Quickly test BFCalculator using the command line
 * @author Albert-Kenneth Okine
 */
public class QuickCalculator {
  public static void main (String[] args) {
    // Define a PrintWriter object directed to StdOut
    PrintWriter pen = new PrintWriter(System.out, true);
    
    // Define a new BFCalculator object to use its methods
    BFCalculator Calculator = new BFCalculator();

    try { // Run Calculator
      // Loop through the user's input and evaluate each one
      for (String expr : args) {
        // Check if the user wants to store a value in the register
        if (expr.contains("STORE")) {
          Calculator.store(expr.charAt(6));
        
        // Otherwise, evaluate the expression and print the result
        } else {
          BigFraction result = Calculator.evaluate(expr);
          pen.println(expr + " = " + result);
        } // if...else
      } // for (String expr : args)
    } // try

    catch (Exception e) { // Catch invalid input
      // Define a PrintWriter object directed to StdErr
      PrintWriter penErr = new PrintWriter(System.err, true);
      // Print an error message to tell the user of the correct form
      penErr.println("USAGE : {FRACTION} {OPERATION} {FRACTION}...\n" +
                     "      : STORE {LOWERCASE LETTER}");
    } // catch

  } // main(String[])
} // QuickCalculator