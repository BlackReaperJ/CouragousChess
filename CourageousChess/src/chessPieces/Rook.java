package chessPieces;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Rook extends ChessPiece{
	private int [] paths = {0,-1, 0, 1, 1, 0, -1, 0};
	private int nextX, nextY;
	
	public Rook(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
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
		for(int i = 0; i< paths.length; i+=2)
			chess = nextMoveSet(chess,xPos,yPos,paths[i],paths[i+1]);
		
		return chess;
	}
	
	public ArrayList<ChessAttributes> nextMoveSet(ArrayList<ChessAttributes> chess, int currentX, int currentY, int moveX, int moveY) {
		nextX = currentX + moveX;
		nextY = currentY + moveY;
		ChessAttributes piece = getChessPiece(chess,nextX, nextY);
		
		if(nextX >= 0 && nextX <= 7 && nextY >= 0 && nextY <= 7){
			if(this.getColor().equals(piece.getColor()))
				return chess;
			else if(!(this.getColor().equals(piece.getColor())) && !(piece.getColor().equals("Blank"))){
				piece.setSelected(true);
				kingCheck(this.getChessPiece(chess, xPos, yPos),piece);
				return chess;
			}
			else{
				piece.setSelected(true);
				return nextMoveSet(chess,nextX,nextY,moveX,moveY);
			}
		}
		return chess;
	}
}
