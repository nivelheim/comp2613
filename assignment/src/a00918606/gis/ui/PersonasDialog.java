/*
 * Project: Gis
 * File: PersonasDialogue.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class PersonasDialogue that displays list of personas
*/


package a00918606.gis.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.data.Persona;
import a00918606.gis.dao.PersonaDao;
import a00918606.gis.dao.PlayerDao;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

@SuppressWarnings("serial")
public class PersonasDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<PersonaListItem> personasList;
	private DefaultListModel<PersonaListItem> listModel;
	private PersonaDao personaDao;
	private PlayerDao playerDao;
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(PersonasDialog.class);

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public PersonasDialog(PersonaDao pd, PlayerDao pl) throws SQLException {
		personaDao = pd;
		playerDao = pl;
		listModel = new DefaultListModel<PersonaListItem>();
		personasList = new JList<PersonaListItem>(listModel);
		JScrollPane scrollPane = new JScrollPane();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[332px][grow]", "[204px,grow]"));
		{
			contentPanel.add(scrollPane, "cell 0 0,grow");
			scrollPane.setViewportView(personasList);
		}
		
		// set the data
		List<String> gamerTags = personaDao.getGamertags();
		for (int i=0; i<=gamerTags.size(); i++) {
			try {
				Persona persona = personaDao.getPersona(i);
				PersonaListItem item = new PersonaListItem(persona);
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
		
		
		personasList.addMouseListener(new ActionJList<PersonaListItem>(personasList));
		
	}
	
	@SuppressWarnings("hiding")
	class ActionJList<PersonaListItem> extends MouseAdapter{
		public JList<PersonaListItem> list;
		    
		public ActionJList(JList<PersonaListItem> l){
			list = l;
		 }
		    
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				int index = list.locationToIndex(e.getPoint());
				ListModel<PersonaListItem> dlm = list.getModel();
				PersonaListItem item = dlm.getElementAt(index);
				list.ensureIndexIsVisible(index);
				System.out.println("Double clicked on " + item);
				
				
				PersonaSpecific dialog;
				try {
					dialog = new PersonaSpecific((a00918606.gis.ui.PersonaListItem) item, playerDao, personaDao);
					dialog.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		     }
		   }
		}
	

}
