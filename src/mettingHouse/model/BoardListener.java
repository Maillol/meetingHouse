package mettingHouse.model;

import java.util.EventListener;

import mettingHouse.model.boardElement.Piece;
import mettingHouse.model.boardElement.Room;

public interface BoardListener extends EventListener {
	public void piecesRemoved( Piece p );
	public void piecesMoved( Piece p, Room from );
	public void onePiece( Piece p );
}
