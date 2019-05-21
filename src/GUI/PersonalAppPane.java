package GUI;

import physicalObject.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import applications.*;
import centralObject.*;
import circularOrbit.*;
import errorHandling.*;
import APIs.*;
import appExceptions.FileInfoConflictException;
import appExceptions.FileSyntaxException;
import appExceptions.RepeatedObjectsException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * This is the class of pane of PersonalAppEcosystem. The circular orbit of app ecosystem
 * will be drawn on this pane.
 * @author Luke Skywalker
 *
 */
public class PersonalAppPane extends CircularOrbitPane<User,PersonalApp>{
	//helper filed
	private List<List<AppUseNote>> ecos=null;
	private List<MyDate> timenode=null;
	private Map<String,PersonalApp> nametoappmap=null;
	private static final int tracknum=4;
	private int curorbindex=1;
	//Trackgame instance
	PersonalAppEcosystem pereco=null;
	//name-circle map(a circle list)  For draw relations on gui
	Map<String,Circle> namecirclemap=new HashMap<>();
	
	//special
	Button difBut=new Button("Difference");
	Button logdisBut=new Button("LogicalDis");	
	Button phyDisBut=new Button("PhysicalDis");
	Button isLegalBut=new Button("IsLegal");
	Button queryBut=new Button("QueryLog");
	FileChooser filechooser=new FileChooser();
	Stage stage=new Stage();
	//exception fields
	PersonalAppHandler appHandler=new PersonalAppHandler();
	

	List<CircularOrbit<User,PersonalApp>> orbitlist=new ArrayList<>();//oribitlist that contains orbits.
	/*Abstract function:
	 * 	1.ecos is used for generate the orbits.
	 * 	2.timenode contains the time stamps of each period of app ecosystem.
	 * 	3.nametoappmap maps the names of the app to itselves.
	 * 	4.tracknum is the number of tracks on each app ecosystems.
	 * 	5.curorbindex is the current index of orbit in the orbit list.
	 * 	6.pereco is used for generating the orbits of personal app ecosystem.
	 * 	7.namecirclemap maps each name of a app to its circle on pane.
	 * 	8.difBut,logdisBut,phyDisBut,isLegalBut are buttons for users to use to operate the GUI.
	 * 	9.filechooser,stage is used for the user to choose initial file of this system.
	 * 	10.appHandler is an exception handler handle the exceptions in this system.
	 * 	11.orbitlist contains the orbits generated from personal app ecosystem.
	 */
	/*Rep Invariant:
	 * 	none.
	 */
	/**
	 * Creator of a pane of personal app ecosystem, which will be shown on the main stage.
	 */
	PersonalAppPane() {
		super();
		//inner jobs
		ApplicationFactory perappfac=new AppEcosystemFactory();
		this.pereco=(PersonalAppEcosystem)perappfac.getApplication();
		String apppstr="([[a-z][A-Z][0-9]]+),([[a-z][A-Z][0-9]]+),([[a-z][A-Z][0-9]-_\\.]+),"
		 + "(\"[[a-z][A-Z][0-9]\\s]+\"),(\"[[a-z][A-Z][0-9]\\s]+\"),([0-9]+),([0-9]{1,3}\\.[0-9]{2})";
		Pattern appp=Pattern.compile(apppstr);		
		
		//gui jobs
		specialuse.add(difBut, 0, 0);
		specialuse.add(logdisBut, 0, 1);
		specialuse.add(isLegalBut, 1, 0);
		specialuse.add(this.phyDisBut, 1, 1);
		specialuse.add(this.queryBut, 2, 1);
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
		phyDisBut.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			Scanner s=new Scanner(inputtext);
			String name1=s.next();String name2=s.next();
			PersonalApp app1=this.nametoappmap.get(name1);PersonalApp app2=this.nametoappmap.get(name2);
			CircularOrbitAPIs<User,PersonalApp> api=new CircularOrbitAPIs<>();
			//int distance=api.getLogicalDistance(orbit, app1, app2);
			double distance=this.orbit.getPhysicalDistance(app1, app2);
			this.outputfield.setText("The physical distance between "+app1+" and "+app2
					+" is "+String.valueOf(distance));
			s.close();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",PhysicalDistance,"+
					inputtext+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		//initbut
		this.initBut.setOnAction(e->{
			/*
			 * File file=filechooser.showOpenDialog(stage);
			try {
				this.pereco.initFromFile(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			this.pereco.ArrangeEcos();
			this.ecos=this.pereco.getEcos();
			this.timenode=this.pereco.getPeriodList();
			this.nametoappmap=this.pereco.getUseNoteToAppMap();
			 */
			
			File file;
			file=filechooser.showOpenDialog(stage);
			while(true) {
				try {
					this.pereco=(PersonalAppEcosystem)perappfac.getApplication();
					this.pereco.initFromFile(file);
					this.pereco.ArrangeEcos();
					break;
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					this.pereco=(PersonalAppEcosystem)perappfac.getApplication();
					file=filechooser.showOpenDialog(stage);
				} catch (FileSyntaxException e1) {
					String ret=this.appHandler.FilesyntaxHandling(e1.getMessage());
					this.outputfield.setText(ret);
					this.pereco=(PersonalAppEcosystem)perappfac.getApplication();
					file=filechooser.showOpenDialog(stage);
				} catch (RepeatedObjectsException e1) {
					System.out.println(e1.getMessage());
					this.outputfield.setText(e1.getMessage());
					this.pereco=(PersonalAppEcosystem)perappfac.getApplication();
					file=filechooser.showOpenDialog(stage);
				} catch (FileInfoConflictException e1) {
					System.out.println(e1.getMessage());
					this.outputfield.setText(e1.getMessage());
					this.pereco=(PersonalAppEcosystem)perappfac.getApplication();
					file=filechooser.showOpenDialog(stage);
				}
			}
			
			
			this.ecos=this.pereco.getEcos();
			this.timenode=this.pereco.getPeriodList();
			this.nametoappmap=this.pereco.getUseNoteToAppMap();
			FromEcoToOrbit();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",Initialize,"+
					"initialize from file"+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		this.addObjBut.setOnAction(e->{
			String str=this.inputfield.getText();Matcher matcher;
			matcher=appp.matcher(str);matcher.find();
			String name=matcher.group(1);String company=matcher.group(2);
			String version=matcher.group(3);String functionality=matcher.group(4);
			String businessarea=matcher.group(5);
			int tracknum=Integer.valueOf(matcher.group(6));
			double thetaontra=Double.valueOf(matcher.group(7));
			//System.out.println(name+"  "+num+"  "+nation+"  "+age+"  "+bestscore);
			//Athlete a=AthleteFactory.getInstance(name, num, nation, age, bestscore);
			PersonalApp a=PersonalAppFactory.getInstance(name, company, version, 
					functionality, businessarea);
			orbit.addPhyToTrack(a, thetaontra, tracknum);
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",AddObject,"+
					str+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		this.remObjBut.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			Scanner s=new Scanner(inputtext);
			String name=s.next();int tracknum=s.nextInt();
			/*
			 * Map<PersonalApp, Double> athletesmap=this.orbit.getThetas();PersonalApp deleath=null;
			Set<PersonalApp> athset=athletesmap.keySet();
			for(PersonalApp a:athset) {
				if(a.getName().equals(name)) {
					deleath=a;	break;
				}
			}
			 */
			PersonalApp deleath=this.nametoappmap.get(name);
			orbit.removePhy(deleath, tracknum);
			s.close();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",RemoveObject,"+
					inputtext+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		setOrbit.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			int input=Integer.valueOf(inputtext);
			int orbitlistsize=this.orbitlist.size();
			if(input>orbitlistsize)	{
				this.outputfield.setText("There is only "+orbitlistsize+" orbits in this plan");
			}else {
				this.orbit=this.orbitlist.get(input-1);
			}
			this.curorbindex=input;
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",SetOrbit,"+
					inputtext+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		this.logdisBut.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			Scanner s=new Scanner(inputtext);
			String name1=s.next();String name2=s.next();
			PersonalApp app1=this.nametoappmap.get(name1);PersonalApp app2=this.nametoappmap.get(name2);
			CircularOrbitAPIs<User,PersonalApp> api=new CircularOrbitAPIs<>();
			//int distance=api.getLogicalDistance(orbit, app1, app2);
			int distance=this.orbit.getLogicalDistance(app1, app2);
			this.outputfield.setText("The physical distance between "+app1+" and "+app2
					+" is "+String.valueOf(distance));
			s.close();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",LogicalDistance,"+
					inputtext+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		
		this.difBut.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			Scanner s=new Scanner(inputtext);
			int index1=s.nextInt();int index2=s.nextInt();
			CircularOrbit<User,PersonalApp> c1=this.orbitlist.get(index1-1);
			CircularOrbit<User,PersonalApp> c2=this.orbitlist.get(index2-1);
			CircularOrbitAPIs<User,PersonalApp> api=new CircularOrbitAPIs<>();
			Difference diff=api.getPersonalAppDifference(c1, c2);
			this.outputfield.setText(diff.toString());
			s.close();
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",GetDifference,"+
					inputtext+",succeed");
			this.log.log(lr);
			this.logsaver.add(lr);
		});
		this.isLegalBut.setOnAction(e->{
			String inputtext=this.inputfield.getText();
			Scanner s=new Scanner(inputtext);
			int index=s.nextInt();String appname1=s.next();String appname2=s.next();
			boolean ret=this.pereco.synthensisLegal(this.orbitlist.get(index-1), 
									appname1, appname2, index);
			StringBuffer sbuf=new StringBuffer("App "+appname1+" and "+appname2+" is: ");
			if(ret==true) {
				sbuf.append("legal\n");
			}else {
				sbuf.append("illegal\n");
			}
			this.outputfield.setText(sbuf.toString());
			LogRecord lr=new LogRecord(Level.INFO, "Operation"+",IsLegal,"+
					inputtext+",succeed");
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
	 * A function that transit the ecosystem  information read from file to the orbit system.
	 */
	private void FromEcoToOrbit() {
		int size=this.ecos.size();
		
		int maxsynthensis=0;int syn;
		for(int i=0;i<size&&i<100;i++) {
			for(AppUseNote a:this.ecos.get(i)) {
				syn=a.getSynthesize();
				if(maxsynthensis<syn) {
					maxsynthensis=syn;
				}
			}
		}
		//List<List<PersonalApp>> orbittracks=null;
		CircularOrbit<User,PersonalApp> cor=null;HashSet<String> targetappnames=null;
		//System.out.println("max synthesis is:  "+maxsynthensis);
		for(List<AppUseNote> aulist:ecos) {
			
			//add circular orbit	
			cor=CircularOrbit.empty();
			for(int i=1;i<=tracknum;i++) {
				cor.addInsideTrack(i);
			}
			String aunname=null;List<String> aunnamelist=new ArrayList<>();
			for(AppUseNote aun:aulist) {
				int onwhichtrack=this.OnWhichTrack(aun, maxsynthensis);
				aunname=aun.getName();aunnamelist.add(aunname);
				PersonalApp pa=this.nametoappmap.get(aunname);
				//System.out.println("app is: "+aun.getName()+"   synthesis is: "+aun.getSynthesize()
				//+"   so on track  "+onwhichtrack);
				double theta=Math.random()*360.0;
				cor.addPhyToTrack(pa, theta, onwhichtrack);
			}
			//add relation for cor
			for(String name:aunnamelist) {
				targetappnames=this.pereco.getRelationMap().get(name);
				PersonalApp pa=this.nametoappmap.get(name);PersonalApp tarpa=null;
				for(String tarname:targetappnames) {
					tarpa=this.nametoappmap.get(tarname);
					if(cor.containsObject(tarpa)) {
						cor.addPhyRelation(pa, tarpa);
					}
				}
			}
			this.orbitlist.add(cor);
		}
		
	}
	/**
	 * Given an app use note and the max synthesis of apps on one circular orbit, calculating
	 * which track should the app on.
	 * @param aun		an app use note you want to calculate with.
	 * @param maxsyn	the max synthesis of apps on one circular orbit
	 * @return			which track should the app on
	 */
	private int OnWhichTrack(AppUseNote aun,int maxsyn) {
		int syn=aun.getSynthesize();
		if(syn>0.1*maxsyn) {
			return 1;
		}else if(syn>0.05*maxsyn) {
			return 2;
		}else if(syn>0.01*maxsyn) {
			return 3;
		}else  {
			return 4;
		}
	}
	
	
	@Override
	void Draw() {
		List<Double> radiuses=this.orbit.getRadius();
		List<HashSet<PersonalApp>> objs=this.orbit.getObjOnTracks();
		Map<PersonalApp,Double> thetas=this.orbit.getThetas();
		this.namecirclemap=new HashMap<>();
		StringBuffer s=new StringBuffer();
		
		
		int radiussize=radiuses.size();double maxradius=0;
		if(radiussize>0) {
			maxradius=radiuses.get(radiussize-1);
		}
		MyDate f=this.timenode.get(curorbindex-1);
		MyDate t=this.timenode.get(curorbindex);
		s.append("From "+f.toString()+" to "+t.toString()+"\n");
		for(int i=0;i<radiussize;i++) {
			double radiusi=radiuses.get(i);
			s.append("Track "+(i+1)+" : radius is "+radiusi+"\n");
			DrawOneOrbit(radiusi,maxradius,objs.get(i),thetas,s);
		}
		this.outputmes=s.toString();
		this.outputfield.setText(outputmes);
		
		//set center circle
		Circle nucircle=new Circle();
		nucircle.centerXProperty().bind(circlepane.widthProperty().divide(2));
		nucircle.centerYProperty().bind(circlepane.heightProperty().divide(2));
		nucircle.radiusProperty().bind(circlepane.heightProperty().multiply((0.9/2)*(0.3/maxradius)));
		nucircle.setStroke(Color.BLUE);nucircle.setFill(Color.WHITE);
		Label l=new Label(this.pereco.getUserName());
		l.setContentDisplay(ContentDisplay.CENTER);
		l.layoutXProperty().bind(circlepane.widthProperty().divide(2));
		l.layoutYProperty().bind(circlepane.heightProperty().divide(2));
		l.setOnMouseEntered((MouseEvent e) -> {
			l.setScaleX(2);
			l.setScaleY(2);
		});
		l.setOnMouseExited((MouseEvent e) -> {
			l.setScaleX(1);
			l.setScaleY(1);
		});
		circlepane.getChildren().addAll(nucircle,l);
		
		//draw relations
		
		List<String> appname=new ArrayList<>(namecirclemap.keySet());
		/*
		 * for(String name:appname) {
			System.out.println(name+"   "+this.nametoappmap.toString());
		}
		 */
		int namelistsize=appname.size();int[][] visited=new int[namelistsize][namelistsize];
		String namei=null;String namej=null;Circle ci=null;Circle cj=null;
		for(int i=0;i<namelistsize;i++) {
			for(int j=0;j<namelistsize;j++) {
				if(i!=j&&visited[i][j]==0) {
					namei=appname.get(i);namej=appname.get(j);
					if(this.pereco.isRelated(namei, namej)) {
						Line line=new Line();ci=namecirclemap.get(namei);cj=namecirclemap.get(namej);
						line.startXProperty().bind(ci.centerXProperty());
						line.startYProperty().bind(ci.centerYProperty());
						line.endXProperty().bind(cj.centerXProperty());
						line.endYProperty().bind(cj.centerYProperty());
						line.setStroke(Color.BLUE);
						line.setStrokeWidth(1);
						this.circlepane.getChildren().add(line);
					}
					visited[i][j]=1;visited[j][i]=1;
				}
			}
		}
		/*
		 * radiuses=new ArrayList<>();radiuses.add(Double.valueOf(10));radiuses.add(Double.valueOf(100));
		Athlete a1=AthleteFactory.getInstance("1", 1, "AAA", 1, 10.10);
		Athlete a2=AthleteFactory.getInstance("2", 2, "BBB", 2, 20.20);
		Athlete a3=AthleteFactory.getInstance("3", 2, "BBB", 2, 20.20);
		HashSet<Athlete> objjs=new HashSet<>();objjs.add(a1);objjs.add(a2);objjs.add(a3);
		thetas=new HashMap<>();
		thetas.put(a1, Double.valueOf(45));thetas.put(a2, Double.valueOf(90));
		thetas.put(a3, Double.valueOf(30));
			DrawOneOrbit(10,20,objjs,thetas);
		 */
			
		/*
		 * Circle c1=new Circle();
		c1.setCenterX(794.0);c1.setCenterY(341.0);c1.setRadius(10);
		c1.setFill(Color.RED);circlepane.getChildren().add(c1);
		 */
		
	}
	/**
	 * Draw one track of the circular orbit in the system. 
	 * @param radius	the radius of the track.
	 * @param maxrad	the max radius among the track of the orbit this track belongs to.
	 * @param aonetra	objects on one track.
	 * @param thetas	the degree of each object on the track.
	 * @param s			a StringBuffer to put the message of the track in.
	 */
	void DrawOneOrbit(double radius,double maxrad,HashSet<PersonalApp> aonetra,
						Map<PersonalApp,Double> thetas,StringBuffer s) {
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
		for(PersonalApp a:aonetra) {
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
			//add label for each app
			Label l=new Label(a.getName());
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
			circlepane.getChildren().addAll(c,l);
			namecirclemap.put(a.getName(), c);
			
		
			s.append("At theta "+atheta+" : PersonalApp <"+a.getName()+","+a.getCompany()+","
			+a.getVersion()+","+a.getBusinessArea()+","+a.getFunctionality()+">\n");
			/*debug print
			 * System.out.println("radius is "+radongui.doubleValue()+"\n");
				System.out.println("X is "+xongui.doubleValue()+"\n");
				System.out.println("Y is "+yongui.doubleValue()+"\n");
				System.out.println("theta is "+atheta+"\n");
				System.out.println("cX is "+cxongui.doubleValue()+"\n");
				System.out.println("cY is "+cyongui.doubleValue()+"\n");
			 */
		}
		
		
	}	
}
