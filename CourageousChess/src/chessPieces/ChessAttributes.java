package chessPieces;

import java.awt.Graphics2D;

public interface ChessAttributes {
	void move(); 
	boolean collide(ChessAttributes other); 
	void draw(Graphics2D g2);
	int getX();
	int getY();
	String getName();
	String getColor();
	//Have to add set methods
}
