package calculator;

import java.io.PrintWriter; // shorten calls to PrintWriter
import java.util.Scanner; // shorten calls to Scanner

/**
 * Repeatedly read-in a line from the terminal, use BFCalculator to compute
 * the result, and print the result for the user.
 * 
 * @author Albert-Kenneth Okine
 */
public class InteractiveCalculator {
  public static void main(String[] args) throws Exception {
    // Define objects to read-in input from the user and print
    // output to the terminal, both directed to/from StdOut
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scan = new Scanner(System.in);

    // Define a new BFCalculator object to use its methods
    BFCalculator Calculator = new BFCalculator();

    String expr; // Make an empty string to parse input with

    try { // Run Calculator
      // Loop through the user's input and evaluate each line
      while ((scan.hasNextLine()) && 
          !((expr = scan.nextLine()).equals("QUIT")))
      { 
        // Check whether to store a value in the register
        if (expr.contains("STORE")) {
          Calculator.store(expr.charAt(6));

        } else { // Otherwise, evaluate and print the result
          BigFraction result = Calculator.evaluate(expr);
          pen.println(result);
        } // if...else
      } // while
    } // try

    catch (Exception e) { // Catch invalid input
      // Define a PrintWriter object directed to StdErr
      PrintWriter penErr = new PrintWriter(System.err, true);
      // Print an error message to tell the user of the correct form
      penErr.println("USAGE : {FRACTION} {OPERATION} {FRACTION}...\n"
          + "      : STORE {LOWERCASE LETTER}");
    } // catch

    scan.close(); // close the scanner from memory leakage
  } // main(String[])
} // class InteractiveCalculator
