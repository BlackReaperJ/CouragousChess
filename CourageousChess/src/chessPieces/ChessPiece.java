package chessPieces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

abstract public class ChessPiece implements ChessAttributes{
	protected int xPos;
	protected int yPos;
	protected String name;
	protected String color;
	protected String gridColor;
	protected int locationX, locationY;
	protected boolean selected, check;
	protected ArrayList<ChessAttributes> kingInCheck,temp;

	private final int START_POINTX = 0;//Starting point of grid location
	private final int START_POINTY = 0;
	private Rectangle grid;

	public ChessPiece(int xPos, int yPos, String name, String color, String gridColor){
		this.xPos = xPos;
		this.yPos = yPos;
		this.name = name;
		this.color = color;
		this.gridColor = gridColor;
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

	public String getGridColor(){
		return gridColor;
	}

	public void setX(int x){
		xPos = x;
	}

	public void setY(int y){
		yPos = y;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setColor(String color){
		this.color = color;
	}

	public void setGridColor(String gridColor){
		this.gridColor = gridColor;
	}

	public ChessAttributes getChessPiece(ArrayList<ChessAttributes> chess, int x, int y){
		for(ChessAttributes c: chess)
			if(c.getX() == x && c.getY() == y)
				return c;
		return null;
	}

	public void draw(Graphics2D g2, int gridSize) {
		locationX = gridSize * xPos + START_POINTX;
		locationY = gridSize * yPos + START_POINTY;
		grid = new Rectangle(locationX, locationY, gridSize, gridSize);

		if(gridColor.equals("White")) g2.setColor(Color.WHITE);
		else if(gridColor.equals("Black")) g2.setColor(Color.BLACK);

		if(selected)
			g2.setColor(Color.CYAN);


		if(check && this.getName().equals("King"))
			g2.setColor(Color.RED);

		g2.fill(grid);

		g2.setColor(Color.BLACK);
		g2.draw(grid);
	}

	public boolean contains(int x, int y){
		if(grid.contains(x, y)){
			return true;
		}
		return false;
	}

	public void setSelected(boolean selected){
		this.selected = selected;
	}

	public boolean getSelected(){
		return selected;
	}

	public void setCheck(boolean check){
		this.check = check;
	}

	public boolean getCheck(){
		return check;
	}

	public void swapInfo(ChessAttributes piece){
		int newX = piece.getX();
		int newY = piece.getY();
		String newGridColor = piece.getGridColor();

		piece.setX(this.getX());
		piece.setY(this.getY());
		piece.setGridColor(this.getGridColor());
		piece.setName("Blank");
		piece.setColor("Blank");

		this.setX(newX);
		this.setY(newY);
		this.setGridColor(newGridColor);
		System.out.println(this.getX() + " " + this.getY() + " " + this.getGridColor());
	}

	public void kingCheck(ArrayList<ChessAttributes> chess){
		for(ChessAttributes piece: chess){
			temp = piece.nextMoveSet(chess);
		}
		
		kingInCheck = new ArrayList<ChessAttributes>();
		
		for(ChessAttributes piece: chess){
			if(piece.getCheck()){
				kingInCheck.add(piece);
				System.out.println(this.getName() + " " + this.getColor() + " " + this.getGridColor());
			}
		}
	}
	
	public void kingCheck(ChessAttributes c, ChessAttributes piece){
		if(piece.getName().equals("King")){
			this.setCheck(true);
			piece.setCheck(true);
		}
	}
}
