package chessPieces;

abstract public class ChessPiece implements ChessAttributes{
	protected int xPos;
	protected int yPos;
	protected String name;
	protected String color;
	
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
	
	public void setX(int x){
		xPos = x;
	}
	
	public void setY(int y){
		yPos = y;
	}
}
