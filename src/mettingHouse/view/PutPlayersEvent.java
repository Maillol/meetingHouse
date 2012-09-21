package mettingHouse.view;

public class PutPlayersEvent {
	private String name ;
	private String ia ;

	public PutPlayersEvent( String name, String ia ) {
		this.name = name ;
		this.ia = ia ;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIa() {
		return ia;
	}
}
