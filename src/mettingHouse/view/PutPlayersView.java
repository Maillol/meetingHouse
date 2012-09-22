package mettingHouse.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.EventListenerList;

import mettingHouse.model.AI;
import mettingHouse.model.AI_1;
import mettingHouse.model.Game;
import mettingHouse.model.GameListener;
import mettingHouse.model.Player;

public class PutPlayersView extends JPanel implements ActionListener, GameListener {

	private static AI[] AIS = {
		new AI_1(),
	} ;
	
	private final EventListenerList listeners  = new EventListenerList();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();;
	private JList<String> playersList          = new JList<String>( listModel ) ;
	private JTextField playerNameField         = new JTextField() ;
	private JComboBox<AI> playerTypeComboBox = new JComboBox<AI>() ;
	private JButton addPlayerButton = new JButton( "add" ) ;
	private JLabel endScoreLabel = new JLabel( "Final score" ) ;
	private JTextField endScoreField = new JTextField( "50" ) ;
	private JButton validateButton  = new JButton( "ok" ) ;
	
	public PutPlayersView( Game game ) {
		super();
		game.addGameListener(this) ;
		playersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		playersList.setLayoutOrientation(JList.VERTICAL);
		playersList.setVisibleRowCount( 5 );		
		JScrollPane listScroller = new JScrollPane(playersList);
		
		
		playerTypeComboBox.addItem( new AI() {
			@Override
			public boolean play() {
				return false;
			}
			
			public String toString() {
				return "human" ;
			}
		} ) ;
		
		for (AI ai : AIS) {
			playerTypeComboBox.addItem( ai ) ;
		}
	
		setLayout( new GridBagLayout() ) ;
		GridBagConstraints c = new GridBagConstraints();
		addPlayerButton.addActionListener(this) ;
		validateButton.addActionListener(this) ;
		
		// Row 1
		c.fill = GridBagConstraints.BOTH ;
		c.anchor = GridBagConstraints.NORTH ;
		c.weightx = 1.0 ;
		c.gridx = 0 ;
		c.gridy = 0 ;
		add( playerNameField, c ) ;

		c.gridx = 1 ;
		c.weightx = 0.5 ;
		add( playerTypeComboBox, c ) ;
		
		c.gridx = 2 ;
		c.weightx = 0.0 ;
		add( addPlayerButton, c ) ;
		
		// Row 2
		c.gridx = 0 ;
		c.gridy = 1 ;
		c.gridwidth = 3 ;
		add( listScroller, c ) ;
		c.gridwidth = 1 ;

		// Row 3
		c.gridx = 0 ;
		c.gridy = 3 ;
		add( endScoreLabel, c ) ;
		
		c.gridx = 1 ;
		c.gridy = 3 ;
		add( endScoreField, c ) ;
		
		
		// Row 4
		c.fill = GridBagConstraints.NONE ;
		c.anchor = GridBagConstraints.EAST ;
		c.gridx = 2 ;
		c.gridy = 3 ;
		add( validateButton, c ) ;
		
	}

	private void firePutPlayersClosed() {
		int endScore = Integer.parseInt( endScoreField.getText() ) ;
		for( PutPlayersListener listener : getPutPlayersListeners() ) {
			 listener.putPlayerClosed( endScore ) ;
        }	
	}
	
	private void firePutPlayers( String name, AI ai ) {
		 for( PutPlayersListener listener : getPutPlayersListeners() ) {
			 listener.putPlayer( new PutPlayersEvent(name, ai) ) ;
         }	
	}
	
    public PutPlayersListener[] getPutPlayersListeners() {
        return listeners.getListeners(PutPlayersListener.class);
    }
	
	public void addPutPlayersListener( PutPlayersListener listener) {
        listeners.add( PutPlayersListener.class, listener);
    }
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if ( event.getSource() == addPlayerButton ) {
			String name = playerNameField.getText() ;
			AI ai = (AI)playerTypeComboBox.getSelectedItem() ;
			playerNameField.setText("");
			playerNameField.requestFocus() ;
			firePutPlayers( name, ai ) ;
		}

		else if  ( event.getSource() == validateButton ) {
			firePutPlayersClosed() ;
		}
		
	}

	@Override
	public void playerAdded(Player player) {
		listModel.addElement( player.getName() + "  [" + player.getType() + "]" ) ;
	}

	@Override
	public void playersCleared() {
		listModel.clear() ;
	}

	@Override
	public void humanPlayerAlreadyExists(Player existingHuman) {
		playerNameField.setText( "Please, one human Player by game." ) ;
	}

	@Override
	public void playerNameAlreadyExists(String name) {
		playerNameField.setText( "Player with name '" + name  + "' already exists." ) ;
		
	}
	
	
}