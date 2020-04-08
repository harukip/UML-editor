import javax.swing.ImageIcon;
import javax.swing.JButton;

public class use_class_button extends shape_button {
	public use_class_button(int xSize, int x, int y) {
		super(xSize, x, y);
		ImageIcon icon = new ImageIcon("./img/use_class_icon.png");
		this.setIcon(icon);
	}
}
