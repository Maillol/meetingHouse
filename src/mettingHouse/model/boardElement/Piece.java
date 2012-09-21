package mettingHouse.model.boardElement;

import java.util.ArrayList;

import mettingHouse.model.Player;

public class Piece {
    private String name ;
    private Room room ;
    private Player player ; 
    
    public Piece( String color ) {
    	this.name = color ;
    }
    
    /**
     * @return Liste des sales où le déplacement est possible.
     */
    public ArrayList<Room> posibleMove( ) {
    	if ( room != null ) {
	    	ArrayList<Room> r = new ArrayList<Room>() ;
	    	if ( room.getPieces().size() == 1 )
	    	{
	    		if ( room.getN() != null )
	    			r.add( room.getN() ) ;
		    	if ( room.getS() != null )
		    		r.add( room.getS() ) ;
		    	if ( room.getE() != null )
		    		r.add( room.getE() ) ;
		    	if ( room.getW() != null )
		    		r.add( room.getW() ) ;
	    	}
	    	return r ;
    	}
    	return null ;
    }
    
    public boolean isLocked() {
    	return room.getPieces().size() > 1 ;
    }
	
	public Room getRoom() {
		return room;
	}


	public void setRoom( Room room ) {
		this.room = room ;  
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return player;
	}
    
	public void setPlayer( Player player ) {
		this.player = player ;
	}
}