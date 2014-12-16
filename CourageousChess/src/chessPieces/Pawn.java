package chessPieces;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Pawn extends ChessPiece{

	private int nextX = 0;
	private int nextY = 0;
	private boolean moveUp;

	public Pawn(int x, int y, String name, String color, String gridColor) {
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
		if(getColor().equals("White")){//White pawns only
			nextY = yPos -1;
			if(nextY >= 0){//Moving By one Space up
				ChessAttributes piece = getChessPiece(chess,xPos, nextY);//Finds the chess piece at the next location 
				if(piece.getColor().equals("Blank")){
					piece.setSelected(true);
					moveUp = true;
				}

				nextX = xPos -1;
				if(nextX >=0)//Going Diagonally Left
					piece = getChessPiece(chess,nextX,nextY);
				if(piece.getColor().equals("Black")){
					piece.setSelected(true);
					kingCheck(this.getChessPiece(chess, xPos, yPos),piece);
				}


				nextX = xPos +1; 
				if(nextX <=7)//Going Diagonally Right
					piece = getChessPiece(chess,nextX,nextY);
				if(piece.getColor().equals("Black")){
					piece.setSelected(true);
					kingCheck(this.getChessPiece(chess, xPos, yPos),piece);
				}

				if(yPos == 6){//Start of a pawn as it can go 2 spaces up
					nextY = yPos -2;
					piece = getChessPiece(chess,xPos, nextY);
					if(piece.getColor().equals("Blank") && moveUp)
						piece.setSelected(true);
				}
			}
		}

		if(getColor().equals("Black")){//Black Pawns Only
			nextY = yPos +1;
			if(nextY <= 7){//Moving By one Space down
				ChessAttributes piece = getChessPiece(chess,xPos, nextY);
				if(piece.getColor().equals("Blank")){
					piece.setSelected(true);
					moveUp = true;
				}

				nextX = xPos -1;
				if(nextX >=0)//Going Diagonally Left
					piece = getChessPiece(chess,nextX,nextY);
				if(piece.getColor().equals("White")){
					piece.setSelected(true);
					kingCheck(this.getChessPiece(chess, xPos, yPos),piece);
				}

				nextX = xPos +1; 
				if(nextX <=7)//Going Diagonally Right
					piece = getChessPiece(chess,nextX,nextY);
				if(piece.getColor().equals("White")){
					piece.setSelected(true);
					kingCheck(this.getChessPiece(chess, xPos, yPos),piece);
				}

				if(yPos == 1){//Start of a pawn as it can go 2 spaces down
					nextY = yPos + 2;
					piece = getChessPiece(chess,xPos, nextY);
					if(piece.getColor().equals("Blank") && moveUp)
						piece.setSelected(true);
				}
			}
		}
		moveUp = false;
		return chess;
	}
}
