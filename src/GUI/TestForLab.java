package GUI;

import java.io.File;
import java.io.FileNotFoundException;

import appExceptions.FileSyntaxException;
import appExceptions.RepeatedObjectsException;
import applications.ApplicationFactory;
import applications.GamePlan;
import applications.SortedPlan;
import applications.TrackGame;
import applications.TrackGameFactory;
import circularOrbit.CircularOrbit;
import physicalObject.Athlete;
import physicalObject.AthleteFactory;

public class TestForLab {

  public static void main(String[] args)  {
    ApplicationFactory trackfac = new TrackGameFactory();
    TrackGame trackgame = (TrackGame) trackfac.getApplication();
    File file=new File("E:\\Spring2019_HITCS_SC_Lab5\\TrackGame.txt");
    try {
      Thread.sleep(13000);
      System.out.println("sleep 1");
      Thread.sleep(3000);
      System.out.println("sleep 2");
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
     
    Athlete[] alist=new Athlete[100];
    for(int i=0;i<100;i++) {
      alist[i]=AthleteFactory.getInstance(String.valueOf(i), i, "CHI", i, i+i*0.1);
    }
    long begin=System.currentTimeMillis();
    GamePlan gp=new SortedPlan();
    for(int i=0;i<5;i++) {
      try {
        trackgame = (TrackGame) trackfac.getApplication();
        trackgame.initFromFile(file);
        
        trackgame.arrangeGame(gp, 10);
        
        System.out.println(i);
      } catch (FileNotFoundException | FileSyntaxException | RepeatedObjectsException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    CircularOrbit<String,Athlete> orbit=CircularOrbit.empty();
    
    for(int j=1;j<=100;j++) {
      orbit.addInsideTrack(j);
    }
    for(int j=1;j<=100;j++) {
      orbit.addPhyToTrack(alist[j-1], 0, j);
    }
    for(int j=2;j<=100;j++) {
      orbit.transit(alist[j-1], 90, j, j-1);
    }
    for(int j=1;j<=50;j++) {
      orbit.removePhy(alist[j], j);
    }
    for(int j=1;j<=100;j++) {
      orbit.deleteTrack(j);
    }
    long duration=System.currentTimeMillis()-begin;
    System.out.println(duration);
  }
}
