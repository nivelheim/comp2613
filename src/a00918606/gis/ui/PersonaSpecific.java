/*
 * Project: Gis
 * File: PersonaSpecific.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class PersonaSpecific that displays the specific information about the gamertag that is double clicked
*/

package a00918606.gis.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00918606.gis.dao.PersonaDao;
import a00918606.gis.dao.PlayerDao;
import a00918606.gis.data.Persona;
import a00918606.gis.data.Player;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PersonaSpecific extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField firsNameField;
	private JTextField lastNameField;
	private JTextField emailAddressField;
	private JTextField personaField;

	private Persona persona;
	private Player player;
	private PlayerDao playerDao;
	private PersonaListItem item;
	private List<String> lastNames;
	private PersonaDao personaDao;

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public PersonaSpecific(PersonaListItem item, PlayerDao pl, PersonaDao pd) throws Exception {
		
		personaDao = pd;
		initialize(item, pl);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][][][][][]"));
		{
			idField = new JTextField();
			contentPanel.add(idField, "cell 2 1,growx");
			idField.setColumns(10);
		}
		
		{
			firsNameField = new JTextField();
			contentPanel.add(firsNameField, "cell 2 2,growx");
			firsNameField.setColumns(10);
		}
		{
			lastNameField = new JTextField();
			contentPanel.add(lastNameField, "cell 2 3,growx");
			lastNameField.setColumns(10);
		}
		{
			emailAddressField = new JTextField();
			contentPanel.add(emailAddressField, "cell 2 4,growx");
			emailAddressField.setColumns(10);
		}
		{
			personaField = new JTextField();
			contentPanel.add(personaField, "cell 2 5,growx");
			personaField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updatePlayer();
						try {
							playerDao.update(player);
							PersonaSpecific.this.dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PersonaSpecific.this.player = null;
						PersonaSpecific.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setFields(player);
	}
	
	private void initialize(PersonaListItem it, PlayerDao pl) throws Exception {	
		this.item = it;
		persona = item.getPersona();
		playerDao = pl;
		lastNames = pl.getLastNames();
		
		for (int i=0; i<lastNames.size(); i++) {
			Player p = playerDao.getPlayer(lastNames.get(i));
			long a = p.getId();
			long b = persona.getPlayerId();
			
			if (b == a) {
				player = p;
			}
			
			else {
				//
			}
		}
	}
	
	protected void updatePlayer() {
		player.setFirstName(firsNameField.getText());
		player.setLastName(lastNameField.getText());
		player.setEmailAddress(emailAddressField.getText());
	}

	/**
	 * @param persona
	 * @throws Exception 
	 */
	private void setFields(Player p) throws Exception {
		Player player = new Player();
		player = p;
		String pf = new String();
		
		idField.setText(Long.toString(player.getId()));
		firsNameField.setText(player.getFirstName());
		lastNameField.setText(player.getLastName());
		emailAddressField.setText(player.getEmailAddress());
		
		
		List<String> tags = personaDao.getGamertags();
		List<Persona> personas = new ArrayList<Persona>();
		List<Persona> playerPersonas = new ArrayList<Persona>();
		
		for (String tag : tags) {
			personas.add(personaDao.getPersonaByTag(tag));
		}
		
		for (Persona psn : personas) {
			if (psn.getId() == player.getId()) {
				playerPersonas.add(psn);
			}
		}
		
		player.setPersonaList(playerPersonas);
		for (Persona pp : playerPersonas) {
			pf.concat(pp.getGamerTag());
		}

		personaField.setText(pf);
	}
	
	
}




