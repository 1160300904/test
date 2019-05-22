package applications;

import circularOrbit.CircularOrbit;

/**
 * This is a factory class of a track game class.
 * 
 * @author Luke Skywalker
 *
 */
public class TrackGameFactory implements ApplicationFactory {

    @Override
    public CircularOrbit getApplication() {
        // TODO Auto-generated method stub
        return new TrackGame();
    }

}
