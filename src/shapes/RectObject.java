package shapes;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RectObject extends Shape{
	private Point position;
	private int height = 100, width = 100;
	private List<Port> ports = new ArrayList<Port>();
	private String name = "";
	private int depth;
	
	public RectObject(Point left_top, int d) {
		position = new Point((int)(left_top.getX()+width/2), (int)(left_top.getY()+height/2));
		for(int i = 0; i < 4; i++) {
			//System.out.print("Initialize port"+ i);
			ports.add(new Port(this, i));
		}
		depth = d;
	}
	
	public boolean isinside(Point p) {
		double x = position.getX(), y = position.getY();
		double left_top_x = x - width/2, 
				right_top_x = x + width/2;
		double left_top_y = y - height/2, 
				left_bottom_y = y + height/2;
		if(p.getX() >= left_top_x && p.getX() <= right_top_x && p.getY() >= left_top_y && p.getY() <= left_bottom_y) {
			return true;
		}
		return false;
	}
	
	public int closetoport(Point p) {
		int port_num = -1;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < 4; i++){
			double d = Point.distance(p.getX(), p.getY(), ports.get(i).getposition().getX(), ports.get(i).getposition().getY());
			if(d < min){
				min = d;
				port_num = i;
			}
		}
		return port_num;
	}
	
	public boolean isinarea(Point start, Point end) {
		double x = position.getX(), y = position.getY();
		if(x-width/2 >= start.getX() && y-height/2 >= start.getY() && x+width/2 <= end.getX() && y+height/2 <= end.getY()) {
			return true;
		}
		if(x-width/2 >= end.getX() && y-height/2 >= end.getY() && x+width/2 <= start.getX() && y+height/2 <= start.getY()) {
			return true;
		}
		return false;
	}
	
	public void setposition(Point mouse_start, Point origin, Point mouse_end) {
		double x_diff = mouse_start.getX() - origin.getX();
		double y_diff = mouse_start.getY() - origin.getY();
		position.setLocation(mouse_end.getX() - x_diff, mouse_end.getY() - y_diff);
	}
	
	public void setname(String s) {name = s;}
	
	public List<Port> getPorts() {return ports;}
	
	@Override
	public String gettype() {
		return "RectObject";
	}
	
	public int getheight() {return height;}
	
	public int getwidth() {return width;}
	
	public Point getposition() {return position;}
	
	public int getdepth() {return depth;}
	
	public void draw(Graphics g) {};
	
	public void drawName(Graphics g) {
		super.draw(g);
		g.drawString(name, (int)(position.getX()-width/2+(width*0.25)), (int)(position.getY()-height/2+25));
	}
	
	public void updateport(Graphics g) {
		for(Port p:ports) {
			p.set_position(position);
			for(Line_Object l:p.getlines()) {
				l.draw(g);
			}
		}
	}
	
	public Shape gettop(Point p) {return this;}
}
