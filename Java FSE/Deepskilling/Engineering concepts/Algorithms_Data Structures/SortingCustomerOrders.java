import java.util.Arrays;

public class SortingCustomerOrders {

    public static class Order {
        private final String orderId;
        private final String customerName;
        private final double totalPrice;

        public Order(String orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        @Override
        public String toString() {
            return orderId + " | " + customerName + " | $" + totalPrice;
        }
    }

    public static void mergeSort(Order[] orders) {
        if (orders.length < 2) {
            return;
        }

        int middle = orders.length / 2;
        Order[] left = Arrays.copyOfRange(orders, 0, middle);
        Order[] right = Arrays.copyOfRange(orders, middle, orders.length);

        mergeSort(left);
        mergeSort(right);
        merge(orders, left, right);
    }

    private static void merge(Order[] target, Order[] left, Order[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int targetIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].getTotalPrice() >= right[rightIndex].getTotalPrice()) {
                target[targetIndex++] = left[leftIndex++];
            } else {
                target[targetIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            target[targetIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            target[targetIndex++] = right[rightIndex++];
        }
    }

    public void printAnalysis() {
        System.out.println("Analysis:");
        System.out.println("Merge sort runs in O(n log n) time and O(n) extra space.");
        System.out.println("It is stable, which helps preserve order for equal totals.");
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order("ORD-1001", "Asha", 240.50),
            new Order("ORD-1002", "Ravi", 89.99),
            new Order("ORD-1003", "Neha", 320.00),
            new Order("ORD-1004", "Ishaan", 145.75)
        };

        mergeSort(orders);
        for (Order order : orders) {
            System.out.println(order);
        }

        new SortingCustomerOrders().printAnalysis();
    }
}