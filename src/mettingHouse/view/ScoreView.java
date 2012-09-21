package mettingHouse.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mettingHouse.model.Game;
import mettingHouse.model.Player;

public class ScoreView extends JPanel {

	private GridBagConstraints c = new GridBagConstraints() ;
	private Game game ;
	private JButton nextButton = new JButton( "Next round" ) ;
	
	public ScoreView( Game game ) {
		super( new GridBagLayout() ) ;
		this.game = game ;
	}
	
	public void addNextButtonListener( ActionListener listener ) {
		nextButton.addActionListener( listener ) ;
	}
	
	public void update() {
		removeAll() ;
		c.fill = GridBagConstraints.BOTH ;
		c.ipadx = 5 ;
		c.ipady = 5 ;
		c.gridy = 1 ;
		for (Entry<Player, ArrayList<Integer>> entry : game.getScoreByRound().entrySet() ) {
			c.gridx = 0 ;
			add( new JLabel( entry.getKey().toString() ), c ) ;
			c.gridx++ ;
			for (Integer point : entry.getValue() ) {
				add( new JLabel( point.toString() ), c ) ;
				c.gridx++ ;
			}
			c.gridy++ ;
		}

		if ( game.getWinner() != null ) {
			add( new JLabel( game.getWinner().toString() + " is winner !!!" ), c ) ;
			nextButton.setText( "End Game" ) ;
			c.gridy++ ;
		}
		add( nextButton, c ) ;
		
		int end = c.gridx;
		c.gridy = 0 ;
		for ( c.gridx = 1; c.gridx < end; c.gridx++ ) {
			add( new JLabel( "round " + new Integer( c.gridx ).toString() ), c ) ; 
		}
		
	}
}
