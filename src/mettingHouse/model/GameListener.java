package mettingHouse.model;

import java.util.EventListener;

public interface GameListener extends EventListener {
	public void playerAdded( Player player ) ;
	public void playersCleared() ;
	public void humanPlayerAlreadyExists( Player existingHuman ) ;
	public void playerNameAlreadyExists( String name ) ;
}
