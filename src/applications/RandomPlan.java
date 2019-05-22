package applications;

import java.util.*;

import physicalObject.*;

/**
 * This is an implementation of interface GamePlan. This RandomPlan return a
 * plan which plan the game randomly.
 *
 */
public class RandomPlan implements GamePlan {

    @Override
    public List<List<Athlete>> plan(List<Athlete> athletes, int tracknum) {
        assert tracknum > 0;
        if (tracknum > 10 || tracknum < 4) {
            throw new IllegalArgumentException();
        }
        List<List<Athlete>> retlist = new ArrayList<>();
        List<Athlete> grouplist = new ArrayList<>();
        for (int i = 0; i < tracknum && i < athletes.size(); i++) {
            grouplist.add(athletes.get(i));
        }
        for (int i = tracknum; i < athletes.size(); i++) {
            if (i % tracknum == 0) {
                retlist.add(grouplist);
                grouplist = new ArrayList<>();
            }
            grouplist.add(athletes.get(i));
        }
        retlist.add(grouplist);
        return retlist;
    }

}
