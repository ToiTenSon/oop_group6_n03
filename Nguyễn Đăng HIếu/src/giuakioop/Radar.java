package giuakioop;
public class Radar {
    private DiscreteSignal signal;
    public Radar(DiscreteSignal signal) {
        this.signal = signal;
    }
    public void displayRadarSignals(int n) {
            System.out.println("Radar signal at n = " + n + ": " + signal.radarDiscreteSignal(n));
        
    }
}
