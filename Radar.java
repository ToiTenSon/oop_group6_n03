// Nguyen Truong Son
// mssv: 23010313
// github: oop_group6_n03
public class Radar {
    public double radarDiscreteSignal(int n) {
        if (n >= 0 && n <= 15) {
            return 1 - ((double) n / 15);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Radar radar = new Radar();
        int n = 4;
        System.out.println("Output for n = " + n + ": " + radar.radarDiscreteSignal(n));
    }
}