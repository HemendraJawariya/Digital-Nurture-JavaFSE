public class FinancialForecasting {

    public static double forecastValue(double currentValue, double growthRate, int periods) {
        if (periods == 0) {
            return currentValue;
        }

        return forecastValue(currentValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void printForecastSeries(double startingValue, double growthRate, int periods) {
        for (int period = 0; period <= periods; period++) {
            double value = forecastValue(startingValue, growthRate, period);
            System.out.println("Period " + period + ": " + String.format("%.2f", value));
        }
    }

    public void printAnalysis() {
        System.out.println("Analysis:");
        System.out.println("The recursive forecast performs one recursive call per period, so it is O(n) time.");
        System.out.println("The recursion depth is O(n) space on the call stack.");
    }

    public static void main(String[] args) {
        printForecastSeries(1000.0, 0.08, 5);
        new FinancialForecasting().printAnalysis();
    }
}