package mettingHouse.model.boardElement;

import java.util.ArrayList;

import mettingHouse.model.Rect;

public class Room {

    private Room n ;
    private Room s ;
    private Room e ;
    private Room w ;
    private boolean vertical ;
    public Rect rect ;
    private ArrayList<Piece> pieces ;
    
    
    public Room( boolean vertical ) {
        this.vertical = vertical ;
        this.rect = vertical ? new Rect( 0, 0, 2, 4 ) : new Rect( 0, 0, 4, 2 ) ; 
        this.pieces = new ArrayList<Piece>() ;
    }

    
    /**
     * Créer une piece dans la salle avec pour nom name
     * @param name
     * @return
     */
    public Piece CreatePiece( String name ) {
    	Piece piece = new Piece( name ) ;
    	pieces.add( piece ) ;
    	piece.setRoom( this ) ;
    	return piece ;
    }
    
    /**
     * Ajoute une piece à la salle. 
     * @param p Piece à ajouter à la salle
     */
    public void addPiece( Piece p ) {
    	pieces.add( p ) ;
    }


    /**
     * Enlever une piece à la salle.
     * @param p Pion à enlever à la salle
     */
    public void delPiece( Piece p ) {
    	pieces.remove( p ) ;
    }

    /**
     * @return la liste des pièce de la salle.
     */
    public ArrayList<Piece> getPieces( ) {
		return ( ArrayList<Piece> ) pieces.clone() ;
    }


    
    /**
     * Connecter une autre salles à une porte donnée.
     * gate 0 = N, 1 = S, 2 = E, 3 = W
     */
    public void join( Room other, int gate )
    {
        if ( gate == 0 ) {
            n = other ;
            other.s = this ;
            if ( vertical ) {
                if ( other.vertical )
                    other.rect = new Rect( rect.x, rect.y - 4, 2, 4 ) ;
                else 
                    other.rect = new Rect( rect.x - 1, rect.y -2, 4, 2 ) ;
            }
            else {
                if ( other.vertical )
                    other.rect = new Rect( rect.x + 1, rect.y - 4, 2, 4 ) ;
                else
                    other.rect = new Rect( rect.x, rect.y -2, 4, 2 ) ;
            }
        } 
        else if ( gate == 1 ) {
            s = other ;
            other.n = this ;
            if ( vertical ) {
                if ( other.vertical )
                    other.rect = new Rect( rect.x, rect.y + 4, 2, 4 ) ;
                else 
                    other.rect = new Rect( rect.x - 1, rect.y + 4, 4, 2 ) ;
            }
            else {
                if ( other.vertical )
                    other.rect = new Rect( rect.x + 1, rect.y + 2, 2, 4 ) ;
                else 
                    other.rect = new Rect( rect.x, rect.y +2, 4, 2 ) ;
            }
        }
        else if ( gate == 2 ) {
            e = other ;
            other.w = this ;
            if ( vertical ) {
                if ( other.vertical )
                    other.rect = new Rect( rect.x - 2, rect.y, 2, 4 ) ;
                else 
                    other.rect = new Rect( rect.x - 4, rect.y + 1, 4, 2) ;
            }
            else {
                if ( other.vertical )
                    other.rect = new Rect( rect.x - 2, rect.y -1, 2, 4 ) ;
                else 
                    other.rect = new Rect( rect.x - 4, rect.y, 4, 2) ;
            }
        } 
        else if ( gate == 3 ) {
            w = other ;
            other.e = this ;
            if ( vertical ) {
                if ( other.vertical )
                    other.rect = new Rect( rect.x + 2, rect.y, 2, 4 ) ;
                else 
                    other.rect = new Rect( rect.x + 2, rect.y + 1, 4, 2 ) ;
            }
            else {
                if ( other.vertical )
                    other.rect = new Rect( rect.x + 4, rect.y -1, 2, 4 ) ;
                else 
                    other.rect = new Rect( rect.x + 4, rect.y, 4, 2 ) ;
            }
        }
    }

    /**
     * 
     * @return true if room is vertical, else false.
     */
    public boolean isVertical() {
    	return vertical;
    }

    
    /* Place the first room
     */
    public void Place() {
        if ( vertical )
            rect = new Rect( 0, 0, 2, 4 ) ;
        else 
            rect = new Rect( 0, 0, 4, 2 ) ;
    }

    public Room getN() {
        return n ;
    }

    public Room getS() {
        return s ;
    }
    
    public Room getE() {
        return e ;
    }
    
    public Room getW() {
        return w ;
    }

}