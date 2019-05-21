package applications;
/**
 * This is a class used to compose the using information during one period
 * of the personal app ecosystem.
 * @author Luke Skywalker
 *
 */
public class AppUseNote implements Comparable<AppUseNote>{
	
	private int frequence;
	private int time;
	private String name;
	/*Abstract function:
	 * 	1.frequence means the frequency the app is used during this period.
	 * 	2.time means the total time the app is used during this period.
	 * 	3.name means the name of the app.
	 */
	/*Rep Invariant:
	 * 	1.frequence must be 0 or a positive number.
	 * 	2.time must be 0 or a positive number.
	 * 	3.name cannot be an empty string.
	 */
	void checkRep() {
		assert frequence>=0;
		assert time>=0;
		assert name.equals("")==false;
	}
	/**
	 * Creator of an AppUseNote
	 * @param frequence		the frequence used to creat an instance of AppUseNote.
	 * @param time			the time used to creat an instance of AppUseNote.
	 * @param name			the name of this app.
	 */
	AppUseNote(int frequence,int time,String name){
		assert frequence>=0;
		assert time>=0;
		assert name.equals("")==false;
		this.frequence=frequence;
		this.time=time;
		this.name=name;
		checkRep();
	}
	/**
	 * Get the frequency of the app use note.
	 * @return		 the frequency of the app use note.
	 */
	public int getFrequence() {
		return this.frequence;
	}
	/**
	 * Get the total use time of the app use note.
	 * @return		 the total use time of the app use note.
	 */
	public int getTime() {
		return this.time;
	}
	/**
	 * Get the name of the app.
	 * @return the name of the app.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Get the synthesize of the app based on the frequence and the time information of this app.
	 * @return		the synthesize of the app
	 */
	public int getSynthesize() {
		int ret= frequence*10+time;
		assert  ret>=0;
		return ret;
	}

	@Override
	public int compareTo(AppUseNote a) {
		return this.getSynthesize()-a.getSynthesize();
	}
	@Override
	public String toString() {
		StringBuffer s=new StringBuffer(this.name+"  frequence: ");
		s.append(this.frequence);
		s.append("time: ");
		s.append(this.time);
		return s.toString();
	}
}

