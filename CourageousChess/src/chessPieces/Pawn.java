package chessPieces;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Pawn extends ChessPiece{
	private ArrayList<Rectangle> nextPaths;
	private int nextX = 0;
	private int nextY = 0;
	
	public Pawn(int x, int y, String name, String color) {
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
		
		if(getColor().equals("White")){//White pawns only
			nextY = yPos -1;
			if(nextY >= 0){//Moving By one Space up
				ChessAttributes c = getChessPiece(chess,xPos, nextY);//Finds the chess piece at the next location 
				if(c == null)
					nextPaths.add(chessGrid[xPos][nextY]);
	
				nextX = xPos -1;
				if(nextX >=0)//Going Diagonally Left
					c = getChessPiece(chess,nextX,nextY);
					if(c != null && c.getColor().equals("Black"))
						nextPaths.add(chessGrid[nextX][nextY]);
					
				nextX = xPos +1; 
				if(nextX <=7)//Going Diagonally Right
					c = getChessPiece(chess,nextX,nextY);
					if(c != null && c.getColor().equals("Black"))
						nextPaths.add(chessGrid[nextX][nextY]);
					
				if(yPos == 6){//Start of a pawn as it can go 2 spaces up
					nextY = yPos -2;
				    c = getChessPiece(chess,xPos, nextY);
					if(c == null)
						nextPaths.add(chessGrid[xPos][nextY]);
				}
			}
		}
		
		if(getColor().equals("Black")){//Black Pawns Only
			nextY = yPos +1;
			if(nextY <= 7){//Moving By one Space down
				ChessAttributes c = getChessPiece(chess,xPos, nextY);
				if(c == null)
					nextPaths.add(chessGrid[xPos][nextY]);
	
				nextX = xPos -1;
				if(nextX >=0)//Going Diagonally Left
					c = getChessPiece(chess,nextX,nextY);
					if(c != null && c.getColor().equals("White"))
						nextPaths.add(chessGrid[nextX][nextY]);
					
				nextX = xPos +1; 
				if(nextX <=7)//Going Diagonally Right
					c = getChessPiece(chess,nextX,nextY);
					if(c != null && c.getColor().equals("White"))
						nextPaths.add(chessGrid[nextX][nextY]);
					
				if(yPos == 1){//Start of a pawn as it can go 2 spaces down
					nextY = yPos + 2;
				    c = getChessPiece(chess,xPos, nextY);
					if(c == null)
						nextPaths.add(chessGrid[xPos][nextY]);
				}
			}
		}
		System.out.println(nextPaths);
		return nextPaths;
	}
}
