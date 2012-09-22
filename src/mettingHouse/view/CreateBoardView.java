package mettingHouse.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.EventListenerList;

import mettingHouse.model.BoardGenerator;
import mettingHouse.model.LinearBoardGenerator;
import mettingHouse.model.StandardBoardGenerator;


public class CreateBoardView extends JPanel implements ActionListener {

	private static BoardGenerator[] BOARDGENERATORS = {
		new StandardBoardGenerator(),
		new LinearBoardGenerator()
	} ;
	
	
    private final EventListenerList listeners = new EventListenerList();
	private JLabel numberOfRoomLabel = new JLabel( "Number of room") ;
	private JLabel houseTypeLabel    = new JLabel( "House Type") ;
	private SpinnerNumberModel numberOfRoomModel = new SpinnerNumberModel(8, 2, 80, 1);
	private JSpinner numberOfRoomField = new JSpinner( numberOfRoomModel ) ;
	private JComboBox<BoardGenerator> houseTypeComboBox  = new JComboBox<BoardGenerator>() ;
	private JButton validateButton  = new JButton( "Ok" ) ;
	
	
	public CreateBoardView(){
		super( new GridBagLayout() );
		
		validateButton.addActionListener(this) ;
		
		for ( BoardGenerator generator : BOARDGENERATORS ) {
			houseTypeComboBox.addItem( generator ) ;
		}

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH ;

		c.gridy = 0 ;
		c.gridx = 0 ; 
		add( numberOfRoomLabel, c ) ;
		
		c.gridx = 1 ;
		add( numberOfRoomField, c ) ;
		
		c.gridy = 1 ;
		c.gridx = 0 ;
		add( houseTypeLabel, c ) ;
		
		c.gridx = 1 ;
		add( houseTypeComboBox, c ) ;
		
		c.gridx = 2 ;
		add( validateButton, c ) ;
	}
	

    public CreateBoardListener[] getCreateBoardListeners() {
        return listeners.getListeners(CreateBoardListener.class);
    }
	
	public void addCreateBoardListener( CreateBoardListener listener) {
        listeners.add( CreateBoardListener.class, listener);
    }
	
    
	@Override
	public void actionPerformed(ActionEvent event ) {
		int nbRoom =  numberOfRoomModel.getNumber().intValue() ;
		for ( CreateBoardListener listener : getCreateBoardListeners() ) {
			BoardGenerator houseType = (BoardGenerator) houseTypeComboBox.getSelectedItem() ;
			listener.createBoard( new CreateBoardEvent(nbRoom, houseType) );
		}
	}
	
}
