package chessPieces;

import java.util.ArrayList;

public class ChessGrid {
	
	private int xPos = 0, yPos = 0;
	private ArrayList<ChessAttributes> chess;
	
	public ChessGrid() {
		chess = new ArrayList<ChessAttributes>();
		
		Pawn pawn;
		Knight knight;
		Bishop bishop;
		Rook rook;
		Queen queen;
		King king;
		
		for(int i =0; i<8; i++){//Add Black Pawns to chess grid
			pawn = new Pawn(i,1,"Pawn","Black");
			chess.add(pawn);
		}
		
		for(int i =0; i<8; i++){//Adds White Pawns to chess grid
			pawn = new Pawn(i,6,"Pawn","White");
			chess.add(pawn);
		}
		
		//Adds Knights to the chess board
		knight = new Knight(1,0,"Knight","Black");
		chess.add(knight);
		knight = new Knight(6,0,"Knight","Black");
		chess.add(knight);
		knight = new Knight(1,7,"Knight","White");
		chess.add(knight);
		knight = new Knight(6,7,"Knight","White");
		chess.add(knight);
		
		//Adds Bishops to the chess board
		bishop = new Bishop(2,0,"Bishop","Black");
		chess.add(bishop);
		bishop = new Bishop(5,0,"Bishop","Black");
		chess.add(bishop);
		bishop = new Bishop(2,7,"Bishop","White");
		chess.add(bishop);
		bishop = new Bishop(5,7,"Bishop","White");
		chess.add(bishop);		
		
		//Adds Rooks to the chess board
		rook = new Rook(0,0,"Rook","Black");
		chess.add(rook);
		rook = new Rook(7,0,"Rook","Black");
		chess.add(rook);
		rook = new Rook(0,7,"Rook","White");
		chess.add(rook);
		rook = new Rook(7,7,"Rook","White");
		chess.add(rook);
		
		//Adds Queens to the chess board
		queen = new Queen(3,0,"Queen","Black");
		chess.add(queen);
		queen = new Queen(3,7,"Queen","Black");
		chess.add(queen);
		
		//Adds Kings to the chess board
		king = new King(4,0,"King","Black");
		chess.add(king);
		king = new King(4,7,"King","Black");
		chess.add(king);
		
		//for(ChessAttributes piece: chess)
		//	System.out.println(piece.getX() + " " + piece.getY() + piece.getName() + piece.getColor());	
	}
	
	public ArrayList<ChessAttributes> getChessGrid(){
		return chess;
	}

}
