package mettingHouse.view;

import mettingHouse.model.BoardGenerator;

public class CreateBoardEvent {
	private int nbRoom ;
	private BoardGenerator typeHouse ;
	
	CreateBoardEvent( int nbRoom, BoardGenerator typeHouse ) {
		this.nbRoom = nbRoom ; 
		this.typeHouse = typeHouse ;
	}

	public int getNbRoom() {
		return nbRoom;
	}

	public BoardGenerator getTypeHouse() {
		return typeHouse;
	}
}
