public class bai1 {
    public static void main(String[] args) {
        DiscreteSignal discreteSignal = new DiscreteSignal(5.0, 50.0);
        ContinuousSignal continuousSignal = new ContinuousSignal(3.0, 60.0, 2.0);

        System.out.println("Tín hiệu rời rạc:");
        System.out.println("Biên độ: " + discreteSignal.getAmplitude());
        System.out.println("Tần số: " + discreteSignal.getFrequency());
        System.out.println("Chu kỳ: " + discreteSignal.getPeriod());
        System.out.println("Bước sóng: " + discreteSignal.getWavelength());

        int[] x = {1, 2, 3, 4, 5}; 
        int n = 2; 
        System.out.println("x(" + n + ") = " + DiscreteSignal.DiscreteSum.calculateSum(x, n));

        System.out.println("\nTín hiệu liên tục:");
        System.out.println("Biên độ: " + continuousSignal.getAmplitude());
        System.out.println("Tần số: " + continuousSignal.getFrequency());
        System.out.println("Chu kỳ: " + continuousSignal.getPeriod());
        System.out.println("Bước sóng: " + continuousSignal.getWavelength());
    }
}