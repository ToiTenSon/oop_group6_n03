public class Discrete_Signal {
    public static double calculateX(int n) {
        if (n == 0) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int delta(int n) {
        return (n == 0) ? 1 : 0;
    }
    public static double discreteSignalSum(int n) {
        double result = 0;
        for (int k = Integer.MIN_VALUE; k <= Integer.MAX_VALUE; k++) {
            result += calculateX(k) * delta(n - k);
        }
        return result;
    }
}
