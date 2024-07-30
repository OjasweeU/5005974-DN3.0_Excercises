
public interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee Classes
public class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of " + amount + " via PayPal.");
    }
}

public class StripeGateway {
    public void charge(double amount) {
        System.out.println("Charging " + amount + " using Stripe.");
    }
}


public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}

public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double amount) {
        stripeGateway.charge(amount);
    }
}


public class TestAdapterPattern {
    public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        paypal.processPayment(150.0);

        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        stripe.processPayment(200.0);
    }
}
