package chessPieces;

import java.util.ArrayList;

public class ChessGrid {
	
	private int xPos = 0, yPos = 0;
	private ArrayList<ChessAttributes> chess;
	private String gridColor;
	private int gridColors = 0;
	
	public ChessGrid() {
		chess = new ArrayList<ChessAttributes>();
		
		Blank blank;
		Pawn pawn;
		Knight knight;
		Bishop bishop;
		Rook rook;
		Queen queen;
		King king;
		
		for(int i =0; i<8; i++){//Add Black Pawns to chess grid
			if(i %2 ==0) 	gridColor = "Black";
			else 			gridColor = "White";			
			pawn = new Pawn(i,1,"Pawn","Black", gridColor);
			chess.add(pawn);
		}
		
		for(int i =0; i<8; i++){//Adds White Pawns to chess grid
			if(i %2 ==0) 	gridColor = "White";
			else 			gridColor = "Black";
			pawn = new Pawn(i,6,"Pawn","White",gridColor);
			chess.add(pawn);
		}
		
		//Adds Knights to the chess board
		knight = new Knight(1,0,"Knight","Black","Black");
		chess.add(knight);
		knight = new Knight(6,0,"Knight","Black","White");
		chess.add(knight);
		knight = new Knight(1,7,"Knight","White","White");
		chess.add(knight);
		knight = new Knight(6,7,"Knight","White","Black");
		chess.add(knight);
		
		//Adds Bishops to the chess board
		bishop = new Bishop(2,0,"Bishop","Black","White");
		chess.add(bishop);
		bishop = new Bishop(5,0,"Bishop","Black","Black");
		chess.add(bishop);
		bishop = new Bishop(2,7,"Bishop","White","Black");
		chess.add(bishop);
		bishop = new Bishop(5,7,"Bishop","White","White");
		chess.add(bishop);		
		
		//Adds Rooks to the chess board
		rook = new Rook(0,0,"Rook","Black","White");
		chess.add(rook);
		rook = new Rook(7,0,"Rook","Black","Black");
		chess.add(rook);
		rook = new Rook(0,7,"Rook","White","Black");
		chess.add(rook);
		rook = new Rook(7,7,"Rook","White","White");
		chess.add(rook);
		
		//Adds Queens to the chess board
		queen = new Queen(3,0,"Queen","Black","Black");
		chess.add(queen);
		queen = new Queen(3,7,"Queen","White","White");
		chess.add(queen);
		
		//Adds Kings to the chess board
		king = new King(4,0,"King","Black","White");
		chess.add(king);
		king = new King(4,7,"King","White","Black");
		chess.add(king);
		
		//Add Blanks on the Chess Board
		for(int i =0; i<8; i++){
			for(int j=2; j<6; j++){
				if(gridColors %2 ==0) 	gridColor = "White";
				else 			gridColor = "Black";
				blank = new Blank(i,j,"Blank","Blank", gridColor);
				chess.add(blank);
				gridColors++;
			}
			gridColors++;
		}
		
		for(ChessAttributes piece: chess)
			System.out.println(piece.getX() + " " + piece.getY() + piece.getName() + piece.getColor());	
	}
	
	public ArrayList<ChessAttributes> getChessGrid(){
		return chess;
	}

}
