package giuakioop;
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
    
    
    public double radarDiscreteSignal(int n) {
        if (n >= 0 && n <= 15) {
            return 1 - ((double) n / 15);
        } else {
            return 0;
        }
    }
    
}