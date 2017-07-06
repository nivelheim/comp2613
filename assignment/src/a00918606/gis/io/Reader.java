/*
 * Project: Gis
 * File: Reader.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class Reader, which is a superclass of all Reader classes
*/

package a00918606.gis.io;

import java.util.Arrays;

import a00918606.gis.ApplicationException;
import a00918606.gis.data.GisData;

public class Reader {

	public static final String RECORD_DELIMITER = ":";
	public static final String FIELD_DELIMITER = "\\|";

	/**
	 * @param row
	 * @return
	 * @throws ApplicationException
	 */
	public static String[] getElements(String row, GisData gisData) throws ApplicationException {
		String[] elements = row.split(FIELD_DELIMITER);
		if (elements.length != gisData.getAttributeCount()) {
			throw new ApplicationException(String.format("Expected %d but got %d: %s", gisData.getAttributeCount(),
					elements.length, Arrays.toString(elements)));
		}

		return elements;
	}
}
