/**
 * Project: Gis
 * File: ApplicationException.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 *
 */

package a00918606.gis;

@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	/**
	 * Construct an ApplicationException
	 * 
	 * @param message
	 *            the exception message.
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Construct an ApplicationException
	 * 
	 * @param throwable
	 *            a Throwable.
	 */
	public ApplicationException(Throwable throwable) {
		super(throwable);
	}

}
