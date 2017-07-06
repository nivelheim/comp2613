/*
 * Project: Gis
 * File: ScoresDialogue.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class ScoresDialogue that generates output list of scores
*/

package a00918606.gis.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.data.Score;
import a00918606.gis.dao.ScoreDao;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings({ "serial", "unused" })
public class ScoresDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<ScoreListItem> scoresList;
	private DefaultListModel<ScoreListItem> listModel;
	private ScoreDao scoreDao;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ScoresDialog(ScoreDao sd) throws SQLException {
		scoreDao = sd;
		listModel = new DefaultListModel<ScoreListItem>();
		
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[332px]", "[204px]"));
		{
			scoresList = new JList<ScoreListItem>(listModel);
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 0,grow");
			scrollPane.setViewportView(scoresList);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(400, 400));
		}
		
		
		
		// set the data
		List<Integer> scores = scoreDao.getPersonaIds();
		List<Score> scoreList;
		for (int i=1; i<=scores.size(); i++) {
			try {
				scoreList = scoreDao.getScores();
				for (int j = 0; j<scoreList.size(); j++) {
					listModel.addElement(new ScoreListItem(scoreList.get(j)));
				}
				
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
