package mettingHouse.model;

abstract public class AI {
	PlayerWithAI player ;
	Board board ;

	public void setContext( PlayerWithAI player, Board board ) {
		this.player = player ;
		this.board = board ;
	}

	abstract public boolean play() ;

}