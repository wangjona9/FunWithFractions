import java.util.Scanner;

/**
 * An implementation of an interactive calculator
 * to read in user input and repeatedly prompt after
 * printing the result.
 * 
 * @author Jonathan Wang
 * September 2023
 */
public class InteractiveCalculator {
    public static void main(String[] args) {
        BFcalculator calculator = new BFcalculator();
        Scanner scanner = new Scanner(System.in); // Initialize scanner

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("QUIT")) { // break if input = "QUIT"
                break;
            }

            try { // Code that may throw exception
                if (input.startsWith("STORE ")) {
                    calculator.store(input); // Call the store method if input = "STORE reg"
                } else {
                    BigFraction result = calculator.evaluate(input); 
                    if (result != null) {
                        System.out.println(result); // Print the result to the next line
                    }
                }
            } catch (Exception e) { // Catch the exception that the input is invalid
                System.out.println("Error: Please enter a valid expression (include spaces and use STORE for letter variables). Do not use two nums/operations/expressions in a row");
            }
        }

        scanner.close();
    }
}

