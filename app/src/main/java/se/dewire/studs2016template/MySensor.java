package se.dewire.studs2016template;

public class MySensor implements ISensor {

    @Override
    public void register(Observer o) {
        o.event(Observer.Event.UP);
    }
}
