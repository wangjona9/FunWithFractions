/**
 * An implementation of a BigFraction calculator
 * to read in a string of BigFractions. Used for
 * the InteractiveCalculator & QuickCalculator classes.
 * 
 * @author Jonathan Wang
 * September 2023
 */
public class BFCalculator {
  private BigFraction lastComputedValue;
  private BigFraction[] registers = new BigFraction[30]; // Instantiate a BigFraction array

  public BigFraction evaluate(String exp) throws Exception {
    String[] tokens = exp.split("\\s+"); // Split the expression into tokens

    BigFraction total = null; // Initialize total to null

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];

      if (i == 0) {
        // The first token should be either a number or a variable
        if (token.matches("[a-zA-Z]")) {
          // It's a variable, fetch its value from registers
          total = getVariableValue(token.charAt(0));
        } else {
          total = new BigFraction(token); // It's a number
        }
      } else if (i % 2 == 1) {
        // Odd-indexed tokens should be operators
        char operator = token.charAt(0);

        if (total == null) {
          System.out.println("Invalid expression. Missing operand.");
          return null;
        }

        BigFraction operand;
        if (i + 1 < tokens.length) {
          // Fetch the next token as an operand
          if (tokens[i + 1].matches("[a-zA-Z]")) {
            operand = getVariableValue(tokens[i + 1].charAt(0));
          } else {
            operand = new BigFraction(tokens[i + 1]);
          }
        } else {
          System.out.println("Invalid expression. Missing operand.");
          return null;
        }

        switch (operator) { // Switch case for mathematical operators
          case '+':
            total = total.add(operand);
            break;
          case '-':
            total = total.subtract(operand);
            break;
          case '*':
            total = total.multiply(operand);
            break;
          case '/':
            total = total.divide(operand);
            break;
          default:
            System.out.println("Invalid operator: " + operator);
            return null;
        }
        i++; // Skip the next token (operand)
      }
    }

    lastComputedValue = total;
    return total;
  }

  public void store(String register) {
    if (register.startsWith("STORE ")) {
      char symbol = register.charAt(6); // Initialize the reg to a char at the index 6 (after STORE)
      registers[symbol - 'a'] = lastComputedValue; // Store into the lastComputedValue variable
    } else {
      System.out.println("Invalid register: " + register); // Check if the register is invalid
    }
  }

  public BigFraction getVariableValue(char symbol) {
    return registers[symbol - 'a']; // Get the end index
  }
}
