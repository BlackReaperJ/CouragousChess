package chessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ChessPanel extends JPanel{

	private ArrayList<ChessAttributes> chess;
	private Rectangle [][] chessGrid;
	private Rectangle square;
	
	private final int GRID = 8;//Chess Grid 8 by 8
	private final int START_POINTX = 15;//Starting point of grid location
	private final int START_POINTY = 0;
	private double gridSize = this.getWidth() / 8;//The size of each square on grid

	private double locationX;
	private double locationY;
	private int colorGrid = 0;
	
	public ChessPanel(ArrayList<ChessAttributes> chess) {
		this.chess = chess;
		drawGrid();
	}

	public void drawGrid() {
		chessGrid = new Rectangle[GRID][GRID];
		locationX = START_POINTX;
		locationY = START_POINTY;
		gridSize = this.getHeight() / 8;
		
		for(int i = 0; i< GRID; i++){
			for(int j=0; j< GRID; j++){
				square = new Rectangle((int)locationX,(int)locationY,(int)gridSize,(int)gridSize);
				chessGrid[i][j] = square;
				locationY = locationY + gridSize;
			}
			locationX = locationX + gridSize;
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
					g2.setColor(Color.WHITE);
				else
					g2.setColor(Color.BLACK);
				g2.fill(chessGrid[j][i]);
					colorGrid++;
			}
			colorGrid++;
		}
		
		g2.setColor(Color.GRAY);
		for(ChessAttributes piece: chess){
			g2.drawString(piece.getName(), (int)(piece.getX() * gridSize + 0.5 * gridSize), (int)(piece.getY() * gridSize + 0.5 * gridSize));
		}

		//g2.fill(chessGrid[0][0]);
		//g2.drawString(this.getHeight() + " " + this.getWidth(), 100, 100);
	}

}
