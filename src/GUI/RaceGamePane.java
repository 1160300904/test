package GUI;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIs.CircularOrbitAPIs;
import APIs.Difference;
import appExceptions.*;
import applications.*;
import circularOrbit.CircularOrbit;
import errorHandling.*;
import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import physicalObject.*;

/**
 * This is the class of pane of TrackGame. The circular orbit of TrackGame will be drawn on this
 * pane.
 * 
 * @author Luke Skywalker
 *
 */
public class RaceGamePane extends CircularOrbitPane<String, Athlete> {

  // Trackgame instance
  TrackGame trackgame = null;

  // special
  Button arangeBut = new Button("ArrangePlan");
  Button setPlanBut = new Button("SetPlan");
  Button difBut = new Button("Difference");
  Button isLegalBut = new Button("IsLegal");
  Button queryBut = new Button("QueryLog");
  FileChooser filechooser = new FileChooser();
  Stage stage = new Stage();
  // exceptions fields
  TrackGameHandler trackHandler = new TrackGameHandler();

  List<CircularOrbit<String, Athlete>> orbitlist = new ArrayList<>();// oribitlist that contains
                                                                     // orbits.
  /*
   * Abstract function: 1.trackgame is used for generating the orbits of track game.
   * 2.arangeBut,setPlanBut,difBut,isLegalBut are buttons for users to use to operate the GUI.
   * 3.filechooser,stage is used for the user to choose initial file of this system. 4.trackHandler
   * is an exception handler handle the exceptions in this system. 11.orbitlist contains the orbits
   * generated from TrackGame.
   */
  /*
   * Rep Invariant: none.
   */

  /**
   * Creator of TrackGamePane, which will be shown on the main stage.
   */
  RaceGamePane() {
    super();
    // inner jobs
    ApplicationFactory trackfac = new TrackGameFactory();
    this.trackgame = (TrackGame) trackfac.getApplication();
    String athletepstr = "([a-z[A-Z]]+),([0-9]+),([A-Z]{3}),([0-9]+),"
        + "([0-9]{1,2}\\.[0-9]{2}),([0-9]+),([0-9]{1,3}\\.[0-9]{2})";
    Pattern athletep = Pattern.compile(athletepstr);

    // gui jobs
    specialuse.add(arangeBut, 0, 0);
    specialuse.add(setPlanBut, 0, 1);
    specialuse.add(difBut, 1, 0);
    specialuse.add(isLegalBut, 1, 1);
    specialuse.add(this.queryBut, 2, 1);
    queryBut.setOnAction(e -> {
      String inputtext = this.inputfield.getText();
      String[] inputs = inputtext.split(",");
      List<JavaLogElement> retlist = this.logsaver.query(inputs[0], inputs[1]);

      StringBuffer buf = new StringBuffer("The result is:\n");
      for (JavaLogElement ele : retlist) {
        buf.append(ele.toString() + "\n");
      }
      this.outputfield.setText(buf.toString());
      LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",QueryLog," + inputtext + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });
    // initbut
    this.initBut.setOnAction(e -> {

      File file;
      file = filechooser.showOpenDialog(stage);
      while (true) {
        try {
          this.trackgame = (TrackGame) trackfac.getApplication();
          this.trackgame.initFromFile(file);
          break;
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
          this.trackgame = (TrackGame) trackfac.getApplication();
          file = filechooser.showOpenDialog(stage);
        } catch (FileSyntaxException e1) {
          String ret = this.trackHandler.FilesyntaxHandling(e1.getMessage());
          this.outputfield.setText(ret);
          this.trackgame = (TrackGame) trackfac.getApplication();
          file = filechooser.showOpenDialog(stage);
        } catch (RepeatedObjectsException e1) {
          System.out.println(e1.getMessage());
          this.outputfield.setText(e1.getMessage());
          this.trackgame = (TrackGame) trackfac.getApplication();
          file = filechooser.showOpenDialog(stage);
        }
      }
      LogRecord lr = new LogRecord(Level.INFO,
          "Operation" + ",Initialize," + "initialize from file" + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });

    this.addObjBut.setOnAction(e -> {
      String str = this.inputfield.getText();
      Matcher matcher;
      matcher = athletep.matcher(str);
      matcher.find();
      String name = matcher.group(1);
      int num = Integer.valueOf(matcher.group(2));
      String nation = matcher.group(3);
      int age = Integer.valueOf(matcher.group(4));
      double bestscore = Double.valueOf(matcher.group(5));
      int tracknum = Integer.valueOf(matcher.group(6));
      double thetaontra = Double.valueOf(matcher.group(7));
      // System.out.println(name+" "+num+" "+nation+" "+age+" "+bestscore);
      Athlete a = AthleteFactory.getInstance(name, num, nation, age, bestscore);
      orbit.addPhyToTrack(a, thetaontra, tracknum);
      LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",AddObject," + str + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });

    this.remObjBut.setOnAction(e -> {
      String inputtext = this.inputfield.getText();
      Scanner s = new Scanner(inputtext);
      String name = s.next();
      int tracknum = s.nextInt();
      Map<Athlete, Double> athletesmap = this.orbit.getThetas();
      Athlete deleath = null;
      Set<Athlete> athset = athletesmap.keySet();
      for (Athlete a : athset) {
        if (a.getName().equals(name)) {
          deleath = a;
          break;
        }
      }
      orbit.removePhy(deleath, tracknum);
      s.close();
      LogRecord lr =
          new LogRecord(Level.INFO, "Operation" + ",RemoveObject," + inputtext + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });

    setOrbit.setOnAction(e -> {
      String inputtext = this.inputfield.getText();
      int input = Integer.valueOf(inputtext);
      int orbitlistsize = this.orbitlist.size();
      if (input > orbitlistsize) {
        this.outputfield.setText("There is only " + orbitlistsize + " orbits in this plan");
      } else {
        this.orbit = this.orbitlist.get(input - 1);
      }
      LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",SetOrbit," + inputtext + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });

    arangeBut.setOnAction(e -> {
      String inputtext = this.inputfield.getText();
      Scanner s = new Scanner(inputtext);
      String category = s.next();
      int tracknum = s.nextInt();
      GamePlan gp = null;
      if (category.equals("random")) {
        gp = new RandomPlan();
      } else if (category.equals("sorted")) {
        gp = new SortedPlan();
      }
      this.trackgame.arrangeGame(gp, tracknum);
      this.FromPlanToOrbit();
      s.close();
      LogRecord lr =
          new LogRecord(Level.INFO, "Operation" + ",ArrangeGame," + inputtext + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });
    setPlanBut.setOnAction(e -> {
      String inputtext = this.inputfield.getText();
      Scanner s = new Scanner(inputtext);
      int group1 = s.nextInt();
      int track1 = s.nextInt();
      int group2 = s.nextInt();
      int track2 = s.nextInt();
      // System.out.println(group1+" "+track1+" "+group2+" "+track2);
      this.trackgame.setGamePlan(group1, track1, group2, track2);
      this.FromPlanToOrbit();
      s.close();
      LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",SetPlan," + inputtext + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });

    this.difBut.setOnAction(e -> {
      String inputtext = this.inputfield.getText();
      Scanner s = new Scanner(inputtext);
      int index1 = s.nextInt();
      int index2 = s.nextInt();
      CircularOrbit<String, Athlete> c1 = this.orbitlist.get(index1 - 1);
      CircularOrbit<String, Athlete> c2 = this.orbitlist.get(index2 - 1);
      CircularOrbitAPIs<String, Athlete> api = new CircularOrbitAPIs<>();
      Difference diff = api.getRaceGameDifference(c1, c2);
      this.outputfield.setText(diff.toString());
      LogRecord lr =
          new LogRecord(Level.INFO, "Operation" + ",GetDifference," + inputtext + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });

    this.isLegalBut.setOnAction(e -> {
      String inputtext = this.inputfield.getText();
      Scanner s = new Scanner(inputtext);
      int index1 = s.nextInt();
      int index2 = s.nextInt();
      CircularOrbit<String, Athlete> c1 = this.orbitlist.get(index1 - 1);
      CircularOrbit<String, Athlete> c2 = this.orbitlist.get(index2 - 1);
      boolean islegal = this.trackgame.isLegalForTwoOrbits(c1, c2);
      StringBuffer sbuf = new StringBuffer("For these two orbits, they are ");
      if (islegal == true) {
        sbuf.append("legal\n");
      } else {
        sbuf.append("illegal\n");
      }
      sbuf.append("For all the orbits, they are ");
      islegal = this.trackgame.isLegal(this.orbitlist);
      if (islegal == true) {
        sbuf.append("legal\n");
      } else {
        sbuf.append("illegal\n");
      }
      this.outputfield.setText(sbuf.toString());
      s.close();
      LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",IsLegal," + inputtext + ",succeed");
      this.log.log(lr);
      this.logsaver.add(lr);
    });
    /*
     * debug property can add DoubleProperty d1=new
     * SimpleDoubleProperty();d1.bind(circlepane.heightProperty()); DoubleProperty d2=new
     * SimpleDoubleProperty();d2.bind(circlepane.heightProperty().multiply(2)); DoubleProperty
     * d3=new SimpleDoubleProperty();d3.bind(d1.add(d2));
     * 
     * this.arangeBut.setOnAction(e->{ System.out.println("d1 is "+d1.doubleValue()+"d2 is "
     * +d2.doubleValue()+"d3 is "+d3.doubleValue()); });
     */

  }

  /**
   * A function that transit the plan information read from file to the orbit system.
   */
  private void FromPlanToOrbit() {
    int size = this.orbitlist.size();
    for (int i = 0; i < size; i++) {
      this.orbitlist.remove(0);
    }
    List<List<Athlete>> gameplan = trackgame.getGamePlan();
    if ((gameplan.isEmpty() != false) && (gameplan.get(0).isEmpty() != false)) {

    }
    int i = 0;
    CircularOrbit<String, Athlete> cor = null;
    for (List<Athlete> athlist : gameplan) {
      i = 1;
      cor = CircularOrbit.empty();

      for (Athlete a : athlist) {
        cor.addInsideTrack(i);
        if (a != null) {
          cor.addPhyToTrack(a, -90, i);
        }
        i++;
      }
      this.orbitlist.add(cor);
    }
  }

  @Override
  void Draw() {
    List<Double> radiuses = this.orbit.getRadius();
    List<HashSet<Athlete>> objs = this.orbit.getObjOnTracks();
    Map<Athlete, Double> thetas = this.orbit.getThetas();
    StringBuffer s = new StringBuffer();
    int radiussize = radiuses.size();
    double maxradius = 0;
    if (radiussize > 0) {
      maxradius = radiuses.get(radiussize - 1);
    }
    for (int i = 0; i < radiussize; i++) {
      double radiusi = radiuses.get(i);
      s.append("Track " + (i + 1) + " : radius is " + radiusi + "\n");
      DrawOneOrbit(radiusi, maxradius, objs.get(i), thetas, s);
    }
    this.outputmes = s.toString();
    this.outputfield.setText(outputmes);

    /*
     * radiuses=new ArrayList<>();radiuses.add(Double.valueOf(10));radiuses.add(Double.valueOf(
     * 100)); Athlete a1=AthleteFactory.getInstance("1", 1, "AAA", 1, 10.10); Athlete
     * a2=AthleteFactory.getInstance("2", 2, "BBB", 2, 20.20); Athlete
     * a3=AthleteFactory.getInstance("3", 2, "BBB", 2, 20.20); HashSet<Athlete> objjs=new
     * HashSet<>();objjs.add(a1);objjs.add(a2);objjs.add(a3); thetas=new HashMap<>(); thetas.put(a1,
     * Double.valueOf(45));thetas.put(a2, Double.valueOf(90)); thetas.put(a3, Double.valueOf(30));
     * DrawOneOrbit(10,20,objjs,thetas);
     */

    /*
     * Circle c1=new Circle(); c1.setCenterX(794.0);c1.setCenterY(341.0);c1.setRadius(10);
     * c1.setFill(Color.RED);circlepane.getChildren().add(c1);
     */

  }

  /**
   * Draw one track of the circular orbit in the system.
   * 
   * @param radius the radius of the track.
   * @param maxrad the max radius among the track of the orbit this track belongs to.
   * @param aonetra objects on one track.
   * @param thetas the degree of each object on the track.
   * @param s a StringBuffer to put the message of the track in.
   */
  void DrawOneOrbit(double radius, double maxrad, HashSet<Athlete> aonetra,
      Map<Athlete, Double> thetas, StringBuffer s) {
    // calculate radius
    DoubleProperty radongui = new SimpleDoubleProperty();
    DoubleProperty xongui = new SimpleDoubleProperty();
    DoubleProperty yongui = new SimpleDoubleProperty();

    radongui.bind(circlepane.heightProperty().multiply((0.9 / 2) * (radius / maxrad)));
    xongui.bind(circlepane.widthProperty().divide(2));
    yongui.bind(circlepane.heightProperty().divide(2));
    // draw circle
    Circle c = new Circle();
    c.centerXProperty().bind(xongui);
    c.centerYProperty().bind(yongui);
    c.radiusProperty().bind(radongui);
    c.setStroke(Color.GREEN);
    c.setFill(null);
    circlepane.getChildren().add(c);
    // draw athletes
    for (Athlete a : aonetra) {
      double atheta = thetas.get(a);
      c = new Circle();
      // draw circle
      DoubleProperty cxongui = new SimpleDoubleProperty();
      DoubleProperty cyongui = new SimpleDoubleProperty();
      cxongui.bind(xongui.add(radongui.multiply(Math.cos(Math.toRadians(atheta)))));
      cyongui.bind(yongui.subtract(radongui.multiply(Math.sin(Math.toRadians(atheta)))));
      c.centerXProperty().bind(cxongui);
      c.centerYProperty().bind(cyongui);
      /*
       * c.centerXProperty().bind(xongui.add(radongui.multiply(Math.cos(atheta))));
       * c.centerYProperty().bind(yongui.add(radongui.multiply(Math.sin(atheta))));
       */
      c.setStroke(Color.BLUE);
      c.setFill(Color.WHITE);
      c.setRadius(5);

      s.append("At theta " + atheta + " : Athlete <" + a.getName() + "," + a.getNumber() + ","
          + a.getNation() + "," + a.getAge() + "," + a.getBestScore() + ">\n");

      // add label for each app
      Label l = new Label(a.getName());
      l.setContentDisplay(ContentDisplay.RIGHT);
      l.layoutXProperty().bind(cxongui);
      l.layoutYProperty().bind(cyongui);
      l.setOnMouseEntered((MouseEvent e) -> {
        l.setScaleX(2);
        l.setScaleY(2);
      });
      l.setOnMouseExited((MouseEvent e) -> {
        l.setScaleX(1);
        l.setScaleY(1);
      });

      circlepane.getChildren().addAll(c, l);
      /*
       * debug print System.out.println("radius is "+radongui.doubleValue()+"\n");
       * System.out.println("X is "+xongui.doubleValue()+"\n");
       * System.out.println("Y is "+yongui.doubleValue()+"\n");
       * System.out.println("theta is "+atheta+"\n");
       * System.out.println("cX is "+cxongui.doubleValue()+"\n");
       * System.out.println("cY is "+cyongui.doubleValue()+"\n");
       */
    }

    /*
     * for(Label l:llist) { circlepane.getChildren().add(l); }
     */
    /*
     * Circle c1;Label l; for(int i=1;i<10;i++) { c1=new Circle();
     * c1.setCenterX(10*i);c1.setCenterY(10*i); c1.setRadius(3);c1.setFill(Color.YELLOW); l=new
     * Label(String.valueOf(i),c); l.setContentDisplay(ContentDisplay.RIGHT);
     * circlepane.getChildren().addAll(c1,l); }
     */
  }

}
