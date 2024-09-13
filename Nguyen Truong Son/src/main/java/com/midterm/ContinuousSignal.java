package com.midterm;
public class ContinuousSignal implements Signal{
    private double amplitude;
    private double frequency;
    private double period;
    private double wavelength;
    
    public ContinuousSignal(double amplitude, double frequency, double period, double wavelength){
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.period = period;
        this.wavelength = wavelength;
    }
    public double getAmplitude() {
        return amplitude;
    }
    public double getFrequency() {
        return frequency;
    }
    public double getPeriod() {
        return period;
    }
    public double getWavelength() {
        return wavelength;
    }
}

