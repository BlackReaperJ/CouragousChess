package chessPieces;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public interface ChessAttributes {
	void move(); 
	boolean collide(ChessAttributes other); 
	void draw(Graphics2D g2);
	int getX();
	int getY();
	String getName();
	String getColor();
	void setX(int x);
	void setY(int y);
	ArrayList<Rectangle> nextMoveSet();
	//Have to add set methods
}
