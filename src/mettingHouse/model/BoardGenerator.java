package mettingHouse.model;

/**
 * Strategie pour positionner un ensemble de Room et 
 * retourner une Board.
 * @author maillol
 *
 */
public interface BoardGenerator {

	public Board generate( int nbRoom ) ;
	
	public String toString() ;
}