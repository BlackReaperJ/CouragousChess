package chessPieces;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Knight extends ChessPiece{
	private int [] paths = {-1,-2, 1, -2, 2, -1, 2, 1, 1, 2, -1, 2, -2, 1, -2, -1};
	private ArrayList<Rectangle> nextPaths;
	
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

	public ArrayList<Rectangle> nextMoveSet(ArrayList<ChessAttributes> chess, Rectangle [][] chessGrid) {
		nextPaths = new ArrayList<Rectangle>();
		for(int i=0; i<paths.length; i+=2){
			int nextX = xPos + paths[i];
			int nextY = yPos + paths[i+1];
			if(nextX >= 0 && nextX <= 7 && nextY >= 0 && nextY <= 7){
				ChessAttributes c = getChessPiece(chess,nextX, nextY);
				if(c == null || !(getColor().equals(c.getColor()))){
					nextPaths.add(chessGrid[nextX][nextY]);
				}
			}
		}
		System.out.println(nextPaths);
		return nextPaths;
	}

}
