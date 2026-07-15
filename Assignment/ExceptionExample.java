public class ExceptionExample {
    public static void main(String[] args) {
        int numerator = 10;
        int denominator = 0;

        try {
            // This line will throw an ArithmeticException because we cannot divide by zero
            int result = numerator / denominator;
            System.out.println("Result: " + result); 
        } catch (ArithmeticException e) {
            // This block executes ONLY if an ArithmeticException occurs inside the try block
            System.out.println("Error: You cannot divide a number by zero!");
            System.out.println("Exception message: " + e.getMessage());
        }

        // The program continues running normally after the try-catch block
        System.out.println("The program successfully finished running.");
    }
}