package writefilestrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import applications.TrackGame;
import circularOrbit.CircularOrbit;
import physicalObject.Athlete;

public class WriteTrackGamePrintWritter implements WriteTrackGameFile{

  @Override
  public long writeFile(File file, int numOfTrack,
      List<CircularOrbit<String, Athlete>> orbitlist) {
    PrintWriter output = null;
    long begin=System.currentTimeMillis();
    try {
       output=new PrintWriter(file); 
       output.println("CircularOrbitName::=TrackGame");
       output.println("NumOfTracks::="+numOfTrack);
       int orbitSize=orbitlist.size();
       output.println("TotalOribitAmount::="+orbitSize);
       for(int i=1;i<=orbitSize;i++) {
         output.println("OribitNumber::="+i);
         CircularOrbit<String,Athlete> orbit=orbitlist.get(i-1);
         List<HashSet<Athlete>> objects=orbit.getObjOnTracks();
         for(int j=0;j<objects.size();j++) {
           output.println("TrackIndex::="+(j+1));
           HashSet<Athlete> athsOneTrack=objects.get(j);
           for(Athlete a:athsOneTrack) {
             output.println(a.toString());
           }
         }
       }
       
       
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }finally {
      output.close();
    }
    return System.currentTimeMillis()-begin;
  }
}
