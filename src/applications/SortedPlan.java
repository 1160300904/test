package applications;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import physicalObject.Athlete;

/**
 * This is an implementation of interface GamePlan. This SortedPlan return a plan based on the best
 * score of the athletes. The better the score, the later the athlete will show up, and he will more
 * likely to get a middle track of all of the tracks.
 * 
 * @author Luke Skywalker
 *
 */
public class SortedPlan implements GamePlan {

  @Override
  public List<List<Athlete>> plan(List<Athlete> athletes, int tracknum) {
    Collections.sort(athletes, new Comparator<Athlete>() {

      @Override
      public int compare(Athlete arg0, Athlete arg1) {
        double bestscore1 = arg0.getBestScore();
        double bestscore2 = arg1.getBestScore();
        if (bestscore1 > bestscore2) {
          return -1;
        } else if (bestscore1 == bestscore2) {
          return 0;
        }
        return 1;
      }

    });

    for (Athlete a : athletes) {
      // System.out.println(a.getBestScore());
    }
    List<List<Athlete>> retlist = new ArrayList<>();
    List<Athlete> grouplist;

    int leftathnum = athletes.size();
    int i = 0;
    while (leftathnum >= tracknum) {
      grouplist = new ArrayList<>();
      if (tracknum % 2 != 0) {
        for (int i1 = 0; i1 <= tracknum - 1; i1 += 2) {
          grouplist.add(athletes.get(i + i1));
        }
        for (int i1 = tracknum - 2; i1 >= 1; i1 -= 2) {
          grouplist.add(athletes.get(i + i1));
        }
      } else {
        for (int i1 = 0; i1 <= tracknum - 2; i1 += 2) {
          grouplist.add(athletes.get(i + i1));
        }
        for (int i1 = tracknum - 1; i1 >= 1; i1 -= 2) {
          grouplist.add(athletes.get(i + i1));
        }
      }
      retlist.add(grouplist);
      i += tracknum;
      leftathnum -= tracknum;
    }
    if (leftathnum > 0) {
      grouplist = new ArrayList<>();
      for (int i1 = 0; i1 < tracknum; i1++) {
        grouplist.add(null);
      }
      int sign = -1;
      int delta = 0;
      int j = 0;
      if (tracknum % 2 == 0) {
        j = tracknum / 2 - 1;
      } else {
        j = tracknum / 2;
      }
      for (int i1 = athletes.size() - 1; i1 >= athletes.size() - leftathnum; i1--) {
        j = j + sign * delta;
        grouplist.set(j, athletes.get(i1));
        sign *= (-1);
        delta++;
      }
      retlist.add(grouplist);
    }

    return retlist;
  }

}
