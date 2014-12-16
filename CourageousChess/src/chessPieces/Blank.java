package chessPieces;
  
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Blank extends ChessPiece {
	
	public Blank(int x, int y, String name, String color, String gridColor) {
		super(x, y, name, color, gridColor);
		//System.out.println(x + " " + y + " " + name + " " + color+" grid: " + gridColor);
	}

	public void move() {
		
	}

	public boolean collide(ChessAttributes other) {
		return false;
	}

	public void draw(Graphics2D g2, int gridSize) {
		super.draw(g2, gridSize);
	}
	 
	public ArrayList<ChessAttributes> nextMoveSet(ArrayList<ChessAttributes> chess) {
		return chess;
	}

}
