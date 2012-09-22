package mettingHouse.model;

import java.util.ArrayList;

import mettingHouse.model.boardElement.Piece;
import mettingHouse.model.boardElement.Room;


/**
 * This class has created in order to implemente PlayerWithIA
 * You must define the <g>run</g> method. This method whill contain 
 * IA'actions. You can call <i>getAllOtherPiece</i>, <i>getOtherPieceInRoom</i>, and
 * <i>getPieces</i> in <g>run</g> method. When <g>run</g> method call <i>board.remove()</i> or 
 * <i>board.move</i> she must return immediatly call value. 
 *
 * getType method must return user friendly name of IA.
 * @author maillol
 *
 */
public class PlayerWithAI extends Player {
	private Board board ;
	private AI ai; 

	public  PlayerWithAI(String name, AI ai ) {
		super( name ) ;
		this.ai = ai ;
	}

	/**
	 * put board to PlayerWithAI.
	 * @param board
	 */
	public void setBoard( Board board ) {
		this.board = board ;
	}
	
	/**
	 * Retourne la liste de toute les pièces des autres joueurs
	 * @return
	 */
	public ArrayList<Piece> getAllOtherPiece() {
		ArrayList<Piece> r  = new ArrayList<Piece>() ;
		for ( Piece p : board.getPiecesPlaced() ) {
			if ( p.getPlayer() != this ) {
				r.add( p ) ;
			}
		}
		return r ;
	}
	
	/**
	 * Retourne la liste de toute les pieces des autre joueur dans la piece 
	 * Donnée
	 * @return
	 */
	public ArrayList<Piece> getOtherPieceInRoom( Room room ) {
		ArrayList<Piece> r  = room.getPieces() ;
		r.removeAll( getPieces() );
		return r ;
	}
	
	
	/**
	 * Joue un tour.
	 * @return la valeur des apelle à board.remove( Piece p ) ou à board.move( Piece p ).
	 */
	public boolean play() {
		ai.setContext(this, board) ;
		return ai.play() ;
	}

}
