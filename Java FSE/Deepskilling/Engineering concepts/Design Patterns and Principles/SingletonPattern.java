public class SingletonPattern {

    public static final class Logger {
        private static volatile Logger instance;

        private Logger() {
        }

        public static Logger getInstance() {
            if (instance == null) {
                synchronized (Logger.class) {
                    if (instance == null) {
                        instance = new Logger();
                    }
                }
            }
            return instance;
        }

        public void log(String message) {
            System.out.println("LOG: " + message);
        }
    }

    public static void main(String[] args) {
        Logger first = Logger.getInstance();
        Logger second = Logger.getInstance();

        System.out.println("Same instance: " + (first == second));
        first.log("Singleton logger initialized.");
    }
}