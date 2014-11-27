package chessPieces;
import javax.swing.JFrame;
	
	
public class ChessLauncher {
	
	public static void main(String[] args) {
		JFrame frame = new ChessFrame();
		frame.setTitle("Courageous Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
     }
}
