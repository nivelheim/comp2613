/**
 * Project: Gis
 * File: PersonaReader.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class PersonaReader that reads data from input file
 */
package a00918606.gis.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.ApplicationException;
import a00918606.gis.data.Persona;


public class PersonaReader extends Reader {

	public static final String FILENAME = "personas.dat";
	private static final Logger LOG = LogManager.getLogger(PersonaReader.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private PersonaReader() {
	}

	/**
	 * Read the persona input data.
	 * 
	 * @param data
	 *            The input data.
	 * @return A collection of personas.
	 * @throws ApplicationException
	 */
	public static List<Persona> read() throws ApplicationException {
		File file = new File(FILENAME);
		LOG.debug("Reading" + file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e);
		}

		List<Persona> personas = new ArrayList<>();
		try {
			// read past the first row
			if (scanner.hasNext()) {
				scanner.nextLine();
			}
			while (scanner.hasNext()) {
				String row = scanner.nextLine();
				Persona persona = new Persona();
				String[] elements = getElements(row, persona);

				int index = 0;
				persona.setId(Long.parseLong(elements[index++]));
				persona.setPlayerId(Long.parseLong(elements[index++]));
				persona.setGamerTag(elements[index++]);
				persona.setPlatform(elements[index++]);

				personas.add(persona);
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		return personas;
	}

}
