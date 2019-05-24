package writefilestrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import centralObject.User;
import circularOrbit.CircularOrbit;
import physicalObject.Athlete;
import physicalObject.PersonalApp;

public class WriteAppPrintWritter implements WriteAppFile {

  @Override
  public long writeFile(File file, int numOfTrack,
      List<CircularOrbit<User, PersonalApp>> orbitlist) {
    PrintWriter output = null;
    long begin=System.currentTimeMillis();
    try {
       output=new PrintWriter(file); 
       output.println("CircularOrbitName::=PersonalAppEcosystem");
       output.println("NumOfTracks::="+numOfTrack);
       int orbitSize=orbitlist.size();
       output.println("TotalOribitAmount::="+orbitSize);
       for(int i=1;i<=orbitSize;i++) {
         output.println("OribitNumber::="+i);
         CircularOrbit<User, PersonalApp> orbit=orbitlist.get(i-1);
         List<HashSet<PersonalApp>> objects=orbit.getObjOnTracks();
         for(int j=0;j<objects.size();j++) {
           output.println("TrackIndex::="+(j+1));
           HashSet<PersonalApp> athsOneTrack=objects.get(j);
           for(PersonalApp a:athsOneTrack) {
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
