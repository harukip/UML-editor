import javax.swing.ImageIcon;
import javax.swing.JButton;

public class asso_button extends line_button {
	public asso_button(int xSize, int x, int y) {
		super(xSize, x, y);
		ImageIcon icon = new ImageIcon("./img/asso_icon.png");
		this.setIcon(icon);
	}
}
