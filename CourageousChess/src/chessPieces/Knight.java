package chessPieces;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Knight extends ChessPiece{

	public Knight(int x, int y, String name, String color) {
		super(x,y, name, color);
		System.out.println(x + " " + y + " " + name + " " + color);
	}

	public void move() {
		
	}

	public boolean collide(ChessAttributes other) {
		return false;
	}

	public void draw(Graphics2D g2) {
		
	}

	public ArrayList<Rectangle> nextMoveSet() {
		System.out.println(xPos + " " + yPos);
		return null;
	}

}
