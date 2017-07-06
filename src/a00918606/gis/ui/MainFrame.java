/*
 * Project: Gis
 * File: MainFrame.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class MainFrame that generates the mainframe which contains all the UI components and actions.
*/

package a00918606.gis.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.io.TotalReport;
import a00918606.gis.dao.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(MainFrame.class);

	private PlayerDao playerDao;
	private GameDao gameDao;
	private PersonaDao personaDao;
	private ScoreDao scoreDao;
	private boolean selected;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public MainFrame(PlayerDao pl, GameDao gd, PersonaDao pd, ScoreDao sd) throws Exception {
		this.playerDao = pl;
		this.gameDao = gd;
		this.personaDao = pd;
		this.scoreDao = sd;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnNewMenu);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setMnemonic(KeyEvent.VK_Q);
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(-1);
			}
		});
		mnNewMenu.add(mntmQuit);

		JMenu mnNewMenu_1 = new JMenu("Lists");
		mnNewMenu_1.setMnemonic(KeyEvent.VK_L);
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmPlayers = new JMenuItem("Players");
		mntmPlayers.setMnemonic(KeyEvent.VK_P);
		mntmPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PlayersDialog dialog = new PlayersDialog(playerDao);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnNewMenu_1.add(mntmPlayers);

		JMenuItem mntmPersonas = new JMenuItem("Personas");
		mntmPersonas.setMnemonic(KeyEvent.VK_E);
		mntmPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PersonasDialog dialog = new PersonasDialog(personaDao, playerDao);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnNewMenu_1.add(mntmPersonas);

		JMenuItem mntmScores = new JMenuItem("Scores");
		mntmScores.setMnemonic(KeyEvent.VK_S);
		mntmScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ScoresDialog dialog = new ScoresDialog(scoreDao);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnNewMenu_1.add(mntmScores);

		JMenu mnNewMenu_2 = new JMenu("Reports");
		mnNewMenu_2.setMnemonic(KeyEvent.VK_R);
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.setMnemonic(KeyEvent.VK_T);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalReport tr = new TotalReport(scoreDao, gameDao);
				String report = "";
				try {
					report = tr.generateReport();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(MainFrame.this, report, "Total", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnNewMenu_2.add(mntmTotal);

		JCheckBoxMenuItem chckbxmntmDescending_1 = new JCheckBoxMenuItem("Descending");
		chckbxmntmDescending_1.setMnemonic(KeyEvent.VK_D);
		mnNewMenu_2.add(chckbxmntmDescending_1);

		chckbxmntmDescending_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxmntmDescending_1.getState()) {
					selected = true;
				} else {
					selected = false;
				}
			}
		});

		JMenuItem mntmByGame = new JMenuItem("By Game");
		mntmByGame.setMnemonic(KeyEvent.VK_G);
		mntmByGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportDialogue dialog = new ReportDialogue(playerDao, gameDao, scoreDao, personaDao, selected,
							"game", null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu_2.add(mntmByGame);

		JMenuItem mntmByCount = new JMenuItem("By Count");
		mntmByCount.setMnemonic(KeyEvent.VK_C);
		mntmByCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportDialogue dialog = new ReportDialogue(playerDao, gameDao, scoreDao, personaDao, selected,
							"count", null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu_2.add(mntmByCount);

		JMenuItem mntmGamertag = new JMenuItem("Gamertag");
		mntmGamertag.setMnemonic(KeyEvent.VK_G);
		mntmGamertag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = JOptionPane.showInputDialog("Enter a gamertag");
				ReportDialogue dialog;
				if (value != null) {
					try {
						dialog = new ReportDialogue(playerDao, gameDao, scoreDao, personaDao, selected, "tag", value);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		mnNewMenu_2.add(mntmGamertag);

		JMenu mnNewMenu_3 = new JMenu("Help");
		mnNewMenu_3.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "COMP2613 Assignment 2\nJoseph Ki Jun Jung\nA00918606",
						"About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnNewMenu_3.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
	}

}
