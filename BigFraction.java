import java.math.BigInteger;
import java.io.PrintWriter;

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
   * (1) Denominators are always positive. Therefore, negative fractions are represented 
   * with a negative numerator. Similarly, if a fraction has a negative numerator, it 
   * is negative.
   * 
   * (2) Fractions are not necessarily stored in simplified form. To obtain a fraction 
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
  public BigFraction(String str) {
    int slashIndex = str.indexOf("/");
    
    String numstr = str.substring(0, slashIndex);
    String denomstr = str.substring(slashIndex + 1);

    BigInteger num = new BigInteger(numstr);
    BigInteger denom = new BigInteger(denomstr);

    this.num = num;
    this.denom = denom;
  } // Fraction

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
      
      resultDenominator = this.denom.multiply(subtractMe.denom);
      resultNumerator = (this.num.multiply(subtractMe.denom)).subtract(subtractMe.num.multiply(this.denom));
      
      return new BigFraction(resultNumerator, resultDenominator).simplify();
  }
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
    BigInteger gcd = this.num.gcd(this.denom);
    return new BigFraction(this.num.divide(gcd), this.denom.divide(gcd));
}

  
  /**
   * Convert this fraction to a string for ease of printing.
   */
 /* */// public String toString() {
    // Special case: It's zero
  //  if (this.num.equals(BigInteger.ZERO)) {
   //   return "0";
  //  } // if it's zero */

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
  //  return this.num + "/" + this.denom;
 // } // toString()
// class BigFraction


  public BigFraction multiply(BigFraction multiple) throws Exception{
      BigInteger newNumerator = this.num.multiply(multiple.num);
      BigInteger newDenominator = this.denom.multiply(multiple.denom);

    return new BigFraction(newNumerator, newDenominator).simplify();
  }
  
  public BigFraction divide(BigFraction divisor) throws Exception{
      BigInteger newNumerator = this.num.divide(divisor.num);
      BigInteger newDenominator = this.denom.divide(divisor.num);
      
      if (newDenominator.compareTo(BigInteger.ZERO) == 0) {
          System.err.println("Can't divide by 0.");
      }
      
    return new BigFraction(newNumerator, newDenominator).simplify();
  }
  
  public static void main(String[] args) throws Exception{
    PrintWriter pen = new PrintWriter(System.out,true);
    BigFraction BigFraction1 = new BigFraction(1,3);
    BigFraction BigFraction2 = new BigFraction(1,5);
    pen.println(BigFraction1.multiply(BigFraction2));

    Counter c1 = new Counter();
    Counter c2 = new Counter();
    c1.increment();
    c1.increment();
    c2.increment();
    pen.println("c1: " + c1.get());
    pen.println("c2: " + c2.get());

    c1.reset();
    c2.reset();

    pen.println("Reset c1: " + c1.get());
    pen.println("Reset c2: " + c2.get());

  }
  // +---------+--------------------------------------------------------
  // | Methods |
  // +---------+

  // ...

} // class  BigFraction

class Counter {
  private int count;

  public Counter() {
    count = 0;
  }

  public Counter(int startingValue) {
    count = 0;
  }

  public void reset() {
    if (count != 0) {
      count = 0;
    }
  }

  public void increment() {
    count++;
  }

  public int get() {
    return count;
  }

  public String toString() {
    return "<" + count + ">";
  }
}



