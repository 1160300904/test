package GUI;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import APIs.CircularOrbitAPIs;
import circularOrbit.CircularOrbit;

/**
 * This is the circular orbit pane of the circular orbit GUI.
 * 
 * @author Luke Skywalker
 *
 * @param <L> The type of central object of this circular orbit.
 * @param <E> The type of physical object of this circular orbit.
 */
public class CircularOrbitPane<L, E> extends BorderPane {
    // init pane
    // ScrollPane scrollcirclepane=new ScrollPane();
    Pane circlepane = new Pane();
    ScrollPane outputpane = new ScrollPane();
    GridPane regularuse = new GridPane();
    GridPane specialuse = new GridPane();
    FlowPane inputpane = new FlowPane();
    VBox rightpane = new VBox();

    // init element

    TextField inputfield = new TextField();
    TextArea outputfield = new TextArea();
    // regular
    Button drawBut = new Button("Draw");
    Button initBut = new Button("Init");
    Button addTrackBut = new Button("AddTrack");
    Button addObjBut = new Button("AddObject");
    Button remTrackBut = new Button("RemoveTrack");
    Button remObjBut = new Button("RemoveObject");
    Button entroBut = new Button("Entropy");
    Button setOrbit = new Button("SetOrbit");
    String outputmes = null;

    // orbit
    CircularOrbit<L, E> orbit = CircularOrbit.empty();// the orbit gui draws
    // log
    protected LogSaver logsaver = LogSaver.getInstance();
    protected Logger log = logsaver.getLogger();

    /*
     * Abstract function:
     * 1.circlepane,outputpane,regularuse,specialuse,inputpane,rightpane are panes
     * of the orbit pane on GUI. 2.inputfield,outputfield are text fields which can
     * deal with informations.
     * 3.drawBut,initBut,addTrackBut,addObjBut,remTrackBut,remObjBut,entroBut,
     * setOrbit,outputmes are buttons for users to make use of the system. 4.orbit
     * is the circular orbit the pane is going to draw.
     */
    /*
     * Rep Invariant: none.
     */
    /**
     * Creator of this circular orbit pane.
     */
    CircularOrbitPane() {
        this.inputfield.setPrefWidth(400);
        regularuse.add(drawBut, 0, 0);
        regularuse.add(initBut, 0, 1);
        regularuse.add(addTrackBut, 1, 0);
        regularuse.add(addObjBut, 1, 1);
        regularuse.add(remTrackBut, 2, 0);
        regularuse.add(remObjBut, 2, 1);
        regularuse.add(entroBut, 3, 1);
        regularuse.add(setOrbit, 3, 0);
        regularuse.setPadding(new Insets(110, 110, 110, 110));
        // this.specialuse.setPadding(new Insets(110,110,110,110));

        outputpane.setContent(outputfield);
        inputpane.getChildren().add(inputfield);

        rightpane.getChildren().addAll(outputpane, regularuse, specialuse, inputpane);
        // circlepane.setStyle("-fx-background-color: blue;");
        // circlepane.setPadding(new Insets(110,110,110,110));
        // scrollcirclepane.setContent(circlepane);
        this.setCenter(circlepane);
        this.setRight(rightpane);

        // add listener
        drawBut.setOnAction(e -> {
            this.circlepane.getChildren().clear();
            this.Draw();
            LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",Draw," + "draw orbit" + ",succeed");
            this.log.log(lr);
            this.logsaver.add(lr);
        });
        // init is for subclasses
        // addObjBut and remObjBut for subclasses entroBut not implemented yet
        addTrackBut.setOnAction(e -> {
            String inputtext = this.inputfield.getText();
            Double input = Double.valueOf(inputtext);
            orbit.addInsideTrack(input);
            LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",AddTrack," + inputtext + ",succeed");
            this.log.log(lr);
            this.logsaver.add(lr);
        });

        remTrackBut.setOnAction(e -> {
            String inputtext = this.inputfield.getText();
            int input = Integer.valueOf(inputtext);
            orbit.deleteTrack(input);
            LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",DeleteTrack," + inputtext + ",succeed");
            this.log.log(lr);
            this.logsaver.add(lr);
        });

        entroBut.setOnAction(e -> {
            CircularOrbitAPIs<L, E> api = new CircularOrbitAPIs<>();
            double entro = api.getObjectDistributionEntropy(this.orbit);
            this.outputfield.setText("The Entropy of this circular orbit is: " + entro);
            LogRecord lr = new LogRecord(Level.INFO, "Operation" + ",Entropy," + "Entropy" + ",succeed");
            this.log.log(lr);
            this.logsaver.add(lr);
        });

    }

    /**
     * the draw function to draw the orbit system on pane, which will be override by
     * its subclasses.
     */
    void Draw() {
        /*
         * Circle c1=new Circle();
         * c1.radiusProperty().bind(circlepane.heightProperty().divide(2));
         * c1.setFill(Color.GREEN);
         * c1.centerXProperty().bind(circlepane.widthProperty().divide(2));
         * c1.centerYProperty().bind(circlepane.heightProperty().divide(2));
         * circlepane.getChildren().add(c1);
         */
        /*
         * List<Double> radiuslist=this.orbit.getRadius(); List<HashSet<E>>
         * objlist=this.orbit.getObjOnTracks(); DrawOrbits(radiuslist); DrawObjs();
         */
    }

}
