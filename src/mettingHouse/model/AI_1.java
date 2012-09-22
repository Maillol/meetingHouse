package mettingHouse.model;

import java.util.ArrayList;
import java.util.Random;

import mettingHouse.model.boardElement.Piece;

public class AI_1 extends AI {


	
	@Override
	public boolean play() {
		//Tuer les autre piece qui bloque ces pieces 
		for ( Piece p : player.getPieces() ) {
			if ( p.isLocked() ) {
				ArrayList<Piece> otherPieces = player.getOtherPieceInRoom( p.getRoom() ) ;
				if ( otherPieces.size() > 0 ) {
					return board.removePiece( otherPieces.get( 0 ) ) ;
				}
			}
		}
		
		// Aucune piece enemie ne bloque ces pieces prendre une piece au hasard.
		ArrayList<Piece> otherPieces = player.getAllOtherPiece() ;
		if ( otherPieces.size() > 0 ) {
			int rnd = new Random().nextInt( player.getAllOtherPiece().size() );
			Piece pieceSelected = otherPieces.get(rnd);
		
			if ( pieceSelected.isLocked() ) {
				return board.removePiece( pieceSelected ) ;
			} else {
				return board.movePiece( pieceSelected, pieceSelected.posibleMove().get( 0 ) ) ;
			} 
		
		// Il ne reste plus que ses pieces.
		} else {
			int rnd = new Random().nextInt( player.getPieces().size() );
			Piece pieceSelected = player.getPieces().get( rnd );
			
			if ( pieceSelected.isLocked() ) {
				return board.removePiece( pieceSelected ) ;
			} else {
				return board.movePiece( pieceSelected, pieceSelected.posibleMove().get( 0 ) ) ;
			} 
		}
	}
	

	@Override
	public String toString() {
		return "Standard" ;
	}

}
