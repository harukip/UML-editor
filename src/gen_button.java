import javax.swing.ImageIcon;
import javax.swing.JButton;

public class gen_button extends line_button {
	public gen_button(int xSize, int x, int y) {
		super(xSize, x, y);
		ImageIcon icon = new ImageIcon("./img/gen_icon.png");
		this.setIcon(icon);
	}
}
