package mettingHouse.utest;

import mettingHouse.model.Rect;

import org.junit.*;
import static org.junit.Assert.*;

public class Recttest {
    @Test
    public void colize_1() {
        Rect r1 = new Rect( 0, 0, 2, 4 ) ;
        Rect r2 = new Rect( 0, 0, 2, 4 ) ;
        assertTrue( r1.colize( r2 ) );
    }

    @Test
    public void colize_2() {
        Rect r1 = new Rect( 0, 0, 2, 4 ) ;
        Rect r2 = new Rect( 4, 2, 2, 4 ) ;
        assertTrue( ! r1.colize( r2 ) );
    }

    @Test
    public void colize_3() {
        Rect r1 = new Rect( 4, 2, 2, 4 ) ;
        Rect r2 = new Rect( 0, 0, 2, 4 ) ;
        assertTrue( ! r1.colize( r2 ) );
    }

    @Test
    public void connected_1() {
        Rect r1 = new Rect( 0, 0, 2, 4 ) ;
        Rect r2 = new Rect( 0, 4, 2, 4 ) ;
        assertEquals( 1, r1.connected( r2 ) );
    }

    @Test
    public void connected_2() {
        Rect r1 = new Rect( 1, 0, 2, 4 ) ;
        Rect r2 = new Rect( 0, 4, 4, 2 ) ;
        assertEquals( 1, r1.connected( r2 ) );
    }

    @Test
    public void connected_3() {
        Rect r1 = new Rect( 4, 4, 2, 4 ) ;
        Rect r2 = new Rect( 4, 0, 2, 4 ) ;
        assertEquals( 0, r1.connected( r2 ) );
    }

    @Test
    public void connected_4() {
        Rect r1 = new Rect( 4, 4, 2, 4 ) ;
        Rect r2 = new Rect( 0, 5, 4, 2 ) ;
        assertEquals( 2, r1.connected( r2 ) );
    }

    @Test
    public void connected_5() {
        Rect r1 = new Rect( 0, 0, 2, 4 ) ;
        Rect r2 = new Rect( 2, 1, 4, 2 ) ;
        assertEquals( 3, r1.connected( r2 ) );
    }

    @Test
    public void connected_6() {
        Rect r1 = new Rect( 0, 0, 2, 4 ) ;
        Rect r2 = new Rect( 0, 0, 2, 4 ) ;
        assertEquals( -1, r1.connected( r2 ) );
    }


    public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("utest.Recttest");
    }
}
