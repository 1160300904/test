package applications;

import circularOrbit.*;
import physicalObject.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.*;

import GUI.LogSaver;
import appExceptions.FileInfoConflictException;
import appExceptions.FileSyntaxException;
import centralObject.*;
/**
 * This is a class represents an atom structure.
 * @author Luke Skywalker
 *
 */
public class AtomStructure extends ConcreteCircularOrbit<Nucleus, Electron> {
	
	private String elementname;
	private int numoftracks;
	private Map<Integer,Integer> elefromfile=new HashMap<>();
	
	//log
		LogSaver logsaver=LogSaver.getInstance();
		Logger log=logsaver.getLogger();
		
	/*Abstract function:
	 * 	1.elementname is the name of the element.
	 * 	2.numoftracks is the track number read from the file.
	 *  3.elefromfile contains the information of electron on tracks around the nucleus.
	 */
	/*Rep Invariant
	 * 	1.numoftracks must be a positive number.
	 * 	2.each element in elefromfile must be a positive number.
	 * 	3.the track number of this atom structure must equals with the max number in the elefromfile.
	 * 		And each track below that track must be declared in elefromfile.
	 */
	void checkRep() {
		assert this.numoftracks>0;
		Set<Integer> keySet=this.elefromfile.keySet();
		assert keySet.size()==this.numoftracks;
		for(Integer i:keySet) {
			int i1=i;
			assert i>0;
			int value=this.elefromfile.get(i);
			assert value>0;
		}
		for(int i=1;i<=this.numoftracks;i++) {
			Integer in=Integer.valueOf(i);
			assert keySet.contains(in);
		}
	}
	/**
	 * Initialize the AtomStructure from a file you provide.
	 * @param file		the file you want to initialize from.
	 * @throws FileNotFoundException
	 * 					If the file cannot be found.
	 * @throws FileSyntaxException
	 * 					If the sentences in the file has syntax errors.
	 * @throws FileInfoConflictException
	 * 					If the information in the file conflicts with each other.
	 */
	public void initWithFile(File file) throws FileNotFoundException,FileSyntaxException,
			FileInfoConflictException {
		assert file!=null;
		Scanner input=new Scanner(file);String str=null;int linenum=1;
		Pattern elementnamep=Pattern.compile("ElementName\\s*::=\\s*([A-Z][a-z]?)\\s*");
		
		Pattern trackp=Pattern.compile("NumberOfTracks\\s*::=\\s*([0-9]+)\\s*");
		//NumberOfElectron ::= 1/2;2/8;3/18;4/8;5/1
		String numofelepstr="NumberOfElectron\\s*::=\\s*(([0-9]+)/([0-9]+);)+\\s*";
		Pattern numofelep=Pattern.compile(numofelepstr);
		Matcher matcher;
		while(input.hasNext()) {
			str=input.nextLine();
			if(str.matches("ElementName\\s*::=\\s*([A-Z][a-z]?)\\s*")) {
				matcher=elementnamep.matcher(str);matcher.find();
				this.elementname=matcher.group(1);
				//System.out.println("elementname is:"+this.elementname);
			}else if(str.matches("NumberOfTracks\\s*::=\\s*([0-9]+)\\s*")) {
				matcher=trackp.matcher(str);matcher.find();
				//System.out.println(matcher.group(1));
				this.numoftracks=Integer.valueOf(matcher.group(1));
				//System.out.println("track num is:"+this.numoftracks);
			}else if((str+';').matches(numofelepstr)) {
				Pattern splitp=Pattern.compile("(([0-9]+)/([0-9]+);?)+");
				matcher=splitp.matcher(str);matcher.find();
				str=matcher.group(0);int i1;int i2;
				Pattern nump=Pattern.compile("[0-9]+");
				matcher=nump.matcher(str);
				while(matcher.find()) {
					i1=Integer.valueOf(matcher.group(0));
					matcher.find();
					i2=Integer.valueOf(matcher.group(0));
					this.elefromfile.put(i1, i2);
				}
				/*debug
				 *  for(Integer i:this.elefromfile.keySet()) {
					System.out.print(i+"   ");
				}
				System.out.println();
				for(Integer i:this.elefromfile.values()) {
					System.out.print(i+"   ");
				}
				 */
				 
				/*
				 * String name=matcher.group(1);int num=Integer.valueOf(matcher.group(2));
				String nation=matcher.group(3);int age=Integer.valueOf(matcher.group(4));
				double bestscore=Double.valueOf(matcher.group(5));
				//System.out.println(name+"  "+num+"  "+nation+"  "+age+"  "+bestscore);
				Athlete a=AthleteFactory.getInstance(name, num, nation, age, bestscore);
				this.athletes.add(a);
				 */
			}else if(str.matches("")) {
				
			}else {
				System.out.println(linenum+": "+str);
				String mes="file syntax error: "+str;
				LogRecord lr=new LogRecord(Level.INFO, "Exception"+",FileSyntaxException,"+
				mes+",try again");
				this.log.log(lr);
				this.logsaver.add(lr);
				throw new FileSyntaxException(mes);
			}
			linenum++;
		}
		if(trackNumConflict()==false) {
			String mes="Self conflict on number of tracks of the atom: "
					+ "From file is "+this.numoftracks+" from track info is: "+this.elefromfile.keySet().size();
			LogRecord lr=new LogRecord(Level.INFO, "Exception"+",FileInfoConflictException,"+
			mes+",try again");
			this.log.log(lr);
			this.logsaver.add(lr);
			throw new FileInfoConflictException(mes);
		}
		input.close();
		checkRep();
	}
	/**
	 * Test if the track number get from file conflicts with the track information. 
	 * @return		true if they conflict, false otherwise.
	 */
	private boolean trackNumConflict() {
		return this.numoftracks==this.elefromfile.keySet().size();
	}
	/**
	 * get an unmodifiable view of the elements on track, which can be used to generate 
	 * a circular orbit.
	 * @return	an unmodifiable view of the elements on track
	 */
	public Map<Integer,Integer> getEleOnTrack(){
		//return an unmodifiable view of field. 
		return Collections.unmodifiableMap(this.elefromfile);
	}
	/**
	 * Get the center name of the atom.
	 * @return		the center name of the atom.
	 */
	public String getCenterName() {
		//use immutable type string, helps prevent rep exposure.
		assert this.elementname.equals("")==false;
		return this.elementname;
	}
	/*
	 * public void ElectronJump(Electron ele,  double theta,int tracknum) {
		this.transit(ele, theta, tracknum);
	}
	 */
	 
}