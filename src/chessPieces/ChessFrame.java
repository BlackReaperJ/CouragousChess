package chessPieces;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame ;
import javax.swing.JPanel ;

public class ChessFrame extends JFrame {
	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 800;
	ChessPiece chess[][];
	
	public ChessFrame() {
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		createPanel();
	}
	
	public void createPanel(){
		ChessGrid grid = new ChessGrid(chess);
		JPanel panel = new ChessPanel(chess);
		add(panel,BorderLayout.CENTER);
	}
}
