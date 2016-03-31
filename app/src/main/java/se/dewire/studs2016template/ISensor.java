package se.dewire.studs2016template;

import java.util.Observable;

/**
 * Created by kajo on 2016-03-31.
 */
public interface ISensor{
    public interface Observer{
        public enum Event{
            UP, DOWN, LEFT, RIGHT, SELECT, ERASE
        }
        public void event(Event e);
    }
    public void register(Observer o);
}
