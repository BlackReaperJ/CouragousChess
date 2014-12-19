package chessPieces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Rook extends ChessPiece{
	private int [] paths = {0,-1, 0, 1, 1, 0, -1, 0};
	private int nextX, nextY;
	
	public Rook(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
	}

	public void draw(Graphics2D g2, int gridSize) {
		super.draw(g2, gridSize);
		
		/*Draw Main Body*/
		Polygon mainBody = new Polygon();
		mainBody.addPoint((int)(locationX + 5.5*(gridSize/32.0)),(int)(locationY + 12.5*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 5.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//Left tower
		mainBody.addPoint((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//Left Tower
		mainBody.addPoint((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 11.5*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 11.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//LeftCenter Tower
		mainBody.addPoint((int)(locationX + 14.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//LeftCenter Tower
		mainBody.addPoint((int)(locationX + 14.5*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 17.5*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 17.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//Right Center Tower
		mainBody.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//Right Center Tower
		mainBody.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 23.5*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 23.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//Right Tower
		mainBody.addPoint((int)(locationX + 26.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)));//Right Tower
		mainBody.addPoint((int)(locationX + 26.5*(gridSize/32.0)),(int)(locationY + 12.5*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 23.5*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 21*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 22*(gridSize/32.0)),(int)(locationY + 24.5*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 22*(gridSize/32.0)),(int)(locationY + 25.5*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 24.5*(gridSize/32.0)),(int)(locationY + 28*(gridSize/32.0)));//Bottom Right Corner
		mainBody.addPoint((int)(locationX + 7.5*(gridSize/32.0)),(int)(locationY + 28*(gridSize/32.0)));//Bottom Left Corner
		mainBody.addPoint((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 25.5*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 24.5*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 11*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		
		// Draw top trapezoid
		Polygon topTrapezoid = new Polygon();
		topTrapezoid.addPoint((int)(locationX + 26.5*(gridSize/32.0)),(int)(locationY + 12.5*(gridSize/32.0)));
		topTrapezoid.addPoint((int)(locationX + 23.5*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		topTrapezoid.addPoint((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		topTrapezoid.addPoint((int)(locationX + 5.5*(gridSize/32.0)),(int)(locationY + 12.5*(gridSize/32.0)));
		
		//Draw Bottom Rectangle
		Rectangle rect = new Rectangle((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 24.5*(gridSize/32.0)),(int)(22*(gridSize/32.0))- (int)(10*(gridSize/32.0)),(int)(1.5*(gridSize/32.0)));
		
		if (color.equals("Black")) 
			g2.setColor(Color.BLACK); 
		else
			g2.setColor(Color.WHITE);
		g2.fillPolygon(mainBody);
		g2.fillPolygon(topTrapezoid);
		g2.fill(rect);
		
		if (color.equals("Black"))
			g2.setColor(Color.WHITE); 
		else
			g2.setColor(Color.BLACK);
		g2.drawPolygon(mainBody);
		g2.drawPolygon(topTrapezoid);
		g2.draw(rect);
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
