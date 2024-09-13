package com.midterm;
public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng DiscreteSignal
        DiscreteSignal discreteSignal = new DiscreteSignal(5.0, 10.0, 2.0, 30.0);
        System.out.println("Discrete Signal Properties:");
        System.out.println("Amplitude: " + discreteSignal.getAmplitude());
        System.out.println("Frequency: " + discreteSignal.getFrequency());
        System.out.println("Period: " + discreteSignal.getPeriod());
        System.out.println("Wavelength: " + discreteSignal.getWavelength());

        // Sử dụng hàm calculateX và discreteSignalSum
        int n = 4;
        System.out.println("\nDiscrete signal calculateX(" + n + "): " + discreteSignal.calculateX(n));
        System.out.println("Discrete signal sum for n = " + n + ": " + discreteSignal.discreteSignalSum(n));

        // Tạo đối tượng ContinuousSignal
        ContinuousSignal continuousSignal = new ContinuousSignal(3.0, 15.0, 1.5, 20.0);
        System.out.println("\nContinuous Signal Properties:");
        System.out.println("Amplitude: " + continuousSignal.getAmplitude());
        System.out.println("Frequency: " + continuousSignal.getFrequency());
        System.out.println("Period: " + continuousSignal.getPeriod());
        System.out.println("Wavelength: " + continuousSignal.getWavelength());

        // Sử dụng đối tượng Radar để phân tích tín hiệu rời rạc
        Radar radar = new Radar();
        System.out.println("\nRadar signal analysis for n = " + n + ": " + radar.radarDiscreteSignal(n));
    }
}
