package mettingHouse.model;

import java.util.Random ;
import java.util.ArrayList;

import mettingHouse.model.boardElement.Room;

public class StandardBoardGenerator implements BoardGenerator {


	@Override
	public Board generate(int nbRoom) {
		Random genran = new Random() ;
		boolean vertical = genran.nextBoolean() ;
		Board board = new Board() ;
        Room room = new Room( vertical ) ;
        board.placeRoom( room, 32, 32 ) ;

        nbRoom -- ;
        for (int i = 0; i < nbRoom; i++) {
            ArrayList<Rect> listFreePlace = board.getFreePlaces() ;
            Rect new_place = listFreePlace.get( genran.nextInt( listFreePlace.size() ) ) ;
            Room new_room = new Room( new_place.h > new_place.w ) ;
            board.placeRoom( new_room, new_place.x, new_place.y );
		}

		return board ;
	}

	@Override
	public String toString() {
		return "Standard";
	}

}
