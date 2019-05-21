package centralObject;


/**
 * This is an implementation of Interface Nucleus.
 */
class ConcreteNucleus implements Nucleus {
	
	private String name;
	/*Abstract function:
	 * 	1.name represents the name of the Nucleus.
	 */
	/*Rep Invariant:
	 * 	1.If there is only one letter in the name, then it must be upper case.
	 * 	2.If there are two letters in the name, then the first letter must be upper case,
	 * 		and the second one must be lower case.
	 * 	3.There can be at most two letters in name.
	 */
	/*Safe from rep exposure.
	 * 	1.all fields are private.
	 * 	2.all return type of th emethods are all immutable.
	 */
	void checkRep() {
		if(name.length()==1) {
			char a= name.charAt(0);
			assert a>='A'&&a<='Z';
		}else if(name.length()==2) {
			char a= name.charAt(0);
			assert a>='A'&&a<='Z';
			char b= name.charAt(1);
			assert b>='a'&&b<='z';
		}else {
			assert false;
		}
	}
	/**
	 * Constructor of a concrete Nucleus.
	 * @param Nucleus
	 */
	ConcreteNucleus(String name){
		if(name.length()==1) {
			char a= name.charAt(0);
			assert a>='A'&&a<='Z';
		}else if(name.length()==2) {
			char a= name.charAt(0);
			assert a>='A'&&a<='Z';
			char b= name.charAt(1);
			assert b>='a'&&b<='z';
		}else {
			assert false;
		}
		this.name=name;
		checkRep();
	}
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Nucleus))
			return false;
		Nucleus o1=(Nucleus)o;
		return o1.getName().equals(this.getName());
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
