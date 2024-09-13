package giuakioop;
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