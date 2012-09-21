package mettingHouse.model;

import java.util.ArrayList;

import mettingHouse.model.boardElement.Piece;


public class Player implements Comparable<Player> {
	private String name ;
	private int score ;
	private ArrayList<Piece> pieces ;

	public Player( String name ) {
		this.name = name ;
		pieces = new ArrayList<Piece>();
	}
	
	@Override
	public String toString() {
		return name ;
	}
	
	@Override
	public boolean equals( Object obj ) {
		if ( obj == this )
			return true ;
		
		if (obj instanceof Player ) {
			Player other = (Player) obj;
			return name.equals( other.name ) ;
		}
	
		return false ;
	}


	@Override
	public int compareTo(Player o) {
		Integer.compare( score, o.score ) ;
		return 0;
	}


	/**
	 * Give pieces to player.
	 */
	public void addPieces( Piece pieces) {
		this.pieces.add( pieces );
	}


	public ArrayList<Piece> getPieces() {
		return pieces;
	}


	public void setScore(int score) {
		this.score = score;
	}


	/**
	 * Add <i>point</i> to score's player.
	 */
	public void updateScore( int point ) {
		score += point ;
	}
	
    /**
	 * @return player's score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <i>type</i> is user friendly name of class.
	 * "human" for instances of this class.
	 * @return player's type.
	 */
	public String getType() {
		return "human" ;
	}
	
}
