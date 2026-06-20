public class AdapterPattern {

    interface PaymentProcessor {
        void processPayment(double amount);
    }

    static class PayPalGateway {
        public void makePayment(double amount) {
            System.out.println("PayPal payment processed: $" + amount);
        }
    }

    static class StripeGateway {
        public void pay(double amount) {
            System.out.println("Stripe payment processed: $" + amount);
        }
    }

    static class PayPalAdapter implements PaymentProcessor {
        private final PayPalGateway gateway;

        PayPalAdapter(PayPalGateway gateway) {
            this.gateway = gateway;
        }

        @Override
        public void processPayment(double amount) {
            gateway.makePayment(amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor {
        private final StripeGateway gateway;

        StripeAdapter(StripeGateway gateway) {
            this.gateway = gateway;
        }

        @Override
        public void processPayment(double amount) {
            gateway.pay(amount);
        }
    }

    public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());

        paypal.processPayment(125.75);
        stripe.processPayment(89.50);
    }
}