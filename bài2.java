
public class Radar {
    public static double X(int n) {
        if (n >= 0 && n <= 15) {
            return 1 - (double) n / 15;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int n = 10; // Gán trực tiếp giá trị của n
        double result = X(n);
        System.out.println("Giá trị của X(" + n + ") là: " + result);
    }
}

