package APIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConcreteListDifference implements Difference {
  private int trackNumDiff;
  private List<Integer> objNumDiff = new ArrayList<>();
  private List<String> objDetailDiff = new ArrayList<>();

  /*
   * Abstract function: 1.TrackNumDiff is the difference on track numbers between two circular
   * orbits. if the first one has a tracks, and the second one has b tracks, then the TrackNumDiff
   * is a-b. 2.ObjNumDiff is a list consists of object difference in number. On a particular track,
   * if the first one has a objects, and the second one has b tracks, then the ObjNumDiff is a-b.
   * 3.ObjDetailDiff is a list consists of object difference in details. On a particular track, if
   * the first one has objects {a,b,c}, and the second one has objects {b,c,d,e}, then the
   * ObjNumDiff is {a}-{d,e}.
   */
  /*
   * Rep Invariant: the size of ObjNumDiff and ObjDetailDiff should be the same
   */
  void checkRep() {
    for (Integer i : this.objNumDiff) {
      assert i != null;
    }
    for (String s : this.objDetailDiff) {
      assert s != null;
    }
    assert this.objDetailDiff.size() == this.objNumDiff.size();
  }

  @Override
  public void setTrackNumDiff(int tracknumdif) {
    this.trackNumDiff = tracknumdif;
    checkRep();
  }

  @Override
  public boolean setObjNumDiff(int tracknum, int objnumdiff) {
    assert tracknum > 0;
    int numdifsize = this.objNumDiff.size();
    if (tracknum > numdifsize) {
      return false;
    }
    this.objNumDiff.set(tracknum - 1, objnumdiff);
    return true;
  }

  @Override
  public boolean setObjDetailDiff(int tracknum, String objdetaildiff) {
    assert tracknum > 0;
    if (objdetaildiff.equals("")) {
      return false;
    }
    int numdifsize = this.objDetailDiff.size();
    if (tracknum > numdifsize) {
      return false;
    }
    this.objDetailDiff.set(tracknum - 1, objdetaildiff);
    return true;
  }

  @Override
  public void appendObjNumDiff(int objnumdiff) {
    this.objNumDiff.add(objnumdiff);
  }

  @Override
  public void appendObjDetailDiff(String objdetaildiff) {
    this.objDetailDiff.add(objdetaildiff);
  }

  @Override
  public int getTrackNumDiff() {
    return this.trackNumDiff;
  }

  @Override
  public int getObjNumDiff(int tracknum) {
    assert tracknum > 0;
    int numdifsize = this.objNumDiff.size();
    if (tracknum > numdifsize) {
      return Integer.MAX_VALUE;
    }
    return this.objNumDiff.get(tracknum - 1);
  }

  @Override
  public String getObjDetailDiff(int tracknum) {
    assert tracknum > 0;
    int numdifsize = this.objDetailDiff.size();
    if (tracknum > numdifsize) {
      return "Index out of bound";
    }
    return this.objDetailDiff.get(tracknum - 1);
  }

  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append("The difference of track numbers is: " + this.trackNumDiff + " \n");
    int tracknum = this.objNumDiff.size();
    for (int i = 1; i <= tracknum; i++) {
      s.append("The difference of object numbers on track " + i + " is : "
          + this.objNumDiff.get(i - 1) + "\n");
    }

    if (this.objDetailDiff.size() == 0) {
      s.append("There is no object detail differences between two orbits.\n");
    } else {
      for (int i = 1; i <= tracknum; i++) {
        s.append("The difference of object detail on track " + i + " is : "
            + this.objDetailDiff.get(i - 1) + "\n");
      }
    }

    return s.toString();

  }

}
