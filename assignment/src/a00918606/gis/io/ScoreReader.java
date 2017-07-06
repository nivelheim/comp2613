/*
 * Project: Gis
 * File: ScoreReader.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class ScoreReader that reads scores from input file
*/
package a00918606.gis.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.ApplicationException;
import a00918606.gis.data.Score;

public class ScoreReader extends Reader {

	public static final String FILENAME = "scores.dat";
	private static final Logger LOG = LogManager.getLogger(ScoreReader.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private ScoreReader() {
	}

	/**
	 * Read the game input data.
	 * 
	 * @param data
	 *            The input data.
	 * @return A collection of games.
	 * @throws ApplicationException
	 */
	public static List<Score> read() throws ApplicationException {
		File file = new File(FILENAME);
		LOG.debug("Reading" + file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e);
		}

		List<Score> scores = new ArrayList<>();
		try {
			// reader past the first row
			if (scanner.hasNext()) {
				scanner.nextLine();
			}
			while (scanner.hasNext()) {
				String row = scanner.nextLine();
				Score score = new Score();
				String[] elements = getElements(row, score);

				int index = 0;
				score.setPersonaId(Long.parseLong(elements[index++]));
				score.setGameId(elements[index++]);
				score.setWin(Score.WIN.equals(elements[index++]));

				scores.add(score);
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		return scores;
	}

}
