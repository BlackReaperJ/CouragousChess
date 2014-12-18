package chessPieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;
import javax.swing.Timer ;


import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChessPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<ChessAttributes> chess;
	private ChessAttributes selected;

	private int turn = 0;//White Goes First
	private final int GRID = 8;//Chess Grid 8 by 8
	private double gridSize = Math.min(this.getWidth(), this.getHeight()) / 8;//The size of each square on grid

	private boolean select = false;
	
	private Polygon triangle;
	private final int INTERVAL = 450 ;
	private int triangleTimer = 0;
	
	public ChessPanel(ArrayList<ChessAttributes> chess) {
		this.chess = chess;
		selected = null;
				
		//Creates a flickering Arrow Indicator 
		class TriangleTimer implements ActionListener{
			public void actionPerformed(ActionEvent event){
				triangleTimer++;
				triangleTimer = triangleTimer % 2;
				repaint();
			}
		}
		TriangleTimer listener = new TriangleTimer();
		final Timer timer = new Timer(INTERVAL,listener);
		timer.start();
		
		//Mouse Clicks On GUI 
		class MyListener extends MouseAdapter{
			public void mousePressed(MouseEvent event){
				int x = event.getX();
				int y = event.getY();
				if(!select){
					System.out.println("Select is fALSE");
					for(ChessAttributes piece: chess){
						if(piece.contains(x, y) && !(piece.getName().equals("Blank"))){
							if(turn %2 ==0 && piece.getColor().equals("White") || turn %2 == 1 && piece.getColor().equals("Black")){
								selected = piece;
								select = true;
								break;
							}
							else{
								selected = null;
								select = false;
								break;
							}
						}
					}
				}
				else if(select){
					System.out.println("Select is true");
					for(ChessAttributes piece: chess){
						if(piece.contains(x, y)){
							if(piece.getSelected()){
								if(piece.getX() ==  selected.getX() && piece.getY() == selected.getY()){//To Deselect the selected piece
									System.out.println("1.");
									selected = null;
									select = false;
									for(ChessAttributes c: chess){
										c.setSelected(false);
									}
									break;
								}
								else{//Move the selected chess piece to optional position
									System.out.println("4.");
									for(ChessAttributes c: chess){
										c.setCheck(false);
									}
									selected.swapInfo(piece,chess);
									selected.kingCheck(chess);
									selected = null;
									select = false;

									for(ChessAttributes c: chess){
										c.setSelected(false);
									}
									turn++;
									break;
								}
							}
							else{
								System.out.println("2.");
								selected = null;
								for(ChessAttributes c: chess){
									c.setSelected(false);
								}
								if(piece.getColor().equals("Blank")){
									System.out.println("3.");
									select = false;
									break;
								}
								if(turn %2 ==0 && piece.getColor().equals("White") || turn %2 == 1 && piece.getColor().equals("Black"))
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

		g2.setColor(Color.BLUE);
		for(ChessAttributes piece: chess){
			
			if(!(piece.getName().equals("Blank"))){
				g2.drawString(piece.getColor(), (int)(piece.getX() * gridSize + 0.33 * gridSize), (int)(piece.getY() * gridSize + 0.5 * gridSize));
				g2.drawString(piece.getName(), (int)(piece.getX() * gridSize + 0.33 * gridSize), (int)(piece.getY() * gridSize + 0.66 * gridSize));
			}
			
		}
		
		int [] xTri = {(int)(GRID * gridSize + gridSize * 0.03),(int)(GRID * gridSize + gridSize * 0.25),(int)(GRID * gridSize + gridSize * 0.25)};
		int [] yTriWhite = {(int)( GRID * gridSize - gridSize * 0.5),(int)(GRID * gridSize - gridSize * 0.25),(int)( GRID * gridSize - gridSize * 0.75)};
		int [] yTriBlack = {(int)( gridSize * 0.5),(int)(gridSize * 0.25),(int)( gridSize * 0.75)};
		
		g2.setColor(Color.RED);
		if (turn%2 == 0){
			triangle = new Polygon(xTri, yTriWhite, xTri.length);
		}
		else if(turn%2 == 1){
			triangle = new Polygon(xTri, yTriBlack, xTri.length);	
		}
		
		if(triangleTimer % 2 == 0)
			g2.fill(triangle);
		//for(ChessAttributes piece: chess){
		//	if(piece.getName().equals("Knight")){
		//		System.out.println("Knight: " + piece.getX() +"," + piece.getY() +" " +  piece.getColor());
		//	}
		//}

	}

}
