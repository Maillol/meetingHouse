package mettingHouse.view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


import mettingHouse.model.boardElement.Piece;

/**
 * This class manage link between Piece instance and PieceDrawable instance.
 * Several getPieceDrawable call with the same Piece generate PieceDrawable with the same image member. 
 */
public class PieceDrawable implements Drawable {

	static private HashMap<Piece,PieceDrawable> instances = new HashMap<Piece,PieceDrawable>() ;
	private Rectangle rect ;
	private Piece piece ;
	private Image image ;
	static private ArrayList<Image> images = null ;
	static private ArrayList<Integer> indexImages = new ArrayList<Integer>() ;
	private Component panel ;

	
	private PieceDrawable( Piece piece, int x, int y, Image image, Component panel ) {
		this.piece = piece ;
		this.panel = panel ;
		this.image = image ;
		rect = new Rectangle(x, y, 80, 80) ;
	}
	
	private void loadImage() {
		images = new ArrayList<Image>();
		URL url = getClass().getResource("/tux/overlord59-chevalier-zodiac.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ; 
		url = getClass().getResource("/tux/overlord59-darkvadux-starwars.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-gemili-tux.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-tux-albator.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-tux-faucheur.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-tux-maffieu.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-tux-minigun.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-tux-samourai.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-tux-spiderman.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		url = getClass().getResource("/tux/overlord59-tux-terminator.png") ;
		images.add( Toolkit.getDefaultToolkit().getImage( url ) ) ;
		
		for ( int i = 0; i < images.size(); i++) {
			indexImages.add( i ) ;
		}
		Collections.shuffle( indexImages ) ;
	}
	
	private PieceDrawable( Piece piece, int x, int y, Component panel ) {
		if ( images == null )
			loadImage() ;
	
		this.piece = piece ;
		this.panel = panel ;
		
		rect = new Rectangle(x, y, 80, 80) ;
		image = images.get( indexImages.remove(0) ) ; 
	}
	
	/**
	 * Oublier les instances mémorisé.
	 */
	static public void forgetInstances() {
		if ( images != null ) {
			instances.clear() ;
			indexImages.clear() ;
			for ( int i = 0; i < images.size(); i++) {
				indexImages.add( i ) ;
			}
			Collections.shuffle( indexImages ) ;
		}
	}
	
	public Image getImage() {
		return image ;
	}
	
	static public PieceDrawable getPieceDrawable( Piece piece, int x, int y, Component panel ) {
		PieceDrawable r ;
		if ( instances.containsKey(piece)) {
			Image image = instances.get(piece).getImage() ;
			r = new PieceDrawable( piece, x, y, image, panel ) ;
		} else {
			r = new PieceDrawable( piece, x, y, panel ) ;
			instances.put(piece, r) ;
		}
		return r ;
	}
	
	public void move( int x, int y) {
		rect.setLocation(x, y) ;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, rect.x, rect.y, rect.width, rect.height, panel ) ;
	}

	@Override
	public Rectangle getRectangle() {
		return rect;
	}

	public Piece getPiece() {
		return piece;
	}

}
