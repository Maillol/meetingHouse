/**
 * Cette classe dois créer un nouveau model
 * un nouveau controleur 
 * est relier les deux
 */


package mettingHouse;
import mettingHouse.ctrl.CreateBoardCtrl;
import mettingHouse.model.Game;

public class MettingRoom {

	public static void main(String[] args) {
		final Game model = new Game() ;
		final CreateBoardCtrl firstCtrl = new CreateBoardCtrl( model ) ;
	}
}
