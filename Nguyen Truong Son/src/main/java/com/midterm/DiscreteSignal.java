package com.midterm;
public class DiscreteSignal implements Signal {
    private double amplitude;
    private double frequency;
    private double period;
    private double wavelength;
        public DiscreteSignal(double amplitude, double frequency, double period, double wavelength) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.period = period;
        this.wavelength= wavelength;
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
        return period;
    }
    @Override
    public double getWavelength() {
        return wavelength;
    }
    public double calculateX(int n) {
        if (n == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public double discreteSignalSum(int n) {
        double result = 0;
        for (int k = Integer.MIN_VALUE; k <= Integer.MAX_VALUE; k++) {
            result += calculateX(k) * delta(n - k);
        }
        return result;
    }

    private int delta(int n) {
        return (n == 0) ? 1 : 0;
    }
}
