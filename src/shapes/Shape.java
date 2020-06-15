package shapes;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
	private boolean select = false;
	public void draw(Graphics g) {}
	public abstract String gettype();
	public boolean isselect() {return select;}
	public void setselect(boolean b) {select = b;}
	public void setposition(Point mouse_start, Point origin, Point mouse_end) {}
	public void updateport(Graphics g) {}
	public boolean isinside(Point p) {return false;}
	public boolean isinarea(Point start, Point end) {return false;}
	public Shape gettop(Point p) {return null;}
}
