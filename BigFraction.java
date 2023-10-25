import java.math.BigInteger;

/**
 * A simple implementation of BigFractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Jonathan Wang
 * @version 1.2 of August 2023
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are
   * represented
   * with a negative numerator. Similarly, if a fraction has a negative numerator,
   * it
   * is negative.
   * 
   * (2) Fractions are not necessarily stored in simplified form. To obtain a
   * fraction
   * in simplified form, one must call the `simplify` method.
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
  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   * Warning! Not yet stable.
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  } // Fraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   * Warning! Not yet stable.
   */
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  } // Fraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Warning! Not yet implemented.
   */
  /**
   * Build a new fraction by parsing a string.
   */
  public BigFraction(String str) {
    try {
      if (str.contains("/")) {
        int slashIndex = str.indexOf("/");

        String numstr = str.substring(0, slashIndex);
        String denomstr = str.substring(slashIndex + 1);

        this.num = new BigInteger(numstr);
        this.denom = new BigInteger(denomstr);
      } else {
        this.num = new BigInteger(str);
        this.denom = BigInteger.ONE; // Set the denomin to 1 for single-digit integers
      }
    } catch (NumberFormatException e) {
      // Handle invalid input gracefully for running in the command line
      System.err.println("Invalid number format: " + str);
      this.num = BigInteger.ZERO;
      this.denom = BigInteger.ONE;
    }
  }

  // Fraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add the fraction `addMe` to this fraction.
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the
    // product of this object's denominator
    // and addMe's denominator
    resultDenominator = this.denom.multiply(addMe.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  }// add(Fraction)

  public BigFraction subtract(BigFraction subtractMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultDenominator = this.denom.multiply(subtractMe.denom); // simplify numerator and denominator
    resultNumerator = (this.num.multiply(subtractMe.denom)).subtract(subtractMe.num.multiply(this.denom)); 

    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // subtract()

  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  public BigFraction simplify() {
    BigInteger gcd = this.num.gcd(this.denom); // Greatest Common Denominator
    return new BigFraction(this.num.divide(gcd), this.denom.divide(gcd));
  } // simplify()

  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    }

    // Whole number
    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    }

    // Return the string representation of the numerator,
    // a slash, and the string representation of the denominator
    return this.num + "/" + this.denom;
  } // toString()

  // Lump together the string represention of the numerator,
  // a slash, and the string representation of the denominator
  // return this.num.toString().concat("/").concat(this.denom.toString());
  // return this.num + "/" + this.denom;
  // } // toString()
  // class Fraction

  public BigFraction multiply(BigFraction multiple) throws Exception {
    BigInteger newNumerator = this.num.multiply(multiple.num); // multiply nums
    BigInteger newDenominator = this.denom.multiply(multiple.denom); // multiply denoms

    return new BigFraction(newNumerator, newDenominator).simplify();
  } // multiply(BigFraction)

  public BigFraction divide(BigFraction divisor) throws Exception {
    BigInteger newNumerator = this.num.multiply(divisor.denom); // divide nums
    BigInteger newDenominator = this.denom.multiply(divisor.num); // divide denoms

    if (newDenominator.compareTo(BigInteger.ZERO) == 0) {
      System.err.println("Can't divide by 0."); // Division by 0 exception
    } // divide(BigFraction)

    return new BigFraction(newNumerator, newDenominator).simplify();
  }

  public char intValue() {
    return 0;
  } // intValue()

  // +---------+--------------------------------------------------------
  // | Methods |
  // +---------+

  // ...

} // class BigFraction
