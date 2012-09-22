package mettingHouse.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import mettingHouse.model.Board;
import mettingHouse.model.BoardListener;
import mettingHouse.model.Game;
import mettingHouse.model.PlayerWithAI;
import mettingHouse.model.boardElement.Piece;
import mettingHouse.model.boardElement.Room;
import mettingHouse.view.PieceDrawable;


	public class RoundView extends JPanel {

		private static final long serialVersionUID = 4785096707383293626L;
		
		private BoardViewer boardViewer ;
		private PiecesObserver piecesViewer ;
		private JScrollPane scrollBoardViewer ;
		private JLabel messageLabel = new JLabel( "") ;
		private Game game ;
		
		public RoundView( Game game ) {
			this.setLayout( new BorderLayout() ) ;
			this.game = game ;
			boardViewer  = new BoardViewer() ;
			piecesViewer = new PiecesObserver() ;
			messageLabel.setBackground( Color.white ) ;
			
			piecesViewer.setPreferredSize( new Dimension( 80, 480) ) ;
			piecesViewer.setMinimumSize( new Dimension( 80, 480) ) ;
			boardViewer.setPreferredSize( new Dimension( 560, 480) ) ;
			
			scrollBoardViewer = new JScrollPane( boardViewer ) ;
			scrollBoardViewer.setPreferredSize( new Dimension(560,400) );
			scrollBoardViewer.setMaximumSize( new Dimension(560,400) );
			
			boardViewer.scrollRectToVisible( new Rectangle( 0, 0, 560, 400 ));
			boardViewer.setPreferredSize(    new Dimension(2000,2000) );

			boardViewer.revalidate();
			add( messageLabel, BorderLayout.BEFORE_FIRST_LINE ) ;
			add( scrollBoardViewer, BorderLayout.CENTER ) ;
			add( piecesViewer, BorderLayout.EAST  ) ;
		}
	
		public void addMouseListener( MouseListener listener ) {
			boardViewer.addMouseListener( listener ) ;
		}
	
		public ArrayList<RoomDrawable> getRoomDrawable() {
			return boardViewer.rooms ;
		}

		public void showMessage( final String message ) {

			if ( SwingUtilities.isEventDispatchThread() ) {
				messageLabel.setText(message) ;
			} else {
				SwingUtilities.invokeLater( new Runnable() {
					@Override
					public void run() {
						messageLabel.setText(message) ;
					}
				} ) ;
			}
			messageLabel.revalidate() ;
		}
		
		public void update() {
			Board board = game.getBoard() ;
			if ( board != null ) {
				boardViewer.update( board ) ;
				piecesViewer.update( board ) ;
			}
		}
		
	class PiecesObserver extends JPanel {

		private static final long serialVersionUID = -3695880126013556286L;
		private ArrayList<PieceDrawable> pieces = new ArrayList<PieceDrawable>() ;
		
		public void update( Board board ) {
			removeAll() ;
			int x = -75 ;
			for( Piece p : board.getPiecesPlaced() ) {
				if ( ! (p.getPlayer() instanceof PlayerWithAI) )
					this.pieces.add( PieceDrawable.getPieceDrawable( p, 5, x += 80, this ) ) ;
			}
		}
		
		@Override
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	    	for (PieceDrawable piece : pieces ) {
	    		piece.draw(g) ;
	    	}
		}
				
	} 


	class BoardViewer extends JPanel implements  BoardListener {
	
		private static final long serialVersionUID = 5484522332647952407L;
		
		public ArrayList<RoomDrawable> rooms = new ArrayList<RoomDrawable>() ;

	
		public void update( Board board ) {
			rooms.clear() ;
			for ( Room room : board.getRoomsPlaced() ) {
				rooms.add( new RoomDrawable( room, this ) ) ;
			}
	
			PieceDrawable.forgetInstances() ;
			board.addBoardListener( this ) ;
		}
		
		@Override
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	    	for (RoomDrawable room : rooms ) {
	    		room.draw(g) ;
	    	}
		}
	
	
		@Override
		public void piecesRemoved( Piece p ) {
			SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							repaint() ;
						}
					}
				) ;
		}
	
	
		@Override
		public void piecesMoved( Piece p, Room from ) {
			SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							repaint() ;
						}
					}
				) ;
		}

		@Override
		public void onePiece(Piece p) {
		}
	
	}

} 
