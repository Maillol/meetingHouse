package mettingHouse.model;

import mettingHouse.model.boardElement.Room;

public class LinearBoardGenerator implements BoardGenerator {

	@Override
	public Board generate(int nbRoom) {
		Board board = new Board() ;
		boolean isVertical = true ;
		Room currentRoom = board.placeRoom(0, 0, isVertical) ;
		nbRoom-- ;
		isVertical = ! isVertical ;

		while ( nbRoom > 0 ) {
			currentRoom = board.placeRoom( Board.getERect( currentRoom, isVertical ) ) ;
			nbRoom-- ;
			isVertical = ! isVertical ;
		}
		return board;
	}

	@Override
	public String toString() {
		return "Linear";
	}
	
}
