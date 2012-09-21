package mettingHouse.model;

/**
 * Strategie pour positionner un ensemble de Room
 * retourne une Board.
 * @author maillol
 *
 */
public interface RoomGenerator {

	public Board generate( int nbRoom ) ;
}
