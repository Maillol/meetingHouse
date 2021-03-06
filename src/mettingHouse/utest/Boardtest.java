package mettingHouse.utest;

import mettingHouse.model.Board;
import mettingHouse.model.Rect;
import mettingHouse.model.boardElement.Room;

import org.junit.*;
import static org.junit.Assert.*;

public class Boardtest {
	
	private Room room_v ;
	private Room room_h ;
	
    @Before 
    public void setUp() { 
    	room_v = new Room(true) ;
    	room_v.rect.setPosition(32, 32) ;
    	room_h = new Room(false) ;
    	room_h.rect.setPosition(32, 32) ;
    }
	
    @Test
    public void getNrect() {
		Rect r1 = Board.getNRect( room_v, true );
		assertEquals( "test 1", new Rect( 32, 28, 2, 4 ), r1) ;

		Rect r2 = Board.getNRect( room_v, false );
		assertEquals( "test 2", new Rect( 31, 30, 4, 2 ), r2) ;

		Rect r3 = Board.getNRect( room_h, true );
		assertEquals( "test 3", new Rect( 33, 28, 2, 4 ), r3) ;

		Rect r4 = Board.getNRect( room_h, false );
		assertEquals( "test 4", new Rect( 32, 30, 4, 2 ), r4) ;
    }
   
    @Test
    public void getSrect() {
		Rect r1 = Board.getSRect( room_v, true );
		assertEquals( "test 1", new Rect( 32, 36, 2, 4 ), r1) ;

		Rect r2 = Board.getSRect( room_v, false );
		assertEquals( "test 2", new Rect( 31, 36, 4, 2 ), r2) ;

		Rect r3 = Board.getSRect( room_h, true );
		assertEquals( "test 3", new Rect( 33, 34, 2, 4 ), r3) ;

		Rect r4 = Board.getSRect( room_h, false );
		assertEquals( "test 4", new Rect( 32, 34, 4, 2 ), r4) ;
    }

    @Test
    public void getErect() {
		Rect r1 = Board.getERect( room_v, true );
		assertEquals( "test 1", new Rect( 30, 32, 2, 4 ), r1) ;

		Rect r2 = Board.getERect( room_v, false );
		assertEquals( "test 2", new Rect( 28, 33, 4, 2 ), r2) ;

		Rect r3 = Board.getERect( room_h, true );
		assertEquals( "test 3", new Rect( 30, 31, 2, 4 ), r3) ;

		Rect r4 = Board.getERect( room_h, false );
		assertEquals( "test 4", new Rect( 28, 32, 4, 2 ), r4) ;
    }
    
    @Test
    public void getWrect() {
		Rect r1 = Board.getWRect( room_v, true );
		assertEquals( "test 1", new Rect( 34, 32, 2, 4 ), r1) ;

		Rect r2 = Board.getWRect( room_v, false );
		assertEquals( "test 2", new Rect( 34, 33, 4, 2 ), r2) ;

		Rect r3 = Board.getWRect( room_h, true );
		assertEquals( "test 3", new Rect( 36, 31, 2, 4 ), r3) ;

		Rect r4 = Board.getWRect( room_h, false );
		assertEquals( "test 4", new Rect( 36, 32, 4, 2 ), r4) ;
    }
    
    @Test
    public void getErect2() {
    	room_h.rect.setPosition(0, 0) ;
		Rect r = Board.getERect( room_h, true );
		assertEquals( "test 3", new Rect( -2, -1, 2, 4 ), r) ;
    }
    
    public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("utest.Boardtest");
    }
}
