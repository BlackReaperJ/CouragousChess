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
		
		/*Draw Main Body*/
		Polygon mainBody = new Polygon();
		mainBody.addPoint((int)(locationX + 5.5*(gridSize/32.0)),(int)(locationY + 13*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 11.5*(gridSize/32.0)),(int)(locationY + 17*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 16*(gridSize/32.0)),(int)(locationY + 9.5*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 21.5*(gridSize/32.0)),(int)(locationY + 17*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 27.5*(gridSize/32.0)),(int)(locationY + 13*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 23.5*(gridSize/32.0)),(int)(locationY + 25*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 25*(gridSize/32.0)));
		
		/*Draw Bottom Portion*/
		Rectangle rect = new Rectangle((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 26.5*(gridSize/32.0)), (int)(24*(gridSize/32.0) - 9*(gridSize/32.0)), (int)(2*(gridSize/32.0)));
		
		/*Draw topLeft*/
		Polygon topLeft = new Polygon();
		topLeft.addPoint((int)(locationX + 11.5*(gridSize/32.0)),(int)(locationY + 17*(gridSize/32.0)));
		topLeft.addPoint((int)(locationX + 9.5*(gridSize/32.0)),(int)(locationY + 15.5*(gridSize/32.0)));
		topLeft.addPoint((int)(locationX + 9.5*(gridSize/32.0)),(int)(locationY + 13*(gridSize/32.0)));
		topLeft.addPoint((int)(locationX + 13*(gridSize/32.0)),(int)(locationY + 14.5*(gridSize/32.0)));
		
		/*Draw topRight*/
		Polygon topRight = new Polygon();
		topRight.addPoint((int)(locationX + 21.4*(gridSize/32.0)),(int)(locationY + 17*(gridSize/32.0)));
		topRight.addPoint((int)(locationX + 23.4*(gridSize/32.0)),(int)(locationY + 15.5*(gridSize/32.0)));
		topRight.addPoint((int)(locationX + 23.4*(gridSize/32.0)),(int)(locationY + 13*(gridSize/32.0)));
		topRight.addPoint((int)(locationX + 19.9*(gridSize/32.0)),(int)(locationY + 14.5*(gridSize/32.0)));

		/*Draw cross*/
		Polygon cross = new Polygon();
		cross.addPoint((int)(locationX + 15*(gridSize/32.0)),(int)(locationY + 9.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 17*(gridSize/32.0)),(int)(locationY + 9.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 17*(gridSize/32.0)),(int)(locationY + 5.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 19*(gridSize/32.0)),(int)(locationY + 5.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 19*(gridSize/32.0)),(int)(locationY + 3.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 17*(gridSize/32.0)),(int)(locationY + 3.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 17*(gridSize/32.0)),(int)(locationY + 1.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 15*(gridSize/32.0)),(int)(locationY + 1.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 15*(gridSize/32.0)),(int)(locationY + 3.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 13*(gridSize/32.0)),(int)(locationY + 3.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 13*(gridSize/32.0)),(int)(locationY + 5.5*(gridSize/32.0)));
		cross.addPoint((int)(locationX + 15*(gridSize/32.0)),(int)(locationY + 5.5*(gridSize/32.0)));

		
		if (color.equals("Black")) //RED = BLACK BLUE = WHITE
			g2.setColor(Color.BLACK); 
		else
			g2.setColor(Color.WHITE);
		g2.fillPolygon(mainBody);
		g2.fillPolygon(topLeft);
		g2.fillPolygon(topRight);
		g2.fillPolygon(cross);
		g2.fill(rect);
		if (color.equals("Black"))
			g2.setColor(Color.WHITE); 
		else
			g2.setColor(Color.BLACK);
		g2.drawPolygon(mainBody);
		g2.drawPolygon(topLeft);
		g2.drawPolygon(topRight);
		g2.drawPolygon(cross);
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
