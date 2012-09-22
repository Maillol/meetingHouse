package mettingHouse.ctrl;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mettingHouse.model.Game;
import mettingHouse.model.Player;
import mettingHouse.model.PlayerWithAI;
import mettingHouse.model.boardElement.Piece;
import mettingHouse.view.PieceDrawable;
import mettingHouse.view.RoomDrawable;
import mettingHouse.view.RoundView;
import mettingHouse.view.TopWindows;

public class RoundCtrl implements MouseListener {
	private TopWindows topView ;
	private RoundView view ;
	private Game game ;
	private Piece pieceSelected ;
	private boolean enableListener = false ;
	
	public RoundCtrl ( Game game, TopWindows topView ) {
		this.game = game ;
		this.topView = topView ;
		view = topView.getRoundView() ; 
		pieceSelected = null ;
		topView.addRoundViewListener(this) ;
	}
	
	
	public void start() {
		game.start() ;
		topView.showRoundView() ;
		nextTurn() ;
	}


	private void nextTurn() {
		enableListener = false ;
		Player p = game.nextPlayer() ;
		view.showMessage( p + "'s turn" ) ;
		while ( p instanceof PlayerWithAI ) {
            try {
            	Thread.sleep(1000);
            }
            catch (InterruptedException e)  {
            	e.printStackTrace() ;
            }
            
            boolean endOfRound = ((PlayerWithAI) p).play() ;

            try {
            	Thread.sleep(500);
            }
            catch (InterruptedException e)  {
            	e.printStackTrace() ;
            }

			if ( endOfRound ) {
				game.updateScore() ;
				topView.showScoreView() ; 
				break ;
			} 
			p = game.nextPlayer() ;
			view.showMessage( p + "'s turn" ) ;
		}
		enableListener = true ;
	}
	
	@Override
	public void mouseClicked(final MouseEvent arg0) {
		if ( enableListener ) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					if ( arg0.getButton() == 1 ) {
						Point point = arg0.getPoint() ;
						boolean flag = true ;
						for ( RoomDrawable room : view.getRoomDrawable() ) {
							if( room.getRectangle().contains( point ) ) {
								for ( PieceDrawable piece : room.getPieces() ) {
									if ( piece.getRectangle().contains( point ) ) {
										pieceSelected = piece.getPiece() ;
										flag = false ;
										break ;
									}
								}
	
								if ( flag && pieceSelected != null &&
									pieceSelected.posibleMove().contains( room.getRoom() )  ) {
									game.getBoard().movePiece( pieceSelected, room.getRoom() ) ;
									pieceSelected = null ;
									nextTurn() ;
								}
								
								break ;
							} 
						}
						
					} else if ( arg0.getButton() == 3 ) {
						if ( pieceSelected != null && pieceSelected.isLocked() ) {
							boolean endOfRound = game.getBoard().removePiece( pieceSelected ) ;
							pieceSelected = null ;
							if ( endOfRound ) {
								game.updateScore() ;
								topView.showScoreView() ;
							} 
							else {
								nextTurn() ;
							}
						}
					}
					
				}
			} ).start() ;
		}
	}

	/**
	 * No implemented
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	/**
	 * No implemented
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {	}

	/**
	 * No implemented
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {	}

	/**
	 * No implemented
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) { }

}
