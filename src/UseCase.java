import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class UseCase extends RectObject{

	public UseCase(Point left_top, int d) {
		super(left_top, d);
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.white);
		g.fillOval((int)(getposition().getX()-getwidth()/2), (int)(getposition().getY()-getheight()/2), getwidth(), getheight());
		g.setColor(Color.black);
		g.drawOval((int)(getposition().getX()-getwidth()/2), (int)(getposition().getY()-getheight()/2), getwidth(), getheight());
		drawName(g);
		if(isselect()) {
			for(Port p:getPorts()) {
				p.draw(g);
			}
		}
	}
}
