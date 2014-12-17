package chessPieces;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

abstract public class ChessPiece implements ChessAttributes{
	protected int xPos;
	protected int yPos;
	protected String name;
	protected String color;
	protected String gridColor;
	protected int locationX, locationY;
	protected boolean selected, check, hasMoved, castle;
	protected ArrayList<ChessAttributes> kingInCheck,temp;

	private final int START_POINTX = 0;//Starting point of grid location
	private final int START_POINTY = 0;
	private Rectangle grid;

	private String promotion;

	public ChessPiece(int xPos, int yPos, String name, String color, String gridColor){
		this.xPos = xPos;
		this.yPos = yPos;
		this.name = name;
		this.color = color;
		this.gridColor = gridColor;
		this.selected = false;
		this.check = false;
		this.hasMoved = false;
		this.castle = false;
		//System.out.println(xPos + " " + yPos + " " + name + " " + color +" grid: " + gridColor);
	}

	public int getX(){
		return xPos;
	}

	public int getY(){
		return yPos;
	}

	public String getName(){
		return name;
	}

	public String getColor(){
		return color;
	}

	public String getGridColor(){
		return gridColor;
	}

	public void setX(int x){
		xPos = x;
	}

	public void setY(int y){
		yPos = y;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setColor(String color){
		this.color = color;
	}

	public void setGridColor(String gridColor){
		this.gridColor = gridColor;
	}

	public ChessAttributes getChessPiece(ArrayList<ChessAttributes> chess, int x, int y){
		for(ChessAttributes c: chess)
			if(c.getX() == x && c.getY() == y)
				return c;
		return null;
	}

	public void draw(Graphics2D g2, int gridSize) {
		locationX = gridSize * xPos + START_POINTX;
		locationY = gridSize * yPos + START_POINTY;
		grid = new Rectangle(locationX, locationY, gridSize, gridSize);

		if(gridColor.equals("White")) g2.setColor(Color.LIGHT_GRAY);
		else if(gridColor.equals("Black")) g2.setColor(Color.DARK_GRAY);

		if(selected)
			g2.setColor(Color.CYAN);


		if(check && this.getName().equals("King"))
			g2.setColor(Color.RED);

		g2.fill(grid);

		g2.setColor(Color.BLACK);
		g2.draw(grid);
	}

	public boolean contains(int x, int y){
		if(grid.contains(x, y)){
			return true;
		}
		return false;
	}

	public void setSelected(boolean selected){
		this.selected = selected;
	}

	public boolean getSelected(){
		return selected;
	}

	public void setCheck(boolean check){
		this.check = check;
	}

	public boolean getCheck(){
		return check;
	}

	public void setHasMoved(boolean hasMoved){
		this.hasMoved = hasMoved;
	}

	public boolean getHasMoved(){
		return hasMoved;
	}

	public void swapInfo(ChessAttributes piece, ArrayList<ChessAttributes> chess){
		int newX = piece.getX();
		int newY = piece.getY();
		String newGridColor = piece.getGridColor();

		chess.remove(piece);
		Blank blank = new Blank(this.getX(),this.getY(),"Blank","Blank",this.getGridColor());
		chess.add(blank);

		this.setX(newX);
		this.setY(newY);
		this.setGridColor(newGridColor);
		this.setHasMoved(true);

		if(castle && this.getName().equals(("King"))){//This is for castling swaps the rook with a blank
			ChessAttributes rook = null, swap = null;
			if(this.getX() == 6){
				rook = getChessPiece(chess, xPos+1, yPos);
				swap = getChessPiece(chess, xPos-1, yPos);
			}
			else if(this.getX() == 2){
				rook = getChessPiece(chess, xPos-2, yPos);
				swap = getChessPiece(chess, xPos+1, yPos);
			}
			rook.swapInfo(swap, chess);
		}

		if(this.getName().equals("Pawn") &&(this.getColor().equals("White") && this.getY() == 0 || this.getColor().equals("Black") && this.getY() == 7)){//For Pawn Promotion
			JButton queenButton = new JButton("Queen");
			JButton bishopButton = new JButton("Bishop");
			JButton rookButton = new JButton("Rook");
			JButton knightButton = new JButton("Knight");
			JFrame frame = new JFrame("Pawn Promotion");
			JLabel text = new JLabel("               Choose Piece to Promote Pawn!!!               ");
			promotion = "Queen";

			class PromotionListener implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					promotion = event.getActionCommand();
					ChessAttributes piece = getChessPiece(chess, getX(), getY());
					piece.promote(chess, piece, promotion);
					frame.dispose();
				}
			}
	
			PromotionListener buttonlistener = new PromotionListener();
			queenButton.addActionListener(buttonlistener);
			rookButton.addActionListener(buttonlistener);
			knightButton.addActionListener(buttonlistener);
			bishopButton.addActionListener(buttonlistener);

			frame.setLayout(new FlowLayout());
			frame.setSize(320, 100);
			frame.add(text); 
			frame.add(queenButton);
			frame.add(rookButton);
			frame.add(knightButton);
			frame.add(bishopButton);
			frame.setVisible(true);
			
			class WindowListen implements WindowListener {//If user closes Promotion GUI, goes to Default Queen
				public void windowClosing(WindowEvent arg0) {//Window is Closing
					ChessAttributes piece = getChessPiece(chess, getX(), getY());
					piece.promote(chess, piece, "Queen");
					frame.dispose();
				}
				
				public void windowActivated(WindowEvent arg0) {}
				public void windowClosed(WindowEvent arg0) {}
				public void windowDeactivated(WindowEvent arg0) {}
				public void windowDeiconified(WindowEvent arg0) {}
				public void windowIconified(WindowEvent arg0) {}
				public void windowOpened(WindowEvent arg0) {}
			}
			WindowListen window = new WindowListen();
			frame.addWindowListener(window);
		}
	}
	
	public void promote(ArrayList<ChessAttributes> chess, ChessAttributes piece, String promotion){
		if(promotion.equals("Queen")){
			Queen queen = new Queen(this.getX(),this.getY(),"Queen",this.getColor(),this.getGridColor());
			chess.add(queen);
		}
		else if( promotion.equals("Rook")){
			Rook rook = new Rook(this.getX(),this.getY(),"Rook",this.getColor(),this.getGridColor());
			chess.add(rook);
		}
		else if( promotion.equals("Knight")){
			Knight knight = new Knight(this.getX(),this.getY(),"Knight",this.getColor(),this.getGridColor());
			chess.add(knight);
		}
		else if( promotion.equals("Bishop")){
			Bishop bishop = new Bishop(this.getX(),this.getY(),"Bishop",this.getColor(),this.getGridColor());
			chess.add(bishop);
		}
		chess.remove(piece);
	}
	
	public void kingCheck(ArrayList<ChessAttributes> chess){
		for(ChessAttributes piece: chess){
			temp = piece.nextMoveSet(chess);
		}

		kingInCheck = new ArrayList<ChessAttributes>();

		for(ChessAttributes piece: chess){
			if(piece.getCheck()){
				kingInCheck.add(piece);
			}
		}

		if(kingInCheck != null)
			for(ChessAttributes piece: kingInCheck)
				System.out.println(piece.getX() + " " + piece.getY() + piece.getName() + piece.getColor());	
		else
			System.out.println("There is None");
	}

	public void kingCheck(ChessAttributes c, ChessAttributes piece){
		if(piece.getName().equals("King")){
			this.setCheck(true);
			piece.setCheck(true);
		}
	}
}
