package APIs;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
public class ConcreteListDifference implements Difference {
	private int TrackNumDiff;
	private List<Integer> ObjNumDiff=new ArrayList<>();
	private List<String> ObjDetailDiff=new ArrayList<>();
	/*Abstract function:
	 * 	1.TrackNumDiff is the difference on track numbers between two circular orbits.
	 * 		if the first one has a tracks, and the second one has b tracks, then the TrackNumDiff is a-b.
	 * 	2.ObjNumDiff is a list consists of object difference in number.	
	 * 		On a particular track, if the first one has a objects, and the second one has b tracks, 
	 * 		then the ObjNumDiff is a-b.
	 * 	3.ObjDetailDiff is a list consists of object difference in details.
	 * 		On a particular track, if the first one has objects {a,b,c}, and the second one has objects
	 * 		{b,c,d,e}, then the ObjNumDiff is {a}-{d,e}.
	 */
	/*Rep Invariant:
	 * 	the size of ObjNumDiff and ObjDetailDiff should be the same
	 */
	void checkRep(){
		for(Integer i:this.ObjNumDiff) {
			assert i!=null;
		}
		for(String s:this.ObjDetailDiff) {
			assert s!=null;
		}
		assert this.ObjDetailDiff.size()==this.ObjNumDiff.size();
	}
	@Override
	public void setTrackNumDiff(int tracknumdif) {
		this.TrackNumDiff=tracknumdif;
		checkRep();
	}

	@Override
	public boolean setObjNumDiff(int tracknum, int objnumdiff) {
		assert tracknum>0;
		int numdifsize=this.ObjNumDiff.size();
		if(tracknum>numdifsize) {
			return false;
		}
		this.ObjNumDiff.set(tracknum-1, objnumdiff);
		return true;
	}

	@Override
	public boolean setObjDetailDiff(int tracknum, String objdetaildiff) {
		assert tracknum>0;
		if(objdetaildiff.equals(""))	return false;
		int numdifsize=this.ObjDetailDiff.size();
		if(tracknum>numdifsize) {
			return false;
		}
		this.ObjDetailDiff.set(tracknum-1, objdetaildiff);
		return true;
	}
	
	
	@Override
	public void appendObjNumDiff(int objnumdiff) {
		this.ObjNumDiff.add( objnumdiff);
	}
	@Override
	public void appendObjDetailDiff(String objdetaildiff) {
		this.ObjDetailDiff.add(objdetaildiff);
	}
	
	
	@Override
	public int getTrackNumDiff() {
		return this.TrackNumDiff;
	}

	@Override
	public int getObjNumDiff(int tracknum) {
		assert tracknum>0;
		int numdifsize=this.ObjNumDiff.size();
		if(tracknum>numdifsize) {
			return Integer.MAX_VALUE;
		}
		return this.ObjNumDiff.get(tracknum-1);
	}

	@Override
	public String getObjDetailDiff(int tracknum) {
		assert tracknum>0;
		int numdifsize=this.ObjDetailDiff.size();
		if(tracknum>numdifsize) {
			return "Index out of bound";
		}
		return this.ObjDetailDiff.get(tracknum-1);
	}

	@Override
	public String toString() {
		StringBuffer s=new StringBuffer();
		s.append("The difference of track numbers is: "+this.TrackNumDiff+" \n");
		int tracknum=this.ObjNumDiff.size();
		for(int i=1;i<=tracknum;i++) {
			s.append("The difference of object numbers on track "+i+" is : "+this.ObjNumDiff.get(i-1)+"\n");
		}
		
		if(this.ObjDetailDiff.size()==0) {
			s.append("There is no object detail differences between two orbits.\n");
		}else {
			for(int i=1;i<=tracknum;i++) {
				s.append("The difference of object detail on track "+i
									+" is : "+this.ObjDetailDiff.get(i-1)+"\n");
			}
		}
		
		return s.toString();
		
	}
	
}
