public class QuickCalculator {
    public static void main(String[] args) {
        BFcalculator calculator = new BFcalculator();

        for (String arg : args) { // For-each loop for the args
            try { // Code that may throw exception
                if (arg.startsWith("STORE")) {
                    calculator.store(arg); // Call the store method for the args
                } else {
                    BigFraction result = calculator.evaluate(arg); // Call the evaluate method 
                    if (result != null) {
                        System.out.println(arg + " = " + result); // Print the result of the expression
                    }
                }
            } catch (Exception e) { // Catch the exception
                e.printStackTrace();
            }
        }
    }
}
