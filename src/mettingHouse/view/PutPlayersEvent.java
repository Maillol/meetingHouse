package mettingHouse.view;

import mettingHouse.model.AI;

public class PutPlayersEvent {
	private String name ;
	private AI ai ;

	public PutPlayersEvent( String name, AI ai ) {
		this.name = name ;
		this.ai = ai ;
	}
	
	public String getName() {
		return name;
	}
	
	public AI getIa() {
		return ai;
	}
}
