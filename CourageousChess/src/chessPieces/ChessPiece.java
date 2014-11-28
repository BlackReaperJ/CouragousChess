package chessPieces;

abstract public class ChessPiece implements ChessAttributes{
	private int xPos;
	private int yPos;
	private String name;
	private String color;
	
	public ChessPiece(int xPos, int yPos, String name, String color){
		this.xPos = xPos;
		this.yPos = yPos;
		this.name = name;
		this.color = color;
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public String getName(){
		return name;
	}
	
	public String getColor(){
		return color;
	}
}
