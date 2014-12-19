package chessPieces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Pawn extends ChessPiece{

	private int nextX = 0;
	private int nextY = 0;
	private boolean moveUp;

	public Pawn(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
	}

	public void draw(Graphics2D g2, int gridSize) {
		super.draw(g2, gridSize);
		
		/*Draw Main Body*/
		Polygon base = new Polygon();
		base.addPoint((int)(locationX + 7.5*(gridSize/32.0)),(int)(locationY + 28*(gridSize/32.0)));//Bottom Left Corner
		base.addPoint((int)(locationX + 7.5*(gridSize/32.0)),(int)(locationY + 27*(gridSize/32.0)));
		base.addPoint((int)(locationX + 8*(gridSize/32.0)),(int)(locationY + 25.5*(gridSize/32.0)));
		base.addPoint((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 23.5*(gridSize/32.0)));//Top Left Corner
		base.addPoint((int)(locationX + 22*(gridSize/32.0)),(int)(locationY + 23.5*(gridSize/32.0)));//Top Right Corner
		base.addPoint((int)(locationX + 24*(gridSize/32.0)),(int)(locationY + 25.5*(gridSize/32.0)));
		base.addPoint((int)(locationX + 24.5*(gridSize/32.0)),(int)(locationY + 27*(gridSize/32.0)));
		base.addPoint((int)(locationX + 24.5*(gridSize/32.0)),(int)(locationY + 28*(gridSize/32.0)));//Bottom Right Corner
		
		//Draw the 2nd bottom level 
		Polygon bottom2nd = new Polygon();
		bottom2nd.addPoint((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 23.5*(gridSize/32.0)));//Bottom Left Corner
		bottom2nd.addPoint((int)(locationX + 22*(gridSize/32.0)),(int)(locationY + 23.5*(gridSize/32.0)));//Bottom Right Corner
		bottom2nd.addPoint((int)(locationX + 23*(gridSize/32.0)),(int)(locationY + 22.5*(gridSize/32.0)));
		bottom2nd.addPoint((int)(locationX + 23.5*(gridSize/32.0)),(int)(locationY + 21.5*(gridSize/32.0)));//Middle Right Corner
		bottom2nd.addPoint((int)(locationX + 23*(gridSize/32.0)),(int)(locationY + 20.5*(gridSize/32.0)));
		bottom2nd.addPoint((int)(locationX + 22*(gridSize/32.0)),(int)(locationY + 19.5*(gridSize/32.0)));//Top Right Corner
		bottom2nd.addPoint((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 19.5*(gridSize/32.0)));//Top Left Corner
		bottom2nd.addPoint((int)(locationX + 9*(gridSize/32.0)),(int)(locationY + 20.5*(gridSize/32.0)));
		bottom2nd.addPoint((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 21.5*(gridSize/32.0)));//Middle Left Corner
		bottom2nd.addPoint((int)(locationX + 9*(gridSize/32.0)),(int)(locationY + 22.5*(gridSize/32.0)));
		
		//Draw Middle Trapezoid
		Polygon trapezoid = new Polygon();
		trapezoid.addPoint((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 19.5*(gridSize/32.0)));//Bottom Left Corner
		trapezoid.addPoint((int)(locationX + 22*(gridSize/32.0)),(int)(locationY + 19.5*(gridSize/32.0)));//Bottom Right Corner
		trapezoid.addPoint((int)(locationX + 18.5*(gridSize/32.0)),(int)(locationY + 15*(gridSize/32.0)));//Top Right Corner
		trapezoid.addPoint((int)(locationX + 13.5*(gridSize/32.0)),(int)(locationY + 15*(gridSize/32.0)));//Top Left Corner
		
		//Draw the 2nd top level 
		Polygon top2nd = new Polygon();
		top2nd.addPoint((int)(locationX + 13.5*(gridSize/32.0)),(int)(locationY + 15*(gridSize/32.0)));//Bottom Left Corner
		top2nd.addPoint((int)(locationX + 18.5*(gridSize/32.0)),(int)(locationY + 15*(gridSize/32.0)));//Bottom Right Corner
		top2nd.addPoint((int)(locationX + 19.5*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		top2nd.addPoint((int)(locationX + 20*(gridSize/32.0)),(int)(locationY + 13*(gridSize/32.0)));//Middle Right Corner
		top2nd.addPoint((int)(locationX + 19.5*(gridSize/32.0)),(int)(locationY + 12*(gridSize/32.0)));
		top2nd.addPoint((int)(locationX + 18.5*(gridSize/32.0)),(int)(locationY + 11*(gridSize/32.0)));//Top Right Corner
		top2nd.addPoint((int)(locationX + 13.5*(gridSize/32.0)),(int)(locationY + 11*(gridSize/32.0)));//Top Left Corner
		top2nd.addPoint((int)(locationX + 12.5*(gridSize/32.0)),(int)(locationY + 12*(gridSize/32.0)));
		top2nd.addPoint((int)(locationX + 12*(gridSize/32.0)),(int)(locationY + 13*(gridSize/32.0)));//Middle left Corner
		top2nd.addPoint((int)(locationX + 12.5*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));
		
		//Draw Circle on the Pawn
		Ellipse2D.Double circle = new Ellipse2D.Double((int)(locationX + 12.2*(gridSize/32.0)), (int)(locationY + 3.2*(gridSize/32.0)), (int) ((gridSize/4.0)),(int) ((gridSize/4.0)));
		
		if (color.equals("Black")) 
			g2.setColor(Color.BLACK); 
		else
			g2.setColor(Color.WHITE);
		g2.fillPolygon(base);
		g2.fill(circle);
		g2.fill(bottom2nd);
		g2.fill(trapezoid);
		g2.fill(top2nd);

		
		if (color.equals("Black"))
			g2.setColor(Color.WHITE); 
		else
			g2.setColor(Color.BLACK);
		g2.drawPolygon(base);
		g2.draw(circle);
		g2.draw(bottom2nd);
		g2.draw(trapezoid);
		g2.draw(top2nd);
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
