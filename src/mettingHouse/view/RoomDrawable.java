package mettingHouse.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


import mettingHouse.model.boardElement.Piece;
import mettingHouse.model.boardElement.Room;



public class RoomDrawable implements Drawable {

	public static final int RATE = 72 ;
	public static final int RATE_2 = 30 ;
	
	private Room room ;
	private Rectangle rect ;
	private ArrayList<PieceDrawable> pieces ; 
	private Component panel;
	
	public RoomDrawable( Room room, Component panel ) {
		 this.room = room ;
		 rect = new Rectangle( room.rect.x *RATE, room.rect.y *RATE,
				 room.rect.w *RATE, room.rect.h *RATE ) ;
		 pieces = new ArrayList<PieceDrawable>() ;
		 this.panel = panel ;
	}

	public Room getRoom() {
		return room ;
	}
	
	@Override
	public void draw(Graphics g) {
		int x = room.rect.x *RATE ;
		int y = room.rect.y *RATE ;
		int w = room.rect.w *RATE ;
		int h = room.rect.h *RATE ;
		
		g.setColor(Color.BLACK) ;
		if ( room.getN() == null ) {
			g.drawLine( x, y,      x + w, y ) ;  			
		} else {
			g.drawLine( x, y,        x + w/2 - RATE_2, y ) ; 
			g.drawLine( x + w/2 + RATE_2, y,      x + w, y ) ; 
		}

		if ( room.getS() == null ) {
			g.drawLine( x, y + h,      x + w, y + h ) ;  			
		} else {
			g.drawLine( x, y + h,      x + w/2 - RATE_2, y + h) ; 
			g.drawLine( x + w/2 + RATE_2, y + h,      x + w, y + h ) ;
		}

		if ( room.getE() == null ) {
			g.drawLine( x, y,    x, y + h ) ;  			
		} else {
			g.drawLine( x, y,    x, y + h/2 - RATE_2 ) ; 
			g.drawLine( x, y + h/2 + RATE_2,    x, y + h ) ; 
		}
		 			
		if ( room.getW() == null ) {
			g.drawLine( x + w, y,    x + w, y + h ) ;  			
		} else {
			g.drawLine( x + w, y,    x + w, y + h/2 - RATE_2 ) ; 
			g.drawLine( x + w, y + h/2 + RATE_2,    x + w, y + h ) ; 
		}
		
		pieces.clear() ;
		int offset_x = 0 ;
		int offset_y = 0 ;
		for( Piece p : room.getPieces() ) {
    		PieceDrawable piece_d = PieceDrawable.getPieceDrawable( p, x + 1 + offset_x, y + 1 + offset_y, panel ) ;
    		piece_d.draw( g ) ;
    		pieces.add( piece_d ) ;
    		if ( room.isVertical() ) {
    			if ( offset_x < 60 )
    				offset_x += 60 ;
    			else {
    				offset_x = 0 ;
    				offset_y += 60 ;
    			} 
    		} else {
    			if ( offset_x < 180 )
    				offset_x += 60 ;
    			else {
    				offset_x = 0 ;
    				offset_y += 60 ;
    			} 
    		}
    	}
	}

	public ArrayList<PieceDrawable> getPieces() {
		return pieces ;
	}
	
	@Override
	public Rectangle getRectangle() {
		return rect ;
	}

}
