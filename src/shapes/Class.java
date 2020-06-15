package shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Class extends RectObject{

	public Class(Point left_top, int d) {
		super(left_top, d);
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.white);
		g.fillRect((int)(getposition().getX()-getwidth()/2), (int)(getposition().getY()-getheight()/2), getwidth(), getheight());
		g.setColor(Color.black);
		g.drawRect((int)(getposition().getX()-getwidth()/2), (int)(getposition().getY()-getheight()/2), getwidth(), getheight());
		g.drawRect((int)(getposition().getX()-getwidth()/2), (int)(getposition().getY()-getheight()/2)+(int)(getheight()*0.33), getwidth(), (int)(getheight()*0.33));
		drawName(g);
		if(isselect()) {
			for(Port p:getPorts()) {
				p.draw(g);
			}
		}
	}
}
