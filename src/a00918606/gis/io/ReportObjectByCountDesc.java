/*
 * Project: Gis
 * File: ReportObjectByCountDesc.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class ReportObjectByCountDesc that contains comparator to sort objects by count in descending order
*/
package a00918606.gis.io;

import java.util.Comparator;

import a00918606.gis.data.ReportObject;


public class ReportObjectByCountDesc implements Comparator<ReportObject> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ReportObject o1, ReportObject o2) {
		return o2.getTotalGame() - o1.getTotalGame();
	}

}
