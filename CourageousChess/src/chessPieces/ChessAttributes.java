package chessPieces;

import java.awt.Graphics2D;
import java.util.ArrayList;

public interface ChessAttributes {
	boolean contains(int x, int y); 
	void draw(Graphics2D g2, int gridSize);
	int getX();
	int getY();
	String getName();
	String getColor();
	String getGridColor();
	boolean getCheck();
	boolean getHasMoved();
	void setX(int x);
	void setY(int y);
	void setName(String name);
	void setColor(String color);
	void setGridColor(String gridColor);
	void setSelected(boolean selected);
	void setCheck(boolean check);
	void setHasMoved(boolean hasMoved);
	boolean getSelected();
	ArrayList<ChessAttributes> nextMoveSet(ArrayList<ChessAttributes> chess);
	ChessAttributes getChessPiece(ArrayList<ChessAttributes> chess, int x, int y);
	void swapInfo(ChessAttributes piece, ArrayList<ChessAttributes> chess);
	void kingCheck(ArrayList<ChessAttributes> chess);
	void kingCheck(ChessAttributes c, ChessAttributes piece);
	void promote(ArrayList<ChessAttributes> chess, ChessAttributes piece, String promotion);
	//Have to add set methods
}
