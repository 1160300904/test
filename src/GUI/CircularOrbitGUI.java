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

/**
 * This is the GUI of circular orbit system.
 * 
 * @author Luke Skywalker
 *
 */
public class CircularOrbitGUI extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    /*
     * SplitPane pane=new SplitPane(); Pane leftp=new Pane();GridPane rightp=new GridPane(); Circle
     * c=new Circle(); c.setStroke(Color.BLUE);c.setFill(null);
     * c.centerXProperty().bind(leftp.widthProperty().divide(2));
     * c.centerYProperty().bind(leftp.heightProperty().divide(2));
     * c.radiusProperty().bind(leftp.widthProperty().divide(4));
     * 
     * Button b2=new Button("2");
     * 
     * TextField tf=new TextField(); b2.setOnAction(e->{ System.out.println(tf.getText()); });
     * pane.getItems().addAll(leftp,rightp); leftp.getChildren().add(c); rightp.add(tf, 0,
     * 0);rightp.add(b2, 3,0); Scene scene=new Scene(pane,200,200); stage.setScene(scene);
     * stage.show();
     */
    StackPane bp = new StackPane();
    Button b1 = new Button("RaceGame");
    Button b2 = new Button("AtomStructure");
    Button b3 = new Button("PersonalAppEcosystem");

    b1.setOnAction(e -> {
      bp.getChildren().clear();
      bp.getChildren().add(new RaceGamePane());
    });

    b2.setOnAction(e -> {
      bp.getChildren().clear();
      bp.getChildren().add(new AtomStructurePane());
    });
    b3.setOnAction(e -> {
      bp.getChildren().clear();
      bp.getChildren().add(new PersonalAppPane());
    });
    Text t = new Text("Please choose which app you want to use:");
    StackPane sp1 = new StackPane();
    sp1.getChildren().add(t);
    VBox sp2 = new VBox();
    sp2.getChildren().addAll(b1, b2, b3);
    sp2.setPadding(new Insets(110, 110, 110, 110));

    VBox vb = new VBox();
    vb.getChildren().addAll(sp1, sp2);
    bp.getChildren().add(vb);

    Scene scene = new Scene(bp, 1000, 600);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
