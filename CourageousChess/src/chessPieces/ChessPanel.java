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
	private ChessAttributes selected;
	private ArrayList<Rectangle> nextMoveSet;

	private final int GRID = 8;//Chess Grid 8 by 8
	private double gridSize = Math.min(this.getWidth(), this.getHeight()) / 8;//The size of each square on grid

	private int selectedX = -1, selectedY = -1;
	private boolean select = false;

	public ChessPanel(ArrayList<ChessAttributes> chess) {
		this.chess = chess;
		selected = null;
		
		class MyListener extends MouseAdapter{
			public void mousePressed(MouseEvent event){
				int x = event.getX();
				int y = event.getY();
				selected = null;
				if(!select){
					for(ChessAttributes piece: chess){
						if(piece.contains(x, y) && !(piece.getName().equals("Blank"))){
							selected = piece;
							select = true;
							break;
						}
					}
				}
				else if(select){
					for(ChessAttributes piece: chess){
						if(piece.contains(x, y)){
							if(piece.getSelected()){
								
							}
							else{
								selected = null;
								for(ChessAttributes c: chess){
									c.setSelected(false);
								}
								if(piece.getColor().equals("Blank")){
									select = false;
									break;
								}
								selected = piece;
								break;
							}
						}
					}
				}
				repaint();
			}
		}
		addMouseListener(new MyListener()) ;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		gridSize = this.getHeight() / GRID;
		
		if(selected != null){//For selected piece options like move
			selected.nextMoveSet(chess);
		}
		
		for(ChessAttributes piece: chess){
			piece.draw(g2, (int)gridSize);
		}

		g2.setColor(Color.RED);
		for(ChessAttributes piece: chess){
			if(!(piece.getName().equals("Blank")))
				g2.drawString(piece.getName(), (int)(piece.getX() * gridSize + 0.5 * gridSize), (int)(piece.getY() * gridSize + 0.5 * gridSize));
		}
		
		for(ChessAttributes piece: chess){
			if(piece.getName().equals("Knight")){
				System.out.println("Knight: " + piece.getX() +"," + piece.getY() +" " +  piece.getColor());
			}
		}

	}

}
