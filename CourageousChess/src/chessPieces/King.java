package chessPieces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class King extends ChessPiece{

	private int [] paths = {-1,-1, -1, 1, 1, -1, 1, 1, 0, -1, 0, 1, 1, 0, -1, 0};

	public King(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
	}

	public void move() {

	}

	public boolean collide(ChessAttributes other) {
		return false;
	}

	public void draw(Graphics2D g2, int gridSize) {
		super.draw(g2, gridSize);
		
		//Rectangle rect = new Rectangle((xPos * gridSize) + (gridSize/3), (yPos * gridSize) + (gridSize/3), gridSize/3, gridSize/3);
		Polygon poly = new Polygon();
		poly.addPoint((xPos * gridSize) + 4*(gridSize/16),(yPos * gridSize) + 5*(gridSize/16));
		poly.addPoint((xPos * gridSize) + 6*(gridSize/16),(yPos * gridSize) + 8*(gridSize/16));
		poly.addPoint((xPos * gridSize) + 8*(gridSize/16),(yPos * gridSize) + 4*(gridSize/16));
		poly.addPoint((xPos * gridSize) + 10*(gridSize/16),(yPos * gridSize) + 8*(gridSize/16));
		poly.addPoint((xPos * gridSize) + 12*(gridSize/16),(yPos * gridSize) + 5*(gridSize/16));
		poly.addPoint((xPos * gridSize) + 11*(gridSize/16),(yPos * gridSize) + 10*(gridSize/16));
		poly.addPoint((xPos * gridSize) + 5*(gridSize/16),(yPos * gridSize) + 10*(gridSize/16));
		Rectangle rect = new Rectangle((xPos * gridSize) + 5*(gridSize/16),(yPos * gridSize) + 11*(gridSize/16), 10*(gridSize/16) - 4*(gridSize/16), gridSize/16);
		if (color.equals("Black")) //RED = BLACK BLUE = WHITE
			g2.setColor(Color.BLACK); 
		else
			g2.setColor(Color.WHITE);
		g2.fillPolygon(poly);
		g2.fill(rect);
		if (color.equals("Black"))
			g2.setColor(Color.WHITE); 
		else
			g2.setColor(Color.BLACK);
		g2.drawPolygon(poly);
		g2.draw(rect);
	}

	public ArrayList<ChessAttributes> nextMoveSet(ArrayList<ChessAttributes> chess) {
		this.setSelected(true);
		for(int i=0; i<paths.length; i+=2){
			int nextX = xPos + paths[i];
			int nextY = yPos + paths[i+1];
			if(nextX >= 0 && nextX <= 7 && nextY >= 0 && nextY <= 7){
				ChessAttributes piece = getChessPiece(chess,nextX, nextY);
				if(!(this.getColor().equals(piece.getColor())) || piece.getColor().equals("Blank")){
					piece.setSelected(true);
				}
			}
		}
		if(!(this.getCheck()) && !(this.getHasMoved())){
			ChessAttributes rookCastle = getChessPiece(chess, xPos+3, yPos);
			ChessAttributes temp = getChessPiece(chess, xPos+1, yPos);
			ChessAttributes temp2 = getChessPiece(chess, xPos+2, yPos);

			if(rookCastle.getName().equals("Rook") && !(rookCastle.getHasMoved()) && temp.getName().equals("Blank") && temp2.getName().equals("Blank")){
				temp2.setSelected(true);
				castle = true;
			}
			rookCastle = getChessPiece(chess, xPos-4, yPos);
			temp = getChessPiece(chess, xPos-3, yPos);
			temp2 = getChessPiece(chess, xPos-2, yPos);
			ChessAttributes temp3 = getChessPiece(chess, xPos-1, yPos);
			
			if(rookCastle.getName().equals("Rook") && !(rookCastle.getHasMoved()) && temp.getName().equals("Blank") && temp2.getName().equals("Blank") && temp3.getName().equals("Blank")){
				temp2.setSelected(true);
				castle = true;
			}
		}
		return chess;
	}

}
