public class StrategyPattern {

    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card");
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal");
        }
    }

    static class PaymentContext {
        private PaymentStrategy strategy;

        public void setStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void executePayment(double amount) {
            if (strategy == null) {
                throw new IllegalStateException("Payment strategy not set");
            }
            strategy.pay(amount);
        }
    }

    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CreditCardPayment());
        context.executePayment(250.0);

        context.setStrategy(new PayPalPayment());
        context.executePayment(125.5);
    }
}