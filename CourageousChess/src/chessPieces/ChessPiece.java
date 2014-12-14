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
	protected boolean selected;
	
	private final int START_POINTX = 15;//Starting point of grid location
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
	
	public void setX(int x){
		xPos = x;
	}
	
	public void setY(int y){
		yPos = y;
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
}
