import javax.swing.ImageIcon;
import javax.swing.JButton;

public class select_button extends JButton {
	public select_button(int xSize, int x, int y) {
		ImageIcon icon = new ImageIcon("./img/select_icon.png");
		this.setIcon(icon);
		this.setBounds(x, y, (int)(xSize*0.1), (int)(xSize*0.1));
	}
}
