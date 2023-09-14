public class BFcalculator {
    private BigFraction lastComputedValue;
    private BigFraction[] registers = new BigFraction[10];

    public BigFraction evaluate(String exp) throws Exception {
        String[] tokens = exp.split("\\s+"); // Split the expression into tokens

        BigFraction total = new BigFraction(tokens[0]); // Initialize total with the first token

        for (int i = 1; i < tokens.length; i += 2) {
            char operator = tokens[i].charAt(0);
            BigFraction operand = new BigFraction(tokens[i + 1]);

            switch (operator) {
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
        }
        return total;
    }

    public void store(char register) {
        int index = Character.getNumericValue(register);
        if (index >= 0 && index < 10) {
            registers[index] = lastComputedValue;
        } else {
            System.out.println("Invalid register index. Use a digit from 0 to 9.");
        }
    }

    public BigFraction getRegisterValue(char register) {
        int index = Character.getNumericValue(register);
        if (index >= 0 && index < 10) {
            return registers[index];
        } else {
            System.out.println("Invalid register index. Use a digit from 0 to 9.");
            return null;
        }
    }
}
