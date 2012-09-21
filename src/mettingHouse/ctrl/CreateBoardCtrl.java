package mettingHouse.ctrl;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mettingHouse.model.Game;
import mettingHouse.view.CreateBoardEvent;
import mettingHouse.view.CreateBoardListener;
import mettingHouse.view.PutPlayersEvent;
import mettingHouse.view.PutPlayersListener;
import mettingHouse.view.TopWindows;

public class CreateBoardCtrl implements  CreateBoardListener, PutPlayersListener, ActionListener {

	private Game game; 
	private TopWindows view ;
	private RoundCtrl roundCtrl ;
	
	public CreateBoardCtrl( Game game ) {
		this.game = game ;
				
		TopWindows view = TopWindows.makeInstanceInETD( game ) ;
		roundCtrl = new RoundCtrl( game, view ) ;

		this.view = view ;
		view.addCreateBoardListener( this ) ;
		view.addPutPlayersListener( this ) ;
		view.addScoreListener( this ) ;
		
		view.showPutPlayersView() ;
		view.setVisible(true) ;
	}

	@Override
	public void createBoard(final CreateBoardEvent e) {
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					game.createBoard( e.getNbRoom(), e.getTypeHouse() ) ;
					roundCtrl.start() ;
				}
			}
		).start() ;
	}

	@Override
	public void putPlayer(final PutPlayersEvent event) {
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						game.addPlayer( event.getName(), event.getIa() ) ;
					}
				}
		).start() ;
	}

	@Override
	public void putPlayerClosed( final int endScore ) {
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						game.setEndScore( endScore ) ;
						view.showCreateBoardView() ;
					}
				}
		).start() ;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						if ( game.getWinner() != null ) {
							game.reset() ;
							view.showPutPlayersView() ;
						} else {
							view.showCreateBoardView() ;
						}
					}
				}
		).start() ;
		
	}

	
}
