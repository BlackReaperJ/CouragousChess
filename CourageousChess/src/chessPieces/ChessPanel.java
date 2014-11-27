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

	private int locationX;
	private int locationY;
	private int colorGrid = 0;

	public ChessPanel(ChessPiece[][] chess) {
		this.chess = chess;
		chessGrid = new Rectangle[GRID][GRID];
		locationX = START_POINTX;
		locationY = START_POINTY;

		for(int i = 0; i< GRID; i++){
			for(int j=0; j< GRID; j++){
				square = new Rectangle(locationX,locationY,GRID_SIZE,GRID_SIZE);
				chessGrid[i][j] = square;
				locationY = locationY + GRID_SIZE;
			}
			locationX = locationX + GRID_SIZE;
			locationY = START_POINTY;
		}
		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i =0; i<GRID; i++){
			for(int j=0; j< GRID; j++){
				if(colorGrid %2 ==0)
					g2.setColor(Color.BLACK);
				else
					g2.setColor(Color.WHITE);
				g2.fill(chessGrid[i][j]);
					colorGrid++;
			}
			colorGrid++;
		}
		g2.drawString(this.getHeight() + " ", 120, 100);
	}

}
