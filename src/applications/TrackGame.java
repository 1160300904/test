package applications;

import circularOrbit.*;
import physicalObject.*;
import appExceptions.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.*;

import GUI.LogSaver;
/**
 * This is a class represents a track game.
 */
public class TrackGame extends ConcreteCircularOrbit<String, Athlete> {
	
	private List<Athlete> athletes=new ArrayList<>();
	private List<List<Athlete>> gameplan;
	private int game;
	private int numoftracks;
	//log
	private LogSaver logsaver=LogSaver.getInstance();
	private Logger log=logsaver.getLogger();
	/*Abstract Function:
	 * 	1.athletes represents the athletes generated from the file
	 * 	2.gameplan represents the game plan generated from function ArrangeGame().
	 * 		1)Each inside list of athletes is one match, so the whole gameplan is the matches 
	 * 			generated in some particular ways.
	 * 		2)In each inside list, each index from 0 to its size, represents a track, on which is 
	 * 			an athlete. So, using this inside list, we can generate a match, the index of athlete
	 * 			maps to the track number he owns.
	 * 	3.game represents the type of the game.
	 *  4.numoftracks represents the track numbers read from the file.
	 */
	
	/*Rep Invariant:
	 * 	1.Any two athletes in the 'athletes' fields must not be the same.
	 * 	2.No matter how game plan change, the track number of each match must equals the 'numoftracks'
	 * 	3.No matter how game plan change, if the last match is not full, all other matches must be full
	 * 	4.game must be 100, or 200, or 400, no other number is premitted.
	 * 	4.numoftracks must in a range of [4,10].
	 */
	void checkRep() {
		assert (game==100)||(game==200)||(game==400);
		assert numoftracks<=10&&numoftracks>=4;
		int athsize=this.athletes.size();
		for(int i=0;i<athsize;i++) {
			for(int j=0;j<athsize;j++) {
				if(i!=j) {
					assert athletes.get(i).equals(athletes.get(j))==false;
				}
			}
		}
		int gameplansize=this.gameplan.size();
		for(int i=0;i<gameplansize-1;i++) {
			int matchsize=gameplan.get(i).size();
			if( gameplan.get(i).size()!=this.numoftracks) {
				System.out.println(i);
				assert false;
			}
		}
		
	}
	/**
	 * get the information from the file you provide.
	 * @param file		the file you provide to initialize. It cannot be null.
	 * @return			if the process succeed or not.
	 * @throws FileNotFoundException	
	 * 				if the initial file is not found  in the disk.
	 * @throws FileSyntaxException
	 * 				if the syntax error of the file is avoided.
	 * @throws RepeatedObjectsException
	 * 				if the athlete repeated in the file you provide.
	 */
	public boolean initFromFile(File file) throws FileNotFoundException,FileSyntaxException,
			RepeatedObjectsException {
		assert file!=null;
		Scanner input =new Scanner(file);	String str=null;	int linenum=1;
		//Game ::= 100
		Pattern gamep=Pattern.compile("Game\\s*::=\\s*(100|200|400)\\s*");
		//NumOfTracks ::= 5 
		Pattern trackp=Pattern.compile("NumOfTracks\\s*::=\\s*([4-9]|10)\\s*");
		//Athlete ::= <Cliton,8,USA,21,9.92> 
		String athletepstr="Athlete\\s*::=\\s*<([a-z[A-Z]]+),([0-9]+),([A-Z]{3}),([0-9]+),"
				+ "([0-9]{1,2}\\.[0-9]{2})>\\s*";
		Pattern athletep=Pattern.compile(athletepstr);
		Matcher matcher;
		while(input.hasNext()) {
			str=input.nextLine();
			if(str.matches("Game\\s*::=\\s*(100|200|400)")) {
				matcher=gamep.matcher(str);matcher.find();
				this.game=Integer.valueOf(matcher.group(1));
				//System.out.println("game is:"+this.game);
			}else if(str.matches("NumOfTracks\\s*::=\\s*([4-9]|10)\\s*")) {
				matcher=trackp.matcher(str);matcher.find();
				this.numoftracks=Integer.valueOf(matcher.group(1));
				//System.out.println("track num is:"+this.numoftracks);
			}else if(str.matches(athletepstr)) {
				matcher=athletep.matcher(str);matcher.find();
				String name=matcher.group(1);int num=Integer.valueOf(matcher.group(2));
				String nation=matcher.group(3);int age=Integer.valueOf(matcher.group(4));
				double bestscore=Double.valueOf(matcher.group(5));
				//System.out.println(name+"  "+num+"  "+nation+"  "+age+"  "+bestscore);
				Athlete a=AthleteFactory.getInstance(name, num, nation, age, bestscore);
				//it can be done with a set in the field, then a loop check will not be necessary
				//also, two athlete are the same means, all info of athlete are same.(some might have 
				//the same name.
				if(this.athletes.contains(a)) {
					String mes="Repeated athlete: "+a.getName();
					LogRecord lr=new LogRecord(Level.INFO, "Exception"+",RepeatedObjectsException,"+
					mes+",try again");
					this.log.log(lr);
					this.logsaver.add(lr);
					throw new RepeatedObjectsException(mes);
				}
				this.athletes.add(a);
			}else if(str.matches("")) {
				
			}else {
				System.out.println(linenum+": "+str);
				String mes="FileSyntaxException: "+str;
				LogRecord lr=new LogRecord(Level.INFO, "Exception"+",FileSyntaxException,"+
				mes+",try again");
				this.log.log(lr);
				this.logsaver.add(lr);
				throw new FileSyntaxException(str);
			}
			linenum++;
		}
		input.close();
		
		return true;
	}
	
	/**
	 * ArrangeGame according to the gameplan and the tracknum you provide.
	 * @param gp		the gameplan you provide to arrange an track game. See in class GamePlan.
	 * 					it cannot be null.
	 * @param tracknum	the track number of the game you want to arrange.
	 * 					it must in the range of [4,10].
	 * @return			true if the progress succeeded, false otherwise.
	 */
	public boolean ArrangeGame(GamePlan gp, int tracknum) {
		assert gp!=null;
		assert tracknum<=10&&tracknum>=4;
		/*
		 * if(input.equals("random")) {
			gp=new RandomPlan();
		}else if (input.equals("sorted")){
			gp=new SortedPlan();
		}else { 
			return false;
		}
		 */
		this.gameplan=gp.plan(athletes, tracknum);
		checkRep();
		return true;
	}
	/**
	 * Set the game plan according the input.The athlete in group1, track1, and the athlete
	 * in group2 ,track2, are exchanged. 
	 * You can rearrange the plan with tracks with no one on it.
	 * @param group1	one group you specify.
	 * @param track1	the track number of the athlete you specify 
	 * @param group2	another group you specify.
	 * @param track2	the track number of the other athlete you specify 
	 * @return			true if the progress is succeeded, false otherwise.
	 */
	public boolean SetGamePlan(int group1,int track1,int group2, int track2) {
		
		if(track1<=0||track1>numoftracks) {
			return false;
		}
		if(track2<=0||track2>numoftracks) {
			return false;
		}
		int groupnum=this.gameplan.size();
		if(group1<=0||group1>groupnum) {
			return false;
		}
		if(group2<=0||group2>groupnum) {
			return false;
		}
		group1--;group2--;track1--;track2--;
		Athlete a1=gameplan.get(group1).get(track1);
		Athlete a2=gameplan.get(group2).get(track2);
		//if(a1==null||a2==null)	return false;
		
		gameplan.get(group1).set(track1, a2);
		gameplan.get(group2).set(track2, a1);
		return true;
	}
	/**
	 * Get the game plan generated by the function ArrangeGame().You cannot modify this list.
	 * @return	the game plan generated by the function ArrangeGame().
	 */
	public List<List<Athlete>> getGamePlan(){
		return Collections.unmodifiableList(this.gameplan);
	}
	/**
	 * check if one group as a circular orbit in a track game is legal.
	 * @param c		the circular orbit you want to check.
	 * @return		true if the orbit is legal, false otherwise.
	 */
	public boolean isLegalOneGroup(CircularOrbit<String, Athlete> c) {
		if(c.getCentralObj()!=null)	return false;
		int tracknum=c.TrackAmount();
		if(tracknum<4||tracknum>10)	return false;
		for(int i=1;i<=tracknum;i++) {
			if(c.getObjNumOnTrack(i)>1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Get the total number of athletes of a circular orbit.
	 * @param orbit				the orbit system you want to calculate.
	 * @param tracknum			the track number of the circular oribt.
	 * @return				the total number of athletes of a circular orbit.
	 */
	public int getAthleteNum(CircularOrbit<String, Athlete> orbit,int tracknum) {
		if(tracknum<4||tracknum>10)
			return -1;
		int OrbitAthelteNum=0;
		for(int i=1;i<=tracknum;i++) {
			OrbitAthelteNum+=orbit.getObjNumOnTrack(i);
		}
		return OrbitAthelteNum;
	}
	/**
	 * Get the athlete from a set. The set must contains only one athlete.
	 * @param athset		the set you want to get athlete from. The set must contains only one athlete.
	 * @return			The athlete you want to get.
	 */
	public Athlete getAthOnTrack(HashSet<Athlete> athset) {
		assert athset.size()<=1;
		Athlete a=null;
		for(Athlete a1:athset) {
			a=a1;
		}
		return a;
	}
	/**
	 * Test whether there is two same athletes in two circular orbit.
	 * @param orbit1		One orbit you want to test with.
	 * @param orbit2		Another orbit you want to test with.
	 * @param tracknum		The track number of the circular orbit.
	 * @return				true if there is no two same athletes in two circular orbit.
	 * 						False otherwise.
	 */
	public boolean noSameAthInBothOrbits(CircularOrbit<String, Athlete> orbit1,
			CircularOrbit<String, Athlete> orbit2,int tracknum) {
		Athlete a1=null;Athlete a2=null;
		List<HashSet<Athlete>> athonorbit1=orbit1.getObjOnTracks();
		List<HashSet<Athlete>> athonorbit2=orbit2.getObjOnTracks();
		for(int i=0;i<tracknum;i++) {
			a1=getAthOnTrack(athonorbit1.get(i));
			for(int j=0;j<tracknum;j++) {
				
				a2=getAthOnTrack(athonorbit2.get(j));
				if(a1!=null&&a2!=null) {
					if(a1.equals(a2))	return false;
				}
			}
		}
		return true;
	}
	/**
	 * Test if a list of circular orbit of a track game is legal.
	 * @param clist		The list of circular orbit you want to test.
	 * @return			true if it is legal, false otherwise.
	 */
	public boolean isLegal(List<CircularOrbit<String, Athlete>> clist) {
		int listsize=clist.size();
		if(listsize==0) {
			//System.out.println("There is no circular orbit in this orbit list.\n");
			return false;
		}
		CircularOrbit<String, Athlete> lastorbit=clist.get(listsize-1);
		int tracknum=lastorbit.TrackAmount();
		int lastOrbitAthelteNum=getAthleteNum(lastorbit,tracknum);
		if(lastOrbitAthelteNum<tracknum) {
			for(int i=0;i<listsize-1;i++) {
				if(getAthleteNum(clist.get(i),tracknum)!=tracknum) {
					return false;
				}
			}
		}
		for(int i=0;i<listsize;i++) {
			for(int j=0;j<listsize;j++) {
				if((i!=j)&&
						noSameAthInBothOrbits(clist.get(i),clist.get(j),tracknum)==false) {
					return false;
				}
			}
			if(isLegalOneGroup(clist.get(i))==false)	return false;
		}
		return true;
	}
	/**
	 * Test if two circular orbits of a track game is legal.
	 * @param c1			One orbit you want to test with.
	 * @param orbit2		Another orbit you want to test with.
	 * @return			true if they are legal, false otherwise.
	 */
	public boolean isLegalForTwoOrbits(CircularOrbit<String, Athlete> c1,
			CircularOrbit<String, Athlete> c2) {
		int tracknum=c1.TrackAmount();
		boolean retboo=true;
		retboo=isLegalOneGroup(c1);retboo=isLegalOneGroup(c2);
		retboo=noSameAthInBothOrbits(c1,c2,tracknum);
		return retboo;
	}
}