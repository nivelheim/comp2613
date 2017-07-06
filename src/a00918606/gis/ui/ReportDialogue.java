/*
 * Project: Gis
 * File: ReportDialgue.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class ReportDialgue that displays the report in different orders.
 */

package a00918606.gis.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00918606.gis.dao.*;
import a00918606.gis.data.ReportObject;
import a00918606.gis.io.ReportObjectByCount;
import a00918606.gis.io.ReportObjectByCountDesc;
import a00918606.gis.io.ReportObjectByGame;
import a00918606.gis.io.ReportObjectByGameDesc;
import a00918606.gis.io.ReportObjectByTag;
import a00918606.gis.io.ReportObjectByTagDesc;

import a00918606.gis.io.PlayersReport;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ReportDialogue extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PlayersReport pr;
	private List<ReportObject> objects;

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public ReportDialogue(PlayerDao playerDao, GameDao gameDao, ScoreDao scoreDao, 
			PersonaDao personaDao, boolean s, String type, String tag) throws Exception {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextArea textArea = new JTextArea();
			
			
			
			textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
				
			pr = new PlayersReport(playerDao, gameDao, scoreDao, personaDao);
			objects = pr.returnArray();
			
			if (type == "game") {
				if (s == true) {
					Collections.sort(objects, new ReportObjectByGameDesc());
				}
				
				else {
					Collections.sort(objects, new ReportObjectByGame());
				}
			
				for (ReportObject o : objects) {
					textArea.append(String.format("%-14s %-5s %-30s %-5d %-5d", o.getGameName(), o.getPlatform(), o.getGamerTag(),
							o.getWin(), o.getLoss()) + "\n");
				}
			}
			
			else if (type == "count") {
				if (s == true) {
					Collections.sort(objects, new ReportObjectByCountDesc());
				}
				
				else {
					Collections.sort(objects, new ReportObjectByCount());
				}
				
				for (ReportObject o : objects) {
					textArea.append(String.format("%-14s %-5s %-30s %-5d %-5d", o.getGameName(), o.getPlatform(), o.getGamerTag(),
							o.getWin(), o.getLoss()) + "\n");
				}
			}
			
			else if (type == "tag") {
				List<ReportObject> result = new ArrayList<ReportObject>();
				if (s == true) {
					if (tag.equals("")) {
						for (ReportObject o : objects) {
							result.add(o);
						}
					}
					
					else {
						for (ReportObject o : objects) {
							if (o.getGamerTag().equals(tag)) {
								result.add(o);
							}
	
						}	
					}
					Collections.sort(result, new ReportObjectByTagDesc());
				}	
				
				else {
					if (tag.equals("")) {
						for (ReportObject o : objects) {
							result.add(o);
						}
					}
					
					else {
						for (ReportObject o : objects) {
							if (o.getGamerTag().equals(tag)) {
								result.add(o);
							}

						}	
					}
					Collections.sort(result, new ReportObjectByTag());
				}
				
				for (ReportObject o : result) {
					textArea.append(String.format("%-14s %-5s %-30s %-5d %-5d", o.getGameName(), o.getPlatform(), o.getGamerTag(),
							o.getWin(), o.getLoss()) + "\n");

				}
			}
			
			
			
			JScrollPane scrollPane = new JScrollPane(textArea);
			contentPanel.add(scrollPane);
			scrollPane.setViewportView(textArea);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(400, 250));
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
