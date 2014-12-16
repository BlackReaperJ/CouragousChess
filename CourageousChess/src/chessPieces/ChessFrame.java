package chessPieces;
import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JFrame ;
import javax.swing.JPanel ;

public class ChessFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 800;
	ArrayList<ChessAttributes> chess;
	
	public ChessFrame() {
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		createPanel();
		
		class ResizeListener implements ComponentListener{
			public void componentHidden(ComponentEvent event) {}
			
			public void componentMoved(ComponentEvent event) {}

		
			public void componentResized(ComponentEvent arg0) {
				int newsize = Math.min(getWidth(), getHeight());
				setSize(newsize,newsize);
			}

			public void componentShown(ComponentEvent arg0) {}
			
		}
		addComponentListener(new ResizeListener());
		repaint();
	}
	
	public void createPanel(){
		ChessGrid grid = new ChessGrid();
		chess = grid.getChessGrid();
		JPanel panel = new ChessPanel(chess);
		add(panel,BorderLayout.CENTER);
	}
}
