package mettingHouse.model;

import java.util.Random ;
import java.util.ArrayList;

import mettingHouse.model.boardElement.Piece;

public class PM_1 extends PlayerWithAI {

	public PM_1(String name) {
		super(name);
	}

	@Override
	public boolean run() {
		//Tuer les autre piece qui bloque ces pieces 
		for ( Piece p : getPieces() ) {
			if ( p.isLocked() ) {
				ArrayList<Piece> otherPieces = getOtherPieceInRoom( p.getRoom() ) ;
				if ( otherPieces.size() > 0 ) {
					return board.removePiece( otherPieces.get( 0 ) ) ;
				}
			}
		}
		
		// Aucune piece enemie ne bloque ces pieces prendre une piece au hasard.
		ArrayList<Piece> otherPieces = getAllOtherPiece() ;
		if ( otherPieces.size() > 0 ) {
			int rnd = new Random().nextInt( getAllOtherPiece().size() );
			Piece pieceSelected = otherPieces.get(rnd);
		
			if ( pieceSelected.isLocked() ) {
				return board.removePiece( pieceSelected ) ;
			} else {
				return board.movePiece( pieceSelected, pieceSelected.posibleMove().get( 0 ) ) ;
			} 
		
		// Il ne reste plus que ses pieces.
		} else {
			int rnd = new Random().nextInt( getPieces().size() );
			Piece pieceSelected = getPieces().get( rnd );
			
			if ( pieceSelected.isLocked() ) {
				return board.removePiece( pieceSelected ) ;
			} else {
				return board.movePiece( pieceSelected, pieceSelected.posibleMove().get( 0 ) ) ;
			} 
		}
		
	}

	@Override
	public String getType() {
		return "PM_1";
	}


}
