public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng tín hiệu rời rạc (DiscreteSignal)
        DiscreteSignal discreteSignal = new DiscreteSignal(5.0, 10.0, 2.0);
        System.out.println("Discrete Signal Properties:");
        System.out.println("Amplitude: " + discreteSignal.getAmplitude());
        System.out.println("Frequency: " + discreteSignal.getFrequency());
        System.out.println("Period: " + discreteSignal.getPeriod());

        // Tính toán tín hiệu rời rạc với giá trị n
        int n = 4;
        System.out.println("\nCalculating for n = " + n);
        System.out.println("calculateX(" + n + "): " + discreteSignal.calculateX(n));
        System.out.println("discreteSignalSum(" + n + "): " + discreteSignal.discreteSignalSum(n));

        // Tạo đối tượng tín hiệu liên tục (ContinuousSignal)
        ContinuousSignal continuousSignal = new ContinuousSignal(3.0, 15.0, 1.5);
        System.out.println("\nContinuous Signal Properties:");
        System.out.println("Amplitude: " + continuousSignal.getAmplitude());
        System.out.println("Frequency: " + continuousSignal.getFrequency());
        System.out.println("Period: " + continuousSignal.getPeriod());

        // Sử dụng đối tượng Radar để phân tích tín hiệu rời rạc
        Radar radar = new Radar();
        System.out.println("\nRadar signal analysis for n = " + n + ": " + radar.radarDiscreteSignal(n));
    }
}
