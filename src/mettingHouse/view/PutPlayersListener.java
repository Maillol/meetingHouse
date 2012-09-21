package mettingHouse.view;

import java.util.EventListener;

public interface PutPlayersListener extends EventListener {
	public void putPlayer( PutPlayersEvent event ) ;
	public void putPlayerClosed( int endScore ) ;
}
