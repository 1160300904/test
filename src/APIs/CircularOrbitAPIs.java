package APIs;

import circularOrbit.*;
import physicalObject.*;

import java.util.*;

import centralObject.*;
public class CircularOrbitAPIs<L,E> {
	
	/**
	 * Get the entropy of the input CircularOrbit system, basing on 
	 * the distribution of the objects on tracks.
	 * @param c		the circular orbit system you specify.
	 * @return		the entropy of the input CircularOrbit system
	 */
	public double getObjectDistributionEntropy(CircularOrbit<L,E> c) {
		assert c!=null;
		int trackamount=c.TrackAmount();
		double[] objnum=new double[trackamount];
		double sum=0.0;
		for(int i=0;i<trackamount;i++) {
			objnum[i]=c.NumOnTrack(i+1);
			sum+=objnum[i];
		}
		double resultsum=0.0;
		for(int i=0;i<trackamount;i++) {
			if(objnum[i]!=0) {
				objnum[i]=objnum[i]/sum;
				objnum[i]=objnum[i]*Math.log(objnum[i]);
				resultsum+=objnum[i];
			}
		}
		
		return (-1)*resultsum;
	}
	
	/**
	 * Get the logical distance between two objects, which is the minimum number of edges that connects
	 * two objects on track. if these two objects aren't connected, then return value is INF.
	 * @param c			the circular orbit system that contains these two objects.
	 * @param e1		one of the object you want to calculate the distance with. It must appears in the
	 * 					circular system.
	 * @param e2		another object you want to calculate the distance with.It must appears in the
	 * 					circular system.
	 * @return			the logical distance between two objects. if these two objects aren't connected, 
	 * 					then return value is INF.
	 */
	public int getLogicalDistance (CircularOrbit<L,E> c, E e1, E e2) {
		assert c!=null;assert e2!=null;assert e1!=null;
		int ret= c.getLogicalDistance(e1, e2);
		assert ret>=0;
		return ret;
	}
	
	/**
	 * Get the physical distance between two objects,
	 * @param c			the circular orbit system that contains these two objects.
	 * @param e1		one of the object you want to calculate the distance with.It must appears in the
	 * 					circular system.
	 * @param e2		another object you want to calculate the distance with.It must appears in the
	 * 					circular system.
	 * @return			the physical distance between two objects.
	 */
	public double getPhysicalDistance (CircularOrbit<L,E> c, E e1, E e2) {
		assert c!=null;assert e2!=null;assert e1!=null;		
		double ret= c.getPhysicalDistance(e1, e2);
		assert ret>=0;
		return ret;
	}
	
	/**
	 * calculate the difference between two circular orbits. The defination of Difference is in the Difference
	 * API.
	 * @param c1		one of the circular orbit you want to calculate with.
	 * @param c2		another circular orbit you want to calculate with.
	 * @return			the difference between two circular orbits. The definition of Difference 
	 * 					is in the Difference API.
	 */
	public Difference getDifference (CircularOrbit<L,E> c1, CircularOrbit<L,E> c2) {
		assert c2!=null;assert c1!=null;	
		L cent=c1.getCentralObj();
		if(cent instanceof Nucleus)	{
			return getAtomDifference((CircularOrbit<Nucleus,Electron>)c1,
					(CircularOrbit<Nucleus,Electron>)c2);
		}
												
		if(cent instanceof User) {
			return getPersonalAppDifference ((CircularOrbit<User,PersonalApp>) c1, 
					(CircularOrbit<User,PersonalApp> )c2) ;
		}
			
		Difference ret= getRaceGameDifference ((CircularOrbit<String,Athlete> )c1, 
				(CircularOrbit<String,Athlete>) c2);
		assert ret!=null;
		return ret;
	}
	/**
	 * Get the difference between two circular orbit represents two AtomStructures.
	 * @param c1	one of the circular orbit you want to calculate with.
	 * @param c2	another circular orbit you want to calculate with.
	 * @return		the difference between two circular orbits. The definition of Difference 
	 * 				is in the Difference API.
	 */
	public Difference getAtomDifference (CircularOrbit<Nucleus,Electron> c1, 
									CircularOrbit<Nucleus,Electron> c2) {
		Difference d= Difference.getInstance();
		int c1tracknum=c1.TrackAmount();int c2tracknum=c2.TrackAmount();
		int maxtracknum=Math.max(c1tracknum, c2tracknum);
		int mintracknum=Math.min(c1tracknum, c2tracknum);
		d.setTrackNumDiff(c1tracknum-c2tracknum);
		for(int i=1;i<=mintracknum;i++) {
			d.appendObjNumDiff(c1.getObjNumOnTrack(i)-c2.getObjNumOnTrack(i));
		}
		if(c1tracknum<maxtracknum) {
			for(int i=mintracknum+1;i<=maxtracknum;i++) {
				d.appendObjNumDiff(0-c2.getObjNumOnTrack(i));
			}
		}
		if(c2tracknum<maxtracknum) {
			for(int i=mintracknum+1;i<=maxtracknum;i++) {
				d.appendObjNumDiff(c1.getObjNumOnTrack(i));
			}
		}
		return d;
	}
	/**
	 * Get the difference between two circular orbit represents two PersonalAppEcosystem..
	 * @param c1	one of the circular orbit you want to calculate with.
	 * @param c2	another circular orbit you want to calculate with.
	 * @return		the difference between two circular orbits. The definition of Difference 
	 * 				is in the Difference API.
	 */
	public Difference getPersonalAppDifference (CircularOrbit<User,PersonalApp> c1, 
			CircularOrbit<User,PersonalApp> c2) {
		Difference d= Difference.getInstance();
		int c1tracknum=c1.TrackAmount();int c2tracknum=c2.TrackAmount();
		if(c1tracknum!=c2tracknum) {
			System.out.println("error: two orbits in a racegame have different track numbers.\n");
			return null;
		}
		d.setTrackNumDiff(c1tracknum-c2tracknum);
		List<HashSet<PersonalApp>> c1objlist=c1.getObjOnTracks();
		List<HashSet<PersonalApp>> c2objlist=c2.getObjOnTracks();
		for(int i=1;i<=c1tracknum;i++) {
			d.appendObjNumDiff(c1.getObjNumOnTrack(i)-c2.getObjNumOnTrack(i));
			d.appendObjDetailDiff(getTrackOnAppDiff(c1objlist.get(i-1),c2objlist.get(i-1)));
		}
		return d;
	}
	private String getTrackOnAppDiff(HashSet<PersonalApp> s1,HashSet<PersonalApp> s2) {
		int s1size=s1.size();int s2size=s2.size();
		if(s1size==0&&s2size==0) {
			return "None";
		}
		if(s1size==0) {
			StringBuffer s=new StringBuffer("{ }-{");
			for(PersonalApp pa:s2) {
				s.append(pa.getName()+",");
			}
			s.setCharAt(s.length()-1, '}');
			return s.toString();
		}
		if(s2size==0) {
			StringBuffer s=new StringBuffer("{");
			for(PersonalApp pa:s1) {
				s.append(pa.getName()+",");
			}
			s.setCharAt(s.length()-1, '}');
			s.append("-{ }");
			return s.toString();
		}
		return bothHaveApps(s1,s2);
		
	}
	//use name to compare here
	private String bothHaveApps(HashSet<PersonalApp> s1,HashSet<PersonalApp> s2) {
		//defensive copy here
		HashSet<PersonalApp> rets1=new HashSet<PersonalApp>(s1);
		HashSet<PersonalApp> rets2=new HashSet<PersonalApp>(s2);
		Iterator<PersonalApp> it=rets1.iterator();	PersonalApp pa=null;
		while(it.hasNext()) {
			pa=it.next();
			if(s2.contains(pa)) {
				it.remove();
			}
		}
		 it=rets2.iterator();	
		while(it.hasNext()) {
			pa=it.next();
			if(s1.contains(pa)) {
				it.remove();
			}
		}
		StringBuffer s=new StringBuffer("{");int i=0;
		
		for(PersonalApp p:rets1) {
			s.append(p.getName()+",");
			i++;
		}
		if(i==0)	{
			s.append(" ,");
		}
		s.setCharAt(s.length()-1, '}');
		s.append("-{");
		for(PersonalApp p:rets2) {
			s.append(p.getName()+",");
		}
		s.setCharAt(s.length()-1, '}');
		return s.toString();
	}
	/**
	 * Get the difference between two circular orbit represents two RaceGame..
	 * @param c1	one of the circular orbit you want to calculate with.
	 * @param c2	another circular orbit you want to calculate with.
	 * @return		the difference between two circular orbits. The definition of Difference 
	 * 				is in the Difference API.
	 */
	public Difference getRaceGameDifference (CircularOrbit<String,Athlete> c1, 
										CircularOrbit<String,Athlete> c2) {
		Difference d= Difference.getInstance();
		int c1tracknum=c1.TrackAmount();int c2tracknum=c2.TrackAmount();
		if(c1tracknum!=c2tracknum) {
			System.out.println("error: two orbits in a racegame have different track numbers.\n");
			return null;
		}
		d.setTrackNumDiff(c1tracknum-c2tracknum);
		List<HashSet<Athlete>> c1objlist=c1.getObjOnTracks();
		List<HashSet<Athlete>> c2objlist=c2.getObjOnTracks();
		for(int i=1;i<=c1tracknum;i++) {
			d.appendObjNumDiff(c1.getObjNumOnTrack(i)-c2.getObjNumOnTrack(i));
			d.appendObjDetailDiff(getTrackDiffInRaceGame(c1objlist.get(i-1),c2objlist.get(i-1)));
		}
		
		return d;
	}
	
	private String getTrackDiffInRaceGame(HashSet<Athlete> c1ath,HashSet<Athlete> c2ath) {
		int c1size=c1ath.size();int c2size=c2ath.size();
		if(c1size==0&&c2size==0)	return "None";
		Athlete a1=null;Athlete a2=null;
		
		if(c1size==0) {
			for(Athlete a:c2ath) {
				a2=a;
			}
			return "None-"+a2.getName();
		}
		if(c2size==0) {
			for(Athlete a:c1ath) {
				a1=a;
			}
			return a1.getName()+"-None";
		}
		for(Athlete a:c1ath) {
			a1=a;
		}
		for(Athlete a:c2ath) {
			a2=a;
		}
		if(a1.equals(a2))
			return "None";
		return a1.getName()+"-"+a2.getName();
	}
	
}
