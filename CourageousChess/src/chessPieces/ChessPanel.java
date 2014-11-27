package chessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class ChessPanel extends JPanel{

	private ChessPiece[][] chess;
	private Rectangle [][] chessGrid;
	private Rectangle square;
	
	private final int GRID = 8;//Chess Grid 8 by 8
	private final int START_POINTX = 0;//Starting point of grid location
	private final int START_POINTY = 0;
	//private double END_POINTX = this.getHeight() / 8;
	private double END_POINTY = this.getWidth() / 8;

	private double locationX;
	private double locationY;
	private int colorGrid = 0;
	
	public ChessPanel(ChessPiece[][] chess) {
		drawGrid();
	}

	public void drawGrid() {
		this.chess = chess;
		chessGrid = new Rectangle[GRID][GRID];
		locationX = START_POINTX;
		locationY = START_POINTY;
		//END_POINTX = this.getWidth() / 8;
		END_POINTY = this.getHeight() / 8;
		
		for(int i = 0; i< GRID; i++){
			for(int j=0; j< GRID; j++){
				square = new Rectangle((int)locationX,(int)locationY,(int)END_POINTY,(int)END_POINTY);
				chessGrid[i][j] = square;
				locationY = locationY + END_POINTY;
			}
			locationX = locationX + END_POINTY;
			locationY = START_POINTY;
		}
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		drawGrid();
		
		for(int i = 0; i <GRID; i++){
			for(int j = 0; j < GRID; j++){
				if(colorGrid % 2 == 0)
					g2.setColor(Color.BLACK);
				else
					g2.setColor(Color.WHITE);
				g2.fill(chessGrid[i][j]);
					colorGrid++;
			}
			colorGrid++;
		}

		//g2.fill(chessGrid[0][0]);
		//g2.drawString(this.getHeight() + " " + this.getWidth(), 100, 100);
	}

}
