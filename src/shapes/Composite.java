package shapes;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class Composite extends Shape{
	private Vector<Shape> childs = new Vector<Shape>();
	
	public void addchild(Shape o) {
		childs.add(o);
	}

	@Override
	public String gettype() {
		// TODO Auto-generated method stub
		return "Composite";
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		for(Shape o:childs) {
			o.draw(g);
		}
	}
	
	public Vector<Shape> getchilds(){return childs;}
	
	public void setselect(boolean b) {
		for(Shape o:childs) {
			o.setselect(b);
		}
	}
	
	public boolean isinside(Point p) {
		for(Shape o:childs) {
			if(o.isinside(p)) return true;
		}
		return false;
	}
	
	public Shape gettop(Point p) {
		int top_depth = Integer.MIN_VALUE;
		Shape top_shape = null;
		for(Shape o:childs) {
			switch (o.gettype()) {
			case "RectObject": 
				if(((RectObject)o).isinside(p)) {
					if(((RectObject)o).getdepth() > top_depth) {
						top_depth = ((RectObject)o).getdepth();
						top_shape = o;
					}
				}
				break;
			case "Composite":
				Shape tmp_shape = ((Composite)o).gettop(p);
				if(tmp_shape != null &&((RectObject)tmp_shape).getdepth() > top_depth) {
					top_depth = ((RectObject)tmp_shape).getdepth();
					top_shape = tmp_shape;
				}
			}
		}
		return top_shape;
	}
	
	public void setposition(Point mouse_start, Point origin, Point mouse_end) {
		double x_diff = mouse_start.getX() - origin.getX();
		double y_diff = mouse_start.getY() - origin.getY();
		Point tmpPoint = null;
		for(Shape o:childs) {
			switch (o.gettype()) {
			case "RectObject":
				tmpPoint = ((RectObject)o).getposition();
				break;
			case "Composite":
				tmpPoint = origin;
			}
			o.setposition(mouse_start, tmpPoint, mouse_end);
		}
	}
	public void updateport(Graphics g) {
		for(Shape o:childs) {
			o.updateport(g);
		}
	}
	
	public boolean isinarea(Point start, Point end) {
		boolean all_true = true;
		for(Shape o:childs) {
			if(!o.isinarea(start, end)) {
				all_true = false;
				break;
			}
		}
		return all_true;
	}
	
	public boolean isselect() {
		boolean all_true = true;
		for(Shape o:childs) {
			if(!o.isselect()) {
				all_true = false;
				break;
			}
		}
		return all_true;
	}
	
}
