import java.util.Arrays;

public class ECommercePlatformSearchFunction {

    private final String[] products;

    public ECommercePlatformSearchFunction(String[] products) {
        this.products = products.clone();
        Arrays.sort(this.products);
    }

    public int searchProduct(String target) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = products[middle].compareToIgnoreCase(target);

            if (comparison == 0) {
                return middle;
            }
            if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public void printAnalysis() {
        System.out.println("Analysis:");
        System.out.println("Binary search runs in O(log n) time on a sorted product list.");
        System.out.println("Sorting the products once costs O(n log n); space is O(n) for the copy.");
    }

    public static void main(String[] args) {
        ECommercePlatformSearchFunction search = new ECommercePlatformSearchFunction(
            new String[] {"Shoes", "Watch", "Laptop", "Headphones", "Camera"}
        );

        int index = search.searchProduct("Laptop");
        System.out.println("Product index: " + index);
        search.printAnalysis();
    }
}