package shapes;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class Port {
	private RectObject parent;
	private int port_num;
	private Point position;
	private Vector<Line_Object> lines = new Vector<Line_Object>();
	
	public Port(RectObject p, int num) {
		parent = p;
		port_num = num;
		set_position(parent.getposition());
	}
	
	public void draw(Graphics g) {
		g.fillRect((int)(position.getX()), (int)(position.getY()), 5, 5);
	}
	
	public Vector<Line_Object> getlines(){return lines;}
	
	public void set_position(Point p) {
		int p_x = (int)p.getX(), p_y = (int)p.getY(),
				height = (int)parent.getheight()/2, width = (int)parent.getwidth()/2;
		switch(port_num) {
			case 0:
				position = new Point(p_x, p_y-height);
				break;
			case 1:
				position = new Point(p_x+width, p_y);
				break;
			case 2:
				position = new Point(p_x, p_y+height);
				break;
			case 3:
				position = new Point(p_x-width, p_y);
				break;
		}
	}
	
	public Point getposition() {return position;}
	
	public int getportnum() {return port_num;}
}
