package dataStructures;

/**
 * Returns an AdaptedInteger value
 * is part of the dataStructures Package
 * 
 * @author Angelo Duarte (47427) <amv.duarte@campus.fct.unl.pt>
 * @author Marta Carlos (47592) <m.carlos@campus.fct.unl.pt>
 *
 */
public class AdaptedIntegerClass implements AdaptedInteger{
	
	private static final long serialVersionUID = 1L;
	
	protected int integer;
	
	public AdaptedIntegerClass ( int number ){
		integer = number;
	}
	
	@Override
	public int getValue() {
		return integer;
	}

	@Override
	public int compareTo(AdaptedInteger o) {
		return ( integer - o.getValue() )* (-1);
	}

}
