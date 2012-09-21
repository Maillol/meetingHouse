package mettingHouse.view;


import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mettingHouse.model.Game;

public class TopWindows extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private PutPlayersView putPlayersView ;
	private CreateBoardView createBoardView ;
	private RoundView roundView ;
	private ScoreView scoreView ;
	private static TopWindows instance ;
	
	public RoundView getRoundView() {
		return roundView ;
	}
	
	private Runnable showPutPlayersViewCode = new Runnable() {
		@Override
		public void run() {
			setContentPane( putPlayersView ) ;
			revalidate() ;
		}
	} ;

	
	private Runnable showCreateBoardViewCode = new Runnable() {
		@Override
		public void run() {
			setContentPane( createBoardView ) ;
			revalidate() ;
		}
	};

	
	private Runnable showRoundViewCode = new Runnable() {
		@Override
		public void run() {
			roundView.update() ;
			setContentPane( roundView ) ;
			revalidate() ;
		}
	};
	
	private Runnable showScoreViewCode = new Runnable() {
		@Override
		public void run() {
			scoreView.update() ;
			setContentPane( scoreView ) ;
			revalidate() ;
		}
	}  ;

	

	private TopWindows( Game game) {
		super();
		this.game = game ;
		setSize(640,480); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true); //On interdit pas la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setTitle("metting room") ;

		putPlayersView  = new PutPlayersView( new String[] { "human", "PM_1" }, game ) ;
		createBoardView = new CreateBoardView() ;
		scoreView       = new ScoreView( game ) ;
		roundView 		= new RoundView( game ) ;
	}
	
	
	/**
	 * Créer une instance de TopWindows dans ETD thread et la retourne.
	 * @param game
	 * @return
	 */
	public static TopWindows makeInstanceInETD( final Game game ) {
		Runnable code = new Runnable() {
            public void run() {
            	instance = new TopWindows( game ) ; 
            }
        };

        if (SwingUtilities.isEventDispatchThread()) {
            code.run();
        } else {
            try {
				SwingUtilities.invokeAndWait(code)  ;
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return instance ;
	}
	
	public void showPutPlayersView() {
		 if (SwingUtilities.isEventDispatchThread()) {
			 showPutPlayersViewCode.run();
	     } else {
	    	 SwingUtilities.invokeLater( showPutPlayersViewCode ) ;
	     }
	} 

	
	public void showCreateBoardView() {
		 if (SwingUtilities.isEventDispatchThread()) {
	            showCreateBoardViewCode.run();
	     } else {
	        	SwingUtilities.invokeLater( showCreateBoardViewCode ) ;
	     }
	} 

	public void showScoreView() {
		 if (SwingUtilities.isEventDispatchThread()) {
            showScoreViewCode.run();
        } else {
        	SwingUtilities.invokeLater( showScoreViewCode ) ;
        }
	} 

	public void showRoundView() {
		if (SwingUtilities.isEventDispatchThread()) {
           showRoundViewCode.run();
        } else {
       	    SwingUtilities.invokeLater( showRoundViewCode ) ;
        }
	} 
	
	public void addCreateBoardListener( CreateBoardListener listener ) {
		createBoardView.addCreateBoardListener(listener) ;
	}

	public void addPutPlayersListener( PutPlayersListener listener ) {
		putPlayersView.addPutPlayersListener(listener) ;
	}
	
	public void addScoreListener( ActionListener listener ) {
		scoreView.addNextButtonListener(listener) ;
	}
	
	public void addRoundViewListener( MouseListener listener ) {
		roundView.addMouseListener(listener);
	}
}
