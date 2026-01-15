package h2;

public class H2_main {
    private static final int[] FIBONACCI_CACHE = new int[1000];

    public static void main(String[] args) {
        benchmark(40);
    }

    public static void benchmark(int n) {
        System.out.println("Elapsed nanoseconds (fibonacci): " + benchmark(() -> fibonacci(n)));
        System.out.println("Elapsed nanoseconds (fibonacciCached): " + benchmark(() -> fibonacciCached(n)));
        System.out.println("Elapsed nanoseconds (fibonacciIterative): " + benchmark(() -> fibonacciIterative(n)));
    }

    private static long benchmark(Runnable task) {
        long timeBefore = System.nanoTime();
        task.run();
        return System.nanoTime() - timeBefore;
    }

    public static int fibonacci(int n) {
        if(n == 0) return 0;
        if(n <= 2) return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int fibonacciCached(int n) {
        if(FIBONACCI_CACHE[n] == 0) {
            if(n <= 2) FIBONACCI_CACHE[n] = 1;
            else FIBONACCI_CACHE[n] = fibonacciCached(n-1) + fibonacciCached(n-2);
        }
        return FIBONACCI_CACHE[n];
    }

    public static int fibonacciIterative(int n) {
        int before = 1;
        int current = 1;
        for(int i = 2; i < n; i++) {
            int newCurrent = before + current;
            before = current;
            current = newCurrent;
        }
        return current;
    }
}
