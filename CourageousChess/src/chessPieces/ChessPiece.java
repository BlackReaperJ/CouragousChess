package chessPieces;

abstract public class ChessPiece implements ChessAttributes{
	protected static int xPos = 0 ;
	protected static int yPos = 0;
	
	public ChessPiece(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
}
