/**
 * Project: Gis
 * File: GameReader.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class GameReader that reads data from input file
 */

package a00918606.gis.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.ApplicationException;
import a00918606.gis.data.Game;


public class GameReader extends Reader {

	public static final String FILENAME = "games.dat";
	private static final Logger LOG = LogManager.getLogger(GameReader.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private GameReader() {
	}

	/**
	 * Read the game input data.
	 * 
	 * @param data
	 *            The input data.
	 * @return A collection of games.
	 * @throws ApplicationException
	 */
	public static List<Game> read() throws ApplicationException {
		File file = new File(FILENAME);
		LOG.debug("Reading" + file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e);
		}

		List<Game> games = new ArrayList<>();
		try {
			// reader past the first row
			if (scanner.hasNext()) {
				scanner.nextLine();
			}
			while (scanner.hasNext()) {
				String row = scanner.nextLine();
				Game game = new Game();
				String[] elements = getElements(row, game);

				int index = 0;
				game.setId(elements[index++]);
				game.setName(elements[index++]);
				game.setProducer(elements[index++]);

				games.add(game);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		return games;
	}
}
