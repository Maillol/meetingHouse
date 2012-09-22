/**
 * Gérer les contraintes de placement des Room entre elles.
 */

package mettingHouse.model;

import java.util.ArrayList ;
import javax.swing.event.EventListenerList;

import mettingHouse.model.Rect;
import mettingHouse.model.boardElement.Piece;
import mettingHouse.model.boardElement.Room;


public class Board {

    private final EventListenerList listeners = new EventListenerList();
	private ArrayList<Room> rooms_placed ;
	private ArrayList<Piece> pieceRemoved ;
	private int min_x ;
	private int min_y ;
	private Piece  survivor;
	
	public Board() {
		rooms_placed = new ArrayList<Room>() ;
		pieceRemoved = new ArrayList<Piece>() ;
	}

	
	public ArrayList<Piece> getPieceRemoved(){
		return pieceRemoved ;
	}
	
	/**
	 * Retourne la liste des piece en place
	 * @return
	 */
	public ArrayList<Piece> getPiecesPlaced() {
		ArrayList<Piece> pieces = new ArrayList<Piece>() ;
		for ( Room room : rooms_placed ) {
			pieces.addAll( room.getPieces() );
		}
		return pieces ;
	}

	public ArrayList<Piece>getRemovedPiece() {
		return (ArrayList<Piece>) pieceRemoved.clone() ;
		
	}
	
	public Piece getSurvivor() {
		return survivor ;
	}
	
	public boolean removePiece( Piece p ) {
		if ( p.isLocked() ) {
			pieceRemoved.add( p ) ;

			p.getRoom().delPiece(p) ;
			p.setRoom( null ) ;

			firePieceRemoved( p ) ;
			p.getPlayer().getPieces().remove(p) ;
			if( getPiecesPlaced().size() == 1 ) { 
				survivor = getPiecesPlaced().get(0) ;
				fireOnePiece( survivor ) ;
				return true ;
			}
		}
		return false ;
	}
	
    public boolean movePiece( Piece p, Room r ) {
		if ( ! p.isLocked() && p.posibleMove().contains( r ) ) {
			Room old_room = p.getRoom() ;
			old_room.delPiece(p) ;
			p.setRoom( r ) ;
			r.addPiece( p ) ;
			
			firePieceMoved( p, old_room ) ;
		}
		return false ;
	}


	public void addBoardListener(BoardListener listener) {
        listeners.add(BoardListener.class, listener);
    }
	
	protected void firePieceMoved( Piece p, Room r ) {
		for ( BoardListener bl : listeners.getListeners( BoardListener.class ) ) {
			bl.piecesMoved( p, r ) ;
		}
	}

	protected void firePieceRemoved( Piece p ) {
		for ( BoardListener bl : listeners.getListeners( BoardListener.class ) ) {
			bl.piecesRemoved( p ) ;
		}
	}

	protected void fireOnePiece( Piece p ) {
		for ( BoardListener bl : listeners.getListeners( BoardListener.class ) ) {
			bl.onePiece( p ) ;
		}
	}
	
	public void centerRoomsPosition() {
		for (Room room_placed : rooms_placed) {
			room_placed.rect.x -= min_x ;
			room_placed.rect.y -= min_y ;
		}
		min_x = min_y = 0 ;
	}

	/**
	 * Return true si l'emplacement données n'est pas en collision avec d'autre
	 * emplacement déjà placée.
	 * @param rect
	 * @return
	 */
	public boolean isFreePlace( Rect rect ) {
		for (Room room_placed : rooms_placed) {
			if ( rect.colize( room_placed.rect ) )
				return false ;
		}
		return true ;
	}

	/**
	 * Calcul tous les emplacements disponibles mitoyen avec les 
	 * Room déjà placées.
	 * @return
	 */
	public ArrayList<Rect> getFreePlaces() {
		ArrayList<Rect> freePlaces = new ArrayList<Rect>() ;
		Rect tmp ;
		for (Room room_placed : rooms_placed) {
			if ( room_placed.getN() == null ) {
				tmp = getNRect( room_placed, true ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
				tmp = getNRect( room_placed, false ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
			}
	
			if ( room_placed.getS() == null ) {
				tmp = getSRect( room_placed, true ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
				tmp = getSRect( room_placed, false ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
			}
	
			if ( room_placed.getE() == null ) {
				tmp = getERect( room_placed, true ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
				tmp = getERect( room_placed, false ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
			}
			
			if ( room_placed.getW() == null ) {
				tmp = getWRect( room_placed, true ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
				tmp = getWRect( room_placed, false ) ;
				if ( isFreePlace( tmp ) )
						freePlaces.add( tmp ) ;
			}
		}
	
		return freePlaces ;
	}

	public ArrayList<Room> getRoomsPlaced() {
		return this.rooms_placed ;
	}

	public static Rect getNRect( Room room, boolean vertical )
	{
		if ( room.isVertical() ) {
			if ( vertical )
				return new Rect( room.rect.x, room.rect.y - 4, 2, 4 ) ; 
			else 
				return new Rect( room.rect.x -1, room.rect.y - 2, 4, 2 ) ; 
		}
		
		else {
			if ( vertical )
				return new Rect( room.rect.x + 1, room.rect.y - 4, 2, 4 ) ;
			else 
				return new Rect( room.rect.x, room.rect.y - 2, 4, 2 ) ;
		}
	}

	public static Rect getSRect( Room room, boolean vertical )
	{
		if ( room.isVertical() ) {
			if ( vertical )
				return new Rect( room.rect.x, room.rect.y + 4, 2, 4 ) ; 
			else 
				return new Rect( room.rect.x -1, room.rect.y + 4, 4, 2 ) ; 
		}
		
		else {
			if ( vertical )
				return new Rect( room.rect.x + 1, room.rect.y + 2, 2, 4 ) ;
			else 
				return new Rect( room.rect.x, room.rect.y + 2, 4, 2 ) ;
		}
	}
	
	public static Rect getERect( Room room, boolean vertical )
	{
		if ( room.isVertical() ) {
			if ( vertical )
				return new Rect( room.rect.x -2, room.rect.y, 2, 4 ) ; 
			else 
				return new Rect( room.rect.x -4, room.rect.y + 1, 4, 2 ) ; 
		}
		
		else {
			if ( vertical )
				return new Rect( room.rect.x -2, room.rect.y -1, 2, 4 ) ;
			else 
				return new Rect( room.rect.x -4, room.rect.y, 4, 2 ) ;
		}
	}

	public static Rect getWRect( Room room, boolean vertical )
	{
		if ( room.isVertical() ) {
			if ( vertical )
				return new Rect( room.rect.x +2, room.rect.y, 2, 4 ) ; 
			else 
				return new Rect( room.rect.x +2, room.rect.y + 1, 4, 2 ) ; 
		} 		
		else {
			if ( vertical )
				return new Rect( room.rect.x +4, room.rect.y -1, 2, 4 ) ;
			else
				return new Rect( room.rect.x +4, room.rect.y, 4, 2 ) ;
		}
	}

	public Room placeRoom( int x, int y, boolean vertical ) {
		Room room = new Room( vertical ) ;
		room.rect.setPosition(x, y) ;
		if ( isFreePlace( room.rect ) ) {
			if ( rooms_placed.size() == 0 ) {
				min_x = x ;
				min_y = y ;
			} else {
				if ( x <  min_x )
					min_x = x ;
				if ( y < min_y )
					min_y = y ;
			}	
			rooms_placed.add( room ) ;
			for (Room room_placed : rooms_placed) {
				int num_gate = room.rect.connected( room_placed.rect ) ;
				if ( num_gate > -1 )
					room.join( room_placed, num_gate ) ;
			}
			return room;
		}
		return null ;
	}
	
	public Room placeRoom( Rect place ) {
		int x = place.x ;
		int y = place.y ;
		Room room = new Room( place.w < place.h ) ;
		room.rect.setPosition( x, y) ;
		if ( isFreePlace( room.rect ) ) {
			if ( rooms_placed.size() == 0 ) {
				min_x = x ;
				min_y = y ;
			} else {
				if ( x <  min_x )
					min_x = x ;
				if ( y < min_y )
					min_y = y ;
			}	
			rooms_placed.add( room ) ;
			for (Room room_placed : rooms_placed) {
				int num_gate = room.rect.connected( room_placed.rect ) ;
				if ( num_gate > -1 )
					room.join( room_placed, num_gate ) ;
			}
			return room;
		}
		return null ;
	}
	
	public void placeRoom( Room room, int x, int y ) {
		room.rect.setPosition(x, y) ;
		if ( isFreePlace( room.rect ) ) {
			if ( rooms_placed.size() == 0 ) {
				min_x = x ;
				min_y = y ;
			} else {
				if ( x <  min_x )
					min_x = x ;
				if ( y < min_y )
					min_y = y ;
			}	
			rooms_placed.add( room ) ;
			for (Room room_placed : rooms_placed) {
				int num_gate = room.rect.connected( room_placed.rect ) ;
				if ( num_gate > -1 )
					room.join( room_placed, num_gate ) ;
			}
		}
	}
	
	
	
}