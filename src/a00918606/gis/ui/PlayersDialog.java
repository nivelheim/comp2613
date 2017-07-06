/*
 * Project: Gis
 * File: PlayersDialogue.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class PlayersDialogue that displays a list of players
 */

package a00918606.gis.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.data.Player;
import a00918606.gis.ui.PlayerListItem;
import a00918606.gis.dao.PlayerDao;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;

@SuppressWarnings("serial")
public class PlayersDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<PlayerListItem> playersList;
	private DefaultListModel<PlayerListItem> listModel;
	private PlayerDao playerDao;
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(PlayersDialog.class);

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public PlayersDialog(PlayerDao pl) throws SQLException {
		playerDao = pl;
		listModel = new DefaultListModel<PlayerListItem>();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[332px]", "[204px]"));
		{
			playersList = new JList<PlayerListItem>(listModel);
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 0,grow");
			scrollPane.setViewportView(playersList);
		}
		
		// set the data
		List<String> lastNames = playerDao.getLastNames();
		for (int i=0; i<lastNames.size(); i++) {
			try {
				Player player = playerDao.getPlayer(lastNames.get(i));
				PlayerListItem item = new PlayerListItem(player);
				listModel.addElement(item);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}
	

}
