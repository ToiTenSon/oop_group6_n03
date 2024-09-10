// Nguyen Truong Son
// mssv: 23010313
// github: oop_group6_n03

//tạo interface
public interface Signal {
    double getAmplitude();
    double getFrequency(); 
    double getPeriod();     
    double getWavelength();
}

//tạo class DiscreteSignal
class DiscreteSignal implements Signal {
    private double amplitude;
    private double frequency;
    private double period;
    private double wavelength;

    public DiscreteSignal(double amplitude, double frequency, double period, double wavelength) {
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
    public double discreteSignalSum(int n) {
        return Discrete_Signal.discreteSignalSum(n);
    }
}    

class ContinuousSignal implements Signal{
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

