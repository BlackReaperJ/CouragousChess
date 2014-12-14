package chessPieces;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Knight extends ChessPiece{
	
	private int [] paths = {-1,-2, 1, -2, 2, -1, 2, 1, 1, 2, -1, 2, -2, 1, -2, -1};
	
	public Knight(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
		System.out.println(x + " " + y + " " + name + " " + color+" grid: " + gridColor);
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
		this.setSelected(true);
		for(int i=0; i<paths.length; i+=2){
			int nextX = xPos + paths[i];
			int nextY = yPos + paths[i+1];
			if(nextX >= 0 && nextX <= 7 && nextY >= 0 && nextY <= 7){
				ChessAttributes piece = getChessPiece(chess,nextX, nextY);
					if(!(this.getColor().equals(piece.getColor())) || piece.getColor().equals("Blank")){
						piece.setSelected(true);
						System.out.println(this.getColor()+ " " + piece.getColor()+ " " +nextX + " " + nextY);
					}
			}
		}
		return chess;
	}

}
