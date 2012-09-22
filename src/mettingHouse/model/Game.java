package mettingHouse.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.event.EventListenerList;

import mettingHouse.model.boardElement.Piece;
import mettingHouse.model.boardElement.Room;



public class Game  {

    private final EventListenerList listeners = new EventListenerList();
	private ArrayList<Player> players = new ArrayList<Player>() ;
	private int endScore = 50 ;
	private int currentPlayer ; 
	private Board board ;
	private Player human ;
	private Player winner ;
	private HashMap<Player, ArrayList<Integer>> scoreByRound = new HashMap<Player, ArrayList<Integer>>() ;
	
	
	public void addGameListener( GameListener listener) {
        listeners.add( GameListener.class, listener );
    }
	
	protected void firePlayerAdded( Player p ) {
		for ( GameListener l : listeners.getListeners( GameListener.class ) ) {
			l.playerAdded(p) ;
		}
	}
	
	protected void fireHumanPlayerAlreadyExists( Player existingHuman ) {
		for ( GameListener l : listeners.getListeners( GameListener.class ) ) {
			l.humanPlayerAlreadyExists( existingHuman ) ;
		}
	}
	
	protected void firePlayersCleared() {
		for ( GameListener l : listeners.getListeners( GameListener.class ) ) {
			l.playersCleared() ;
		}
	}

	protected void firePlayerNameAlreadyExists( String name ) {
		for ( GameListener l : listeners.getListeners( GameListener.class ) ) {
			l.playerNameAlreadyExists( name ) ;
		}
	}
	
	
	
	public HashMap<Player, ArrayList<Integer>> getScoreByRound() {
		return scoreByRound ;
	}
	
	
	public Player getWinner() {
		return winner;
	}

	public void addPlayer( String name, AI type ) {
		Player player ;
		if ( type.toString() == "human" ) {
			if ( human != null ) {
				fireHumanPlayerAlreadyExists( human ) ;
				return ;
			}
			player = new Player( name ) ;
			human = player ;
		}
		else {
			player = new PlayerWithAI( name, type ) ;
		}

		// Test if the new player already exist.
		if ( ! players.contains( player ) ) {			
			players.add( player ) ; 
			scoreByRound.put( player, new ArrayList<Integer>()) ;
			firePlayerAdded( player ) ;
		} else {
			firePlayerNameAlreadyExists( name ) ;
		}
	}

	public void setEndScore( int endScore ) {
		this.endScore = endScore ;
	}


	/**
	 * @param nbRoom
	 * @param algo nom de l'algo (Standard, TwoByTwo )
	 * @return
	 */
	public void createBoard( int nbRoom, BoardGenerator algo ) {
		board = algo.generate(nbRoom) ;
		board.centerRoomsPosition() ;
	}

	
	/**
	 * place les pieces et les attributs aux joueurs.
	 */
	public Player start() {
		for ( Player player : players ) {
			player.getPieces().clear() ;
			if ( player instanceof PlayerWithAI ) {
				((PlayerWithAI) player).setBoard( board ) ;
			}
		}

        for (Room room : getBoard().getRoomsPlaced() ) {
        	Player player = nextPlayer() ;
			Piece piece = room.CreatePiece( "a piece" );
			piece.setPlayer( player ) ;
			player.addPieces( piece ) ;
		}

        return nextPlayer() ;
	}
	
	public Player getCurrentPlayer() {
		return players.get( currentPlayer -1 ) ;
	}
	
	public Board getBoard() {
		return board ;
	}

	public Player getHuman() {
		return human ;
	}

	public ArrayList<Player> getPlayers() {
		return players ;
	}
	
	public Player nextPlayer() {
		if ( currentPlayer >= players.size() ) {
			currentPlayer = 0 ;
		}
		Player p = players.get( currentPlayer++ ) ;
		return p;
	}


	public void reset() {
		winner = null ;
		board = null ;
		scoreByRound.clear() ;
		players.clear() ;
		currentPlayer = 0 ;
		firePlayersCleared() ;
		endScore = 50 ;
		human = null ;
	}
	
	/**
	 * Ajoute les scores des pions au joueur
	 * @return 1 si un joueur à dépasser le endScore sinon 0.
	 */
	public boolean updateScore() {
		int point = 0 ;
		for (Piece piece : board.getPieceRemoved()) {
			piece.getPlayer().updateScore( point ) ;
			point++ ;
			System.out.println( "UPDATE poin for " + piece ) ; 
		}

		board.getSurvivor().getPlayer().updateScore(point) ;

		for (Entry<Player, ArrayList<Integer>> entry : scoreByRound.entrySet()) {
			entry.getValue().add( entry.getKey().getScore() ) ;
		}
		
		
		//Player best = Collections.max( players ) ;
		Player best = null ;
		int bestScore = 0 ;
		for (Player p : players ) {
			if ( p.getScore() > bestScore ) {
				bestScore = p.getScore() ;
				best = p ;
			}
		}
		
		if ( best.getScore() >= endScore ) {
			winner = best ;
			return true ;
		} 
		winner = null ;
		return false ;
	}


}