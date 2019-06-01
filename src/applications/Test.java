package applications;

import centralObject.Nucleus;
import centralObject.NucleusFactory;
import circularOrbit.CircularOrbit;
import javafx.application.Application;
import physicalObject.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.time.LocalDateTime;

public class Test {

 public static void main(String[] args)  {
  
   /*
    * CircularOrbit<Nucleus,Electron> c=CircularOrbit.empty();
   c.addInsideTrack(1);
   Electron e1=ElectronFactory.getInstance(1);
   c.addPhyToTrack(e1, 90, 1);
   c.addPhyToTrack(ElectronFactory.getInstance(1), 180, 1);
   List<HashSet<Electron>> es=c.getObjOnTracks();
   
   for(Electron e:es.get(0)) {
     System.out.print(e.toString());
   }
    */
   Integer i1=1;Integer i2=Integer.valueOf(1);
   System.out.print(i1==i2);
 }

}
