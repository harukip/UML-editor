import javax.swing.JButton;

public class shape_button extends JButton {
	public shape_button(int xSize, int x, int y) {
		this.setBounds(x, y, (int)(xSize*0.1), (int)(xSize*0.1));
	}
}
