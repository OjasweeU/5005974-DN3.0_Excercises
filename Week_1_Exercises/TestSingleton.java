
public class TestSingleton {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("This is a log message.");

        if (logger1 == logger2) {
            System.out.println("Both loggers are the same instance.");
        }
    }
}