import java.util.Date;

// Interface 1: Payment Contract
interface Payable {
    void processPayment(double amount);
    String getPaymentStatus();
    void refundPayment(double amount);
}

// Interface 2: Taxable Contract 
interface Taxable {
    double calculateTax();
    void generateTaxInvoice();
}

// Interface 3: Logger Contract
interface Loggable {
    void logActivity(String action);
}

// Class 1: Credit Card Payment Implementation
class CreditCardPayment implements Payable, Taxable, Loggable {
    private String cardNumber;
    private String status = "PENDING";
    private double amount;
    private double tax;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        this.amount = amount;
        this.status = "SUCCESSFUL";
        System.out.println("Processing credit card payment of $" + amount);
        System.out.println("Card Number used: ****-****-****-" + cardNumber.substring(cardNumber.length() - 4));
        logActivity("Payment Processed");
    }

    @Override
    public String getPaymentStatus() {
        return status;
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Refunding credit card payment of $" + amount);
        this.status = "REFUNDED";
        logActivity("Refund Processed");
    }

    @Override
    public double calculateTax() {
        // Credit card transactions: Flat 18% GST
        this.tax = amount * 0.18;
        return tax;
    }

    @Override
    public void generateTaxInvoice() {
        System.out.println("\n--- CREDIT CARD TAX INVOICE ---");
        System.out.println("Transaction Type: Credit Card");
        System.out.println("Base Amount: $" + amount);
        System.out.println("Tax (18% GST): $" + tax);
        System.out.println("Total Charged: $" + (amount + tax));
        System.out.println("--------------------------------");
    }

    @Override
    public void logActivity(String action) {
        System.out.println("[LOG - CREDIT CARD] " + action + " at " + new Date());
    }
}

// Class 2: PayPal Payment Implementation (With Different Business Logic)
class PayPalPayment implements Payable, Taxable, Loggable {
    private String email;
    private String status = "PENDING";
    private double amount;
    private double tax;
    private boolean isEmailVerified;

    public PayPalPayment(String email, boolean isEmailVerified) {
        this.email = email;
        this.isEmailVerified = isEmailVerified;
    }

    @Override
    public void processPayment(double amount) {
        if (!isEmailVerified) {
            this.status = "FAILED";
            System.out.println("Payment FAILED: PayPal account " + email + " is not verified.");
            logActivity("Payment Failed (Unverified Account)");
            return;
        }
        
        this.amount = amount;
        this.status = "SUCCESSFUL";
        System.out.println("Processing PayPal payment of $" + amount + " for account: " + email);
        logActivity("Payment Processed");
    }

    @Override
    public String getPaymentStatus() {
        return status;
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Refunding $" + amount + " to PayPal email: " + email);
        this.status = "REFUNDED";
        logActivity("Refund Processed");
    }

    @Override
    public double calculateTax() {
        // PayPal Business Logic: 5% service fee + 10% processing tax
        double serviceFee = amount * 0.05;
        double processingTax = amount * 0.10;
        this.tax = serviceFee + processingTax;
        return tax;
    }

    @Override
    public void generateTaxInvoice() {
        System.out.println("\n--- PAYPAL DIGITAL RECEIPT ---");
        System.out.println("Transaction Type: PayPal Digital Wallet");
        System.out.println("Base Amount: $" + amount);
        System.out.println("PayPal Fees & Taxes (15%): $" + tax);
        System.out.println("Total Charged: $" + (amount + tax));
        System.out.println("--------------------------------");
    }

    @Override
    public void logActivity(String action) {
        System.out.println("[LOG - PAYPAL] Account (" + email + ") -> " + action + " at " + new Date());
    }
}

// Main Class to execute and test the code
public class Payment {
    public static void main(String[] args) {
        System.out.println("=== TEST 1: CREDIT CARD ===");
        CreditCardPayment cc = new CreditCardPayment("1234567890123456");
        cc.processPayment(100.0);
        cc.calculateTax();
        cc.generateTaxInvoice();
        cc.refundPayment(100.0);
        System.out.println("Current Status: " + cc.getPaymentStatus());

        System.out.println("\n=== TEST 2: PAYPAL (SUCCESS) ===");
        PayPalPayment paypal1 = new PayPalPayment("user@example.com", true);
        paypal1.processPayment(200.0);
        paypal1.calculateTax();
        paypal1.generateTaxInvoice();
        
        System.out.println("\n=== TEST 3: PAYPAL (FAILURE DUE TO LOGIC) ===");
        PayPalPayment paypal2 = new PayPalPayment("scammer@fake.com", false);
        paypal2.processPayment(50.0);
        System.out.println("Current Status: " + paypal2.getPaymentStatus());
    }
}
