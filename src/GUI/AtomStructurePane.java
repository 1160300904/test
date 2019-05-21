package GUI;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import appExceptions.FileInfoConflictException;
import appExceptions.FileSyntaxException;
import applications.*;
import centralObject.*;
import errorHandling.AtomStructureHandler;
import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import physicalObject.*;
/**
 * This is the class of pane of atom structure. The circular orbit of atom structure
 * will be drawn on this pane.
 * @author Luke Skywalker
 *
 */
public class AtomStructurePane extends CircularOrbitPane<Nucleus,Electron> {

	
	//Trackgame instance
	AtomStructure atomstruc=null;
	
	//special
	Button transition=new Button("Transition");
	Button queryBut=new Button("QueryLog");
	FileChooser filechooser=new FileChooser();
	Stage stage=new Stage();
	//exception field
	AtomStructureHandler atomhandler=new AtomStructureHandler();
	/*Abstract function:
	 * 	1.atomstruc is the instance of AtomStructure, which will provide informations for the pane to
	 * 		draw.
	 * 	2.transition is a button to transit the electrons on tracks
	 * 	3.filechooser and stage is used for user to choose the initial file.
	 * 	4.atomhandler is the exception handler of atom structure.
	 */
	/*Rep Invariant:
	 * 	none.
	 */
	/**
	 * Creator of AtomStructurePane, which will be shown on the main stage.
	 */
	AtomStructurePane() {
		super();
		//inner jobs
		ApplicationFactory atomfac=new AtomStructureFactory();
		this.atomstruc=(AtomStructure)atomfac.getApplication();
		
		//gui jobs
		specialuse.add(transition, 0, 0);
		specialuse.add(this.queryBut, 0, 1);
		queryBut.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			String []inputs=inputtext.split(",");
			List<JavaLogElement> retlist=this.logsaver.query(inputs[0], inputs[1]);
			
			StringBuffer buf=new StringBuffer("The result is:\n");
			for(JavaLogElement ele:retlist) {
				buf.append(ele.toString()+"\n");
			}
			this.outputfield.setText(buf.toString());
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",QueryLog,"+
				inputtext+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		//initbut
		this.initBut.setOnAction(e->{
			/*
			 * File file=filechooser.showOpenDialog(stage);
			try {
				this.atomstruc.initWithFile(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			 */
			File file;
			file=filechooser.showOpenDialog(stage);
			while(true) {
				try {
					this.atomstruc=(AtomStructure)atomfac.getApplication();
					this.atomstruc.initWithFile(file);
					break;
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					this.atomstruc=(AtomStructure)atomfac.getApplication();
					file=filechooser.showOpenDialog(stage);
				} catch (FileSyntaxException e1) {
					String ret=this.atomhandler.FilesyntaxHandling(e1.getMessage());
					this.outputfield.setText(ret);
					this.atomstruc=(AtomStructure)atomfac.getApplication();
					file=filechooser.showOpenDialog(stage);
				} catch (FileInfoConflictException e1) {
					System.out.println(e1.getMessage());
					this.outputfield.setText(e1.getMessage());
					this.atomstruc=(AtomStructure)atomfac.getApplication();
					file=filechooser.showOpenDialog(stage);
				}
			}
			FromMapToOrbit();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",Initialize,"+
					"initialize from file"+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		this.addObjBut.setOnAction(e->{
			String str=this.inputfield.getText();
			Scanner s=new Scanner(str);
			double theta=s.nextDouble();int tracknum=s.nextInt();
			Electron ele=ElectronFactory.getInstance(tracknum);
			this.orbit.addPhyToTrack(ele, theta, tracknum);
			s.close();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",AddObject,"+
					str+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		this.remObjBut.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			int tracknum=Integer.valueOf(inputtext);
			
			Map<Electron, Double> electronmap=this.orbit.getThetas();Electron deleele=null;
			Set<Electron> eleset=electronmap.keySet();
			for(Electron a:eleset) {
				if(a.getTrackItOn()==tracknum) {
					deleele=a;	break;
				}
			}
			orbit.removePhy(deleele, tracknum);
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",RemoveObject,"+
					inputtext+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		setOrbit.setOnAction(e->{
			this.outputfield.setText("This operation is useless in this application.");
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",SetOrbit,"+
					"set orbit"+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		this.transition.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			Scanner s=new Scanner(inputtext);
			double theta=s.nextDouble();int restracknum=s.nextInt();int tartracknum=s.nextInt();
			
			Map<Electron, Double> electronmap=this.orbit.getThetas();Electron deleele=null;
			Set<Electron> eleset=electronmap.keySet();
			for(Electron a:eleset) {
				if(a.getTrackItOn()==restracknum) {
					deleele=a;	break;
				}
			}
			//this.orbit.transit(deleele, theta, restracknum,tartracknum);
			this.orbit.removePhy(deleele,restracknum );
			Electron ele=ElectronFactory.getInstance(tartracknum);
			orbit.addPhyToTrack(ele, theta, tartracknum);
			s.close();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",Transition,"+
					"transition"+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		/*debug property can add
		 * DoubleProperty d1=new SimpleDoubleProperty();d1.bind(circlepane.heightProperty());
		DoubleProperty d2=new SimpleDoubleProperty();d2.bind(circlepane.heightProperty().multiply(2));
		DoubleProperty d3=new SimpleDoubleProperty();d3.bind(d1.add(d2));
		
		this.arangeBut.setOnAction(e->{
			System.out.println("d1 is "+d1.doubleValue()+"d2 is "
						+d2.doubleValue()+"d3 is "+d3.doubleValue());
		});
		 */
			
		
	}
	/**
	 * A function that transit the atom structure information read from file to the orbit system.
	 */
	private void FromMapToOrbit() {
		Map<Integer,Integer> map=this.atomstruc.getEleOnTrack();
		int curtranum=1;int numofeleonetra;double thetafortrack;
		Electron ele=null;
		for(Integer i:map.keySet()) {
			curtranum=i; numofeleonetra=map.get(i);
			this.orbit.addInsideTrack(i);
			thetafortrack=360.0/numofeleonetra;
			for(int j=1;j<=numofeleonetra;j++) {
				ele=ElectronFactory.getInstance(curtranum);
				this.orbit.addPhyToTrack(ele, j*thetafortrack, curtranum);
			}
		}
		Nucleus nucleus=NucleusFactory.getInstance(this.atomstruc.getCenterName());
		orbit.setCentralObj(nucleus);
	}
	
	@Override
	void Draw() {
		
		
		List<Double> radiuses=this.orbit.getRadius();
		List<HashSet<Electron>> objs=this.orbit.getObjOnTracks();
		Map<Electron,Double> thetas=this.orbit.getThetas();
		StringBuffer s=new StringBuffer();
		int radiussize=radiuses.size();double maxradius=0;
		if(radiussize>0) {
			maxradius=radiuses.get(radiussize-1);
		}
		for(int i=0;i<radiussize;i++) {
			double radiusi=radiuses.get(i);
			s.append("Track "+(i+1)+" : radius is "+radiusi+"\n");
			DrawOneOrbit(radiusi,maxradius,objs.get(i),thetas,s);
		}
		this.outputmes=s.toString();
		this.outputfield.setText(outputmes);
		
		Circle nucircle=new Circle();
		nucircle.centerXProperty().bind(circlepane.widthProperty().divide(2));
		nucircle.centerYProperty().bind(circlepane.heightProperty().divide(2));
		nucircle.radiusProperty().bind(circlepane.heightProperty().multiply((0.9/2)*(0.3/maxradius)));
		nucircle.setStroke(Color.BLUE);nucircle.setFill(Color.WHITE);
		Label l=new Label(this.atomstruc.getCenterName());
		l.setContentDisplay(ContentDisplay.CENTER);
		l.layoutXProperty().bind(circlepane.widthProperty().divide(2));
		l.layoutYProperty().bind(circlepane.heightProperty().divide(2));
		circlepane.getChildren().add(nucircle);circlepane.getChildren().add(l);
	}
	/**
	 * Draw one track of the circular orbit in the system. 
	 * @param radius	the radius of the track.
	 * @param maxrad	the max radius among the track of the orbit this track belongs to.
	 * @param aonetra	objects on one track.
	 * @param thetas	the degree of each object on the track.
	 * @param s			a StringBuffer to put the message of the track in.
	 */
	void DrawOneOrbit(double radius,double maxrad,HashSet<Electron> aonetra,
							Map<Electron,Double> thetas,StringBuffer s) {
		//calculate radius
		DoubleProperty radongui=new SimpleDoubleProperty();
		DoubleProperty xongui=new SimpleDoubleProperty();
		DoubleProperty yongui=new SimpleDoubleProperty();
		
		radongui.bind(circlepane.heightProperty().multiply((0.9/2)*(radius/maxrad)));
		xongui.bind(circlepane.widthProperty().divide(2));
		yongui.bind(circlepane.heightProperty().divide(2));
		//draw circle
		Circle c=new Circle();
		c.centerXProperty().bind(xongui);
		c.centerYProperty().bind(yongui);
		c.radiusProperty().bind(radongui);
		c.setStroke(Color.GREEN);c.setFill(null);
		circlepane.getChildren().add(c);
		//draw athletes
		for(Electron a:aonetra) {
			double atheta=thetas.get(a);
			c=new Circle();
			//draw circle
			DoubleProperty cxongui=new SimpleDoubleProperty();
			DoubleProperty cyongui=new SimpleDoubleProperty();
			cxongui.bind(xongui.add(radongui.multiply(Math.cos(Math.toRadians(atheta)))));
			cyongui.bind(yongui.subtract(radongui.multiply(Math.sin(Math.toRadians(atheta)))));
			c.centerXProperty().bind(cxongui);
			c.centerYProperty().bind(cyongui);
			/*
			 * c.centerXProperty().bind(xongui.add(radongui.multiply(Math.cos(atheta))));
			c.centerYProperty().bind(yongui.add(radongui.multiply(Math.sin(atheta))));
			 */
			c.setStroke(Color.BLUE);c.setFill(Color.WHITE);c.setRadius(5);
			circlepane.getChildren().add(c);
		
			
			/*debug print
			 * System.out.println("radius is "+radongui.doubleValue()+"\n");
				System.out.println("X is "+xongui.doubleValue()+"\n");
				System.out.println("Y is "+yongui.doubleValue()+"\n");
				System.out.println("theta is "+atheta+"\n");
				System.out.println("cX is "+cxongui.doubleValue()+"\n");
				System.out.println("cY is "+cyongui.doubleValue()+"\n");
			 */
		}
		s.append("There is "+aonetra.size()+" electrons on this orbit\n");
		
		/*
		 * for(Label l:llist) {
			circlepane.getChildren().add(l);
		}
		 */
		/*
		 *  Circle c1;Label l;
		for(int i=1;i<10;i++) {
			c1=new Circle();
			c1.setCenterX(10*i);c1.setCenterY(10*i);
			c1.setRadius(3);c1.setFill(Color.YELLOW);
			l=new Label(String.valueOf(i),c);
			l.setContentDisplay(ContentDisplay.RIGHT);
			circlepane.getChildren().addAll(c1,l);
		}
		 */
	}
	
	
	Memento save() {
		return new Memento(this.orbit);
	}
	
	void restore(Memento m){
		this.orbit=m.getState();
	}
}
