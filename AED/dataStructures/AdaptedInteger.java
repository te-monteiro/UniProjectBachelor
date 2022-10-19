package dataStructures;

import java.io.Serializable;

/**
 * Returns an AdaptedInteger value
 * is part of the dataStructures Package
 * 
 * @author Angelo Duarte (47427) <amv.duarte@campus.fct.unl.pt>
 * @author Marta Carlos (47592) <m.carlos@campus.fct.unl.pt>
 *
 */
public interface AdaptedInteger extends Comparable<AdaptedInteger>, Serializable{
	
	/**
	 * Returns the value of the integer
	 * @return int integer
	 */
	public int getValue();

}
