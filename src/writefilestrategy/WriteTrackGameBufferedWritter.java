package writefilestrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;

import applications.TrackGame;
import circularOrbit.CircularOrbit;
import physicalObject.Athlete;

public class WriteTrackGameBufferedWritter implements WriteTrackGameFile{

  @Override
  public long writeFile(File file, int numOfTrack,
      List<CircularOrbit<String, Athlete>> orbitlist) {
    long begin=System.currentTimeMillis();
    try(BufferedWriter output=new BufferedWriter(new OutputStreamWriter (new FileOutputStream (file)))) {
      
       output.write("CircularOrbitName::=TrackGame");
       output.newLine();
       output.write("NumOfTracks::="+numOfTrack);
       output.newLine();
       int orbitSize=orbitlist.size();
       output.write("TotalOribitAmount::="+orbitSize);output.newLine();
       for(int i=1;i<=orbitSize;i++) {
         output.write("OribitNumber::="+i);output.newLine();
         CircularOrbit<String,Athlete> orbit=orbitlist.get(i-1);
         List<HashSet<Athlete>> objects=orbit.getObjOnTracks();
         for(int j=0;j<objects.size();j++) {
           output.write("TrackIndex::="+(j+1));output.newLine();
           HashSet<Athlete> athsOneTrack=objects.get(j);
           for(Athlete a:athsOneTrack) {
             output.write(a.toString());output.newLine();
           }
         }
       }
       output.flush();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   
    return System.currentTimeMillis()-begin;
  }

}
