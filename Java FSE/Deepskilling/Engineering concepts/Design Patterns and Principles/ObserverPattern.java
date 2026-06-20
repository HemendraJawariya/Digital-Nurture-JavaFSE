import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    interface Observer {
        void update(String stockName, double price);
    }

    interface Subject {
        void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers();
    }

    static class Stock implements Subject {
        private final List<Observer> observers = new ArrayList<>();
        private final String name;
        private double price;

        Stock(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public void setPrice(double price) {
            this.price = price;
            notifyObservers();
        }

        @Override
        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(name, price);
            }
        }
    }

    static class MobileAppObserver implements Observer {
        @Override
        public void update(String stockName, double price) {
            System.out.println("Mobile app: " + stockName + " is now $" + price);
        }
    }

    static class WebDashboardObserver implements Observer {
        @Override
        public void update(String stockName, double price) {
            System.out.println("Web dashboard: " + stockName + " updated to $" + price);
        }
    }

    public static void main(String[] args) {
        Stock stock = new Stock("ACME", 150.0);
        stock.addObserver(new MobileAppObserver());
        stock.addObserver(new WebDashboardObserver());
        stock.setPrice(162.5);
    }
}