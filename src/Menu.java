import javax.swing.JMenuBar;

public class Menu extends JMenuBar{
	private Canvas canvas;
	
	public Menu(Canvas c) {
		canvas = c;
	}
	
	public Canvas getCanvas() {return canvas;}
}
