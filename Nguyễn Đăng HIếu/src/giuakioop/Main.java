package giuakioop;

public class Main {
    public static void main(String[] args) {
        DiscreteSignal discreteSignal = new DiscreteSignal(5.0, 1.0);
        Radar radar = new Radar(discreteSignal);
        radar.displayRadarSignals(4);

        ContinuousSignal continuousSignal = new ContinuousSignal(5.0, 1.0, 3.0);
        System.out.println("Continuous Signal:");
        System.out.println("Amplitude: " + continuousSignal.getAmplitude());
        System.out.println("Frequency: " + continuousSignal.getFrequency());
        System.out.println("Period: " + continuousSignal.getPeriod());
        System.out.println("Wavelength: " + continuousSignal.getWavelength());

        int[] x = {1, 2, 3, 4, 5};
        int n = 2;
        int sum = DiscreteSignal.calculateSum(x, n);
        System.out.println("Sum of signal values at n = " + n + ": " + sum);
    }
}

