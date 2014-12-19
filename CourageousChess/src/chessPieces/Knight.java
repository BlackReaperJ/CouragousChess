package chessPieces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Knight extends ChessPiece{

	private int [] paths = {-1,-2, 1, -2, 2, -1, 2, 1, 1, 2, -1, 2, -2, 1, -2, -1};

	public Knight(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
	}

	public void draw(Graphics2D g2, int gridSize) {
		super.draw(g2, gridSize);
		
		/*Draw Knight Body*/
		Polygon body = new Polygon();
		body.addPoint((int)(locationX + 8.5*(gridSize/32.0)),(int)(locationY + 28*(gridSize/32.0)));
		body.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 28*(gridSize/32.0)));
		body.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 10*(gridSize/32.0)));
		body.addPoint((int)(locationX + 18.5*(gridSize/32.0)),(int)(locationY + 7.5*(gridSize/32.0)));
		body.addPoint((int)(locationX + 11*(gridSize/32.0)),(int)(locationY + 11*(gridSize/32.0)));
		body.addPoint((int)(locationX + 11*(gridSize/32.0)),(int)(locationY + 16*(gridSize/32.0)));
		body.addPoint((int)(locationX + 17*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));

		/*Draw Knight Mouth*/
		Polygon mouth = new Polygon();
		mouth.addPoint((int)(locationX + 11*(gridSize/32.0)),(int)(locationY + 16*(gridSize/32.0)));
		mouth.addPoint((int)(locationX + 13*(gridSize/32.0)),(int)(locationY + 17*(gridSize/32.0)));
		mouth.addPoint((int)(locationX + 17*(gridSize/32.0)),(int)(locationY + 14*(gridSize/32.0)));

		/*Draw Eye*/
		Ellipse2D.Double eye = new Ellipse2D.Double((int)(locationX + 15*(gridSize/32.0)),(int)(locationY + 11*(gridSize/32.0)),(int)(gridSize/16.0),(int)(gridSize/16.0));

		/*Draw Hair*/
		Polygon hair_0 = new Polygon();
		hair_0.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 11*(gridSize/32.0)));
		hair_0.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 12.5*(gridSize/32.0)));

		Polygon hair_1 = new Polygon();
		hair_1.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 13*(gridSize/32.0)));
		hair_1.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 14.5*(gridSize/32.0)));

		Polygon hair_2 = new Polygon();
		hair_2.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 15*(gridSize/32.0)));
		hair_2.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 16.5*(gridSize/32.0)));

		Polygon hair_3 = new Polygon();
		hair_3.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 17*(gridSize/32.0)));
		hair_3.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 18.5*(gridSize/32.0)));

		Polygon hair_4 = new Polygon();
		hair_4.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 19*(gridSize/32.0)));
		hair_4.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 20.5*(gridSize/32.0)));

		Polygon hair_5 = new Polygon();
		hair_5.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 21*(gridSize/32.0)));
		hair_5.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 22.5*(gridSize/32.0)));

		Polygon hair_6 = new Polygon();
		hair_6.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 23*(gridSize/32.0)));
		hair_6.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 24.5*(gridSize/32.0)));

		Polygon hair_7 = new Polygon();
		hair_7.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 25*(gridSize/32.0)));
		hair_7.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 26.5*(gridSize/32.0)));

		/*Draw Ears*/
		Polygon ear = new Polygon();
		ear.addPoint((int)(locationX + 18.5*(gridSize/32.0)),(int)(locationY + 7.5*(gridSize/32.0)));
		ear.addPoint((int)(locationX + 20*(gridSize/32.0)),(int)(locationY + 4*(gridSize/32.0)));
		ear.addPoint((int)(locationX + 22.5*(gridSize/32.0)),(int)(locationY + 10*(gridSize/32.0)));

		if (color.equals("Black")) //RED = BLACK BLUE = WHITE
			g2.setColor(Color.BLACK); 
		else
			g2.setColor(Color.WHITE);
		g2.fillPolygon(body);
		g2.fillPolygon(mouth);
		g2.fill(eye);
		g2.fill(ear);
		//g2.fillPolygon(topRight);
		//g2.fillPolygon(cross);
		if (color.equals("Black"))
			g2.setColor(Color.WHITE); 
		else
			g2.setColor(Color.BLACK);
		g2.drawPolygon(body);
		g2.drawPolygon(mouth);
		g2.draw(eye);
		g2.drawPolygon(hair_0);
		g2.drawPolygon(hair_1);
		g2.drawPolygon(hair_2);
		g2.drawPolygon(hair_3);
		g2.drawPolygon(hair_4);
		g2.drawPolygon(hair_5);
		g2.drawPolygon(hair_6);
		g2.drawPolygon(hair_7);
		g2.drawPolygon(ear);
		/*for (int k = 0; k < 12; k ++) {
			g2.fillPolygon(hair[k]);
		}*/
		//g2.drawPolygon(topRight);
		//g2.drawPolygon(cross);
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
					kingCheck(this.getChessPiece(chess, xPos, yPos),piece);
				}
			}
		}
		return chess;
	}

}
