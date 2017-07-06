package a00918606.gis;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.dao.PlayerDao;
import a00918606.gis.data.Player;
import a00918606.gis.data.Score;
import a00918606.gis.data.Database;
import a00918606.gis.data.Game;
import a00918606.gis.dao.*;
import a00918606.gis.data.AllData;
import a00918606.gis.data.Persona;
import a00918606.gis.ui.MainFrame;

/**
 * Project: Gis
 * File: Gis.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Main program that loads data and runs the MainFrame(UI)
 *
 */
public class Gis {

	private static final Logger LOG = LogManager.getLogger(Gis.class);
	private static final String DB_PROPERTIES_FILENAME = "db.properties";
	
	private static AllData allData;
	
	private static List<Game> games;
	private static List<Player> players;
	private static List<Persona> personas;
	private static List<Score> scores;
	
	private static PlayerDao playerDao;
	private static GameDao gameDao;
	private static PersonaDao personaDao;
	private static ScoreDao scoreDao;
	private static Database db;

	/**
	 * Gis Constructor. Processes the commandline arguments
	 * 
	 * @throws ApplicationException
	 */
	public Gis() throws ApplicationException {
		
	}

	/**
	 * Entry point to GIS
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Instant startTime = Instant.now();
		LOG.info(startTime);
		
		// start the Game Information System
		try {
			new Gis().init();
			
		} catch (Exception e) {
			LOG.debug(e.getMessage());
		}
		

		new Gis().createUI();

		
		Instant endTime = Instant.now();
		LOG.info(endTime);
		LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}
	


	
	/**
	 * @throws ApplicationException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 * 
	 */
	private void init() throws ApplicationException, FileNotFoundException, IOException, SQLException {
		LOG.debug("init()");
		
		Properties dbProperties = new Properties();
		dbProperties.load(new FileInputStream(DB_PROPERTIES_FILENAME));
		db = new Database(dbProperties);
		playerDao = new PlayerDao(db);
		gameDao = new GameDao(db);
		personaDao = new PersonaDao(db);
		scoreDao = new ScoreDao(db);
		
		allData = new AllData();
		allData.loadData();
		
		games = allData.getGames();
		personas = allData.getPersonas();
		players = allData.getPlayers();
		scores = allData.getScores();
		
		if (!Database.tableExists(PlayerDao.TABLE_NAME)) {
			playerDao.create();
			gameDao.create();
			personaDao.create();
			scoreDao.create();
			
			loadData();
		}
		
		
	}
		
	private void loadData() throws ApplicationException, SQLException {
		LOG.debug("loadData()");
		System.out.println("loadData");
		
		for (Game game : games) {
			gameDao.add(game);
		}
		
		for (Player player : players) {
			playerDao.add(player);
		}
		
		for (Persona persona : personas) {
			personaDao.add(persona);
		}
		
		for (Score score : scores) {
			scoreDao.add(score);
		}
		
		
		
		
	}
		

	
	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, use the default.
		}

	}
	
	public void createUI() {
		setLookAndFeel();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mainFrame = new MainFrame(playerDao, gameDao, personaDao, scoreDao);
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
