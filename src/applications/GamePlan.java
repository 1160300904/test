package applications;

import java.util.*;

import physicalObject.*;

/**
 * This is an interface that make plans of the track game based on the input
 * athlete list.
 * 
 * @author Luke Skywalker
 *
 */
public interface GamePlan {

    /**
     * make a game plan based on a list of athletes
     * 
     * @param athletes the athletes in the game.
     * @param tracknum the total numbers of tracks in each match. Must in the range
     *                 of [4,10].
     * @return a game plan in a double nested list, while each inner layer
     *         represents one match.
     * @throws IllegalArgumentException If tracknum is out of range, throw
     *                                  IllegalArgumentException
     */
    public List<List<Athlete>> plan(List<Athlete> athletes, int tracknum);
}
