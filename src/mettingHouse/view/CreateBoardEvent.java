package mettingHouse.view;

import java.awt.AWTEvent;

public class CreateBoardEvent {
	private int nbRoom ;
	private String typeHouse ;
	
	CreateBoardEvent( int nbRoom, String typeHouse ) {
		this.nbRoom = nbRoom ; 
		this.typeHouse = typeHouse ;
	}

	public int getNbRoom() {
		return nbRoom;
	}

	public String getTypeHouse() {
		return typeHouse;
	}
}
