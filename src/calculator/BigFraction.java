package calculator;

import java.math.BigInteger;

/**
 * A simple implementation of Fractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Albert-Kenneth Okine
 * @version 2.0 of September 2023
 */
public class BigFraction {
    // +------------------+---------------------------------------------
    // | Design Decisions |
    // +------------------+
    /*
     * (1) Denominators are always positive. Therefore, negative fractions
     * are represented with a negative numerator. Similarly, if a fraction
     * has a negative numerator, it is negative.
     * 
     * (2) Fractions are always stored in their simplest form.
     */

    // +--------+-------------------------------------------------------
    // | Fields |
    // +--------+

    /** The numerator of the fraction. Can be positive, zero or negative. */
    BigInteger num;

    /** The denominator of the fraction. Must be non-negative. */
    BigInteger denom;

    // +--------------+-------------------------------------------------
    // | Constructors |
    // +--------------+

    /** Build a new fraction with numerator num and denominator denom. */
    public BigFraction(BigInteger num, BigInteger denom) {
        this.num = num;
        this.denom = denom;

        this.simplify(); // simplify the fraction
    } // BigFraction(BigInteger, BigInteger)

    /** Build a new fraction with numerator num and denominator denom. */
    public BigFraction(int num, int denom) {
        this.num = BigInteger.valueOf(num);
        this.denom = BigInteger.valueOf(denom);

        this.simplify(); // simplify the fraction
    } // BigFraction(int, int)

    /** Build a new fraction by parsing a string. */
    public BigFraction(String str) {
        // Split the string by the seperating char '/', if it exists
        String[] stringArr = str.split("/");
        
        this.num = new BigInteger(stringArr[0]);
        // If '/' exists, then use the denominator given; else, make
        // fraction with its denominator set to 1 (whole number)
        this.denom = (stringArr.length == 2)
            ? new BigInteger(stringArr[1])
            : new BigInteger("1");

        this.simplify(); // simplify the fraction
    } // BigFraction(String)

    // +---------+------------------------------------------------------
    // | Methods |
    // +---------+

    /** 
     * Reduce the fraction to its simplest form.
     * 
     * Called immediately after constructors.
     */
    private void simplify() {
        BigInteger resultNumerator;   // result of the numerator
        BigInteger resultDenominator; // result of the denominator

        // The reduced form of a fraction is its numerator and 
        // denominator divided by their greatest common divisor
        resultNumerator = this.num.divide(this.num.gcd(this.denom));
        resultDenominator = this.denom.divide(this.num.gcd(this.denom));

        // Change the object's num and denom to its reduced form
        this.num = resultNumerator;
        this.denom = resultDenominator;
    } // simplify()

    /** Get the denominator of this fraction. */
    public BigInteger denominator() {
        return this.denom;
    } // denominator()
    
    /** Get the numerator of this fraction. */
    public BigInteger numerator() {
        return this.num;
    } // numerator()
    
    /** Express this fraction as a double. */
    public double doubleValue() {
        return this.num.doubleValue() / this.denom.doubleValue();
    } // doubleValue()

    /** Convert this fraction to a string for ease of printing. */
    public String toString() {
        // Special case: It's zero
        if (this.num.equals(BigInteger.ZERO)) {
            return "0";
        } // if it's zero

        // Lump together the string represention of the numerator,
        // a slash, and the string representation of the denominator
        return this.num + "/" + this.denom;
    } // toString()

	/** Return the fractional form of this fraction */
    public BigFraction fractional() {
		return new BigFraction(this.num.mod(this.denom), this.denom);
    } // fractional()
    
    /** Add the fraction `addMe` to this fraction. */
    public BigFraction add(BigFraction addMe) {
        BigInteger resultNumerator;   // result of the numerator
        BigInteger resultDenominator; // result of the denominator

        // The denominator of the result is the product of this object's
        // denominator and addMe's denominator
        resultDenominator = this.denom.multiply(addMe.denom);
        // The numerator is more complicated
        resultNumerator = (this.num.multiply(addMe.denom))
                               .add(addMe.num.multiply(this.denom));

        // Return the computed value
        return new BigFraction(resultNumerator, resultDenominator);
    }// add(BigFraction)

    /** Subtract the fraction `subtractMe` from this fraction */
    public BigFraction subtract(BigFraction subtractMe) {
        BigInteger resultNumerator;   // result of the numerator
        BigInteger resultDenominator; // result of the denominator

        // The denominator of the result is the product of this object's
        // denominator and subtractMe's denominator
        resultDenominator = this.denom.multiply(subtractMe.denom);
        // The numerator is the same as add's, except subtracted
        resultNumerator = (this.num.multiply(subtractMe.denom))
                               .subtract(subtractMe.num.multiply(this.denom));

        // Return the computed value
        return new BigFraction(resultNumerator, resultDenominator);
    } // subtract(BigFraction)

    /** Multiply the fraction `multiplyMe` to this fraction */
    public BigFraction multiply(BigFraction multiplyMe) {
        BigInteger resultNumerator;   // result of the numerator
        BigInteger resultDenominator; // result of the denominator

        // The result of multiplying two fractions is equivalent to the
        // product of the numerators over the product of the denominators
        resultNumerator = this.num.multiply(multiplyMe.num);
        resultDenominator = this.denom.multiply(multiplyMe.denom);
        
        // Return the computed value
        return new BigFraction(resultNumerator, resultDenominator);
    } // multiply(BigFraction)

    /** Divide the fraction `divideMe` from this fraction */
    public BigFraction divide(BigFraction divideMe) {
        BigInteger resultNumerator;   // result of the numerator
        BigInteger resultDenominator; // result of the denominator

        // The result of dividing two fractions is equivalent to the
        // product of the dividend and the inverse of the divisor
        resultNumerator = this.num.multiply(divideMe.denom);
        resultDenominator = this.denom.multiply(divideMe.num);

        // Return the computed value
        return new BigFraction(resultNumerator, resultDenominator);
    } // divide(BigFraction)
} // class BigFraction