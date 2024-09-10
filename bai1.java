interface Signal {
    double getAmplitude();
    double getFrequency();
    double getPeriod();
    double getWavelength();
}

class DiscreteSignal implements Signal {
    private double amplitude;
    private double frequency;

    public DiscreteSignal(double amplitude, double frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    @Override
    public double getAmplitude() {
        return amplitude;
    }

    @Override
    public double getFrequency() {
        return frequency;
    }

    @Override
    public double getPeriod() {
        return 1.0 / frequency;
    }

    @Override
    public double getWavelength() {
        return 0;
    }

    public static class DiscreteSum {
        public static int delta(int n, int k) {
            return (n == k) ? 1 : 0;
        }

        public static int calculateSum(int[] x, int n) {
            int sum = 0;
            for (int k = 0; k < x.length; k++) {
                sum += x[k] * delta(n, k);
            }
            return sum;
        }
    }
}

class ContinuousSignal implements Signal {
    private double amplitude;
    private double frequency;
    private double wavelength;

    public ContinuousSignal(double amplitude, double frequency, double wavelength) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.wavelength = wavelength;
    }

    @Override
    public double getAmplitude() {
        return amplitude;
    }

    @Override
    public double getFrequency() {
        return frequency;
    }

    @Override
    public double getPeriod() {
        return 1.0 / frequency;
    }

    @Override
    public double getWavelength() {
        return wavelength;
    }
}

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
