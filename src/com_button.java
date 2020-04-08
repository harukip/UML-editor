import javax.swing.ImageIcon;
import javax.swing.JButton;

public class com_button extends line_button {
	public com_button(int xSize, int x, int y) {
		super(xSize, x, y);
		ImageIcon icon = new ImageIcon("./img/com_icon.png");
		this.setIcon(icon);
	}
}
