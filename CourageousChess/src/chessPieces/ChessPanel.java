package chessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChessPanel extends JPanel{

	private ArrayList<ChessAttributes> chess;
	private Rectangle [][] chessGrid;
	private Rectangle square, selected;
	private ArrayList<Rectangle> nextMoveSet;
	
	private final int GRID = 8;//Chess Grid 8 by 8
	private final int START_POINTX = 15;//Starting point of grid location
	private final int START_POINTY = 0;
	private double gridSize = Math.min(this.getWidth(), this.getHeight()) / 8;//The size of each square on grid

	private double locationX;
	private double locationY;
	private int colorGrid = 0;
	private int selectedX = -1, selectedY = -1;

	public ChessPanel(ArrayList<ChessAttributes> chess) {
		this.chess = chess;
		selected = null;
		drawGrid();

		class MyListener extends MouseAdapter{
			public void mousePressed(MouseEvent event){
				int x = event.getX();
				int y = event.getY();
				selected = null;
				for(int i =0; i<GRID;i++)
					for(int j =0; j<GRID; j++)
						if(chessGrid[i][j].contains(x, y)){
							//System.out.println(i + "  " + j);
							for(ChessAttributes c : chess){
								if(c.getX() == i && c.getY() == j){
									selected = chessGrid[i][j];
									selectedX = i;
									selectedY = j;
									repaint();
									break;
								}
							}
						}
			}
		}
		addMouseListener(new MyListener()) ;
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
		
		g2.setColor(Color.CYAN);
		if(selected != null){//For selected piece options like move
			g2.fill(selected);
			for(ChessAttributes piece: chess)
				if(selectedX == piece.getX() && selectedY == piece.getY()){
					System.out.println(piece.getName());
					nextMoveSet = piece.nextMoveSet(chess, chessGrid);
				}
			if(nextMoveSet != null)
				for(Rectangle rect: nextMoveSet)
					g2.fill(rect);
		}
		
		g2.setColor(Color.RED);
		for(ChessAttributes piece: chess){
			g2.drawString(piece.getName(), (int)(piece.getX() * gridSize + 0.5 * gridSize), (int)(piece.getY() * gridSize + 0.5 * gridSize));
		}

	}

}
