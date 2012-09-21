package mettingHouse.model;

public class Rect {

    public int x ;
    public int y ;
    public int w ;
    public int h ;
    
    public Rect( int x, int y, int w, int h ) {
        this.x = x ;
        this.y = y ;
        this.w = w ;
        this.h = h ;
    }


    /**
     * Change rectancle's position
     * @param x Horizontal position
     * @param y vertical position
     */
    public void setPosition( int x, int y ) {
        this.x = x ;
        this.y = y ;	
    }
    
    @Override
    public boolean equals( Object obj ) {
    	if ( obj == this )
    		return true ;
    	
    	if (obj instanceof Rect ) {
    		Rect other = (Rect) obj;
    		return x == other.x && y == other.y && 
    		       w == other.w && h == other.h ;
		}

    	return false ;
    }
    
    
    public String toString() {
    	return String.format( "%dx%d at (%d,%d)", w, h, x, y ) ; 
    }
    
    public boolean colize( Rect o ) {
        return (( o.x + o.w > x  &&  o.x < x + w ) &&
                ( o.y + o.h > y  &&  o.y < y + h ) );
    }

    public int connected( Rect o ) {
        if ( ! colize( o ) )
        {
            if ( x + w / 2 == o.x + o.w / 2 ) {
                if ( y + h == o.y )
                    return 1 ;
                if ( o.y + o.h == y )
                    return 0 ;
            } 
            if ( y + h / 2 == o.y + o.h / 2 ) {
                if ( x == o.x + o.w )
                    return 2 ;
                if ( x + w == o.x ) 
                    return 3 ;
            }
        }
        return -1 ;
    }


}