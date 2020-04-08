import javax.swing.ImageIcon;
import javax.swing.JButton;

public class class_button extends shape_button {
	public class_button(int xSize, int x, int y) {
		super(xSize, x, y);
		ImageIcon icon = new ImageIcon("./img/class_icon.png");
		this.setIcon(icon);
	}
}
