import java.awt.Color;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	public Canvas(int xSize, int ySize, int x, int y) {
		this.setBounds(x, y, (int)(xSize*0.9), ySize);
		this.setBackground(Color.white);
	}
}
