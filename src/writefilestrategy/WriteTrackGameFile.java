package writefilestrategy;

import java.io.File;
import java.util.List;

import applications.TrackGame;
import circularOrbit.CircularOrbit;
import physicalObject.Athlete;

public interface WriteTrackGameFile {

  /**
   * write the information to a file.
   * @param file    the file you want to write to.
   * @return    true if this process success.
   */
  public long writeFile(File file,int numOfTrack,List<CircularOrbit<String, Athlete>> orbitlist);
}
