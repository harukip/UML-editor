package modes;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import GUI.Canvas;
import shapes.RectObject;
import shapes.Shape;

public class Select_mode extends Mode {
	private boolean pressed = false, press_on_top = false;
	private int top_depth = -1;
	private Shape tmpShape = null, topShape = null;

	public Select_mode(Canvas c) {
		super(c);
		c.setMode(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point mouse = new Point(e.getX(), e.getY());
		Vector<Shape> objs = getCanvas().getObjects();
		int tmp_depth = -1;
		Shape tmpTopShape = null;
		for(Shape o:objs) {
			o.setselect(false);
		}
		for(int i = 0; i < objs.size(); i++) {
			if(objs.elementAt(i).isinside(mouse)) {
				Shape currentShape = objs.elementAt(i).gettop(mouse);
				if(((RectObject)currentShape).getdepth() > tmp_depth) {
					tmp_depth = ((RectObject)currentShape).getdepth();
					tmpTopShape = objs.elementAt(i);
				}
			}
		}
		if(tmpTopShape != null) tmpTopShape.setselect(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;
		Vector<Shape> objs = getCanvas().getObjects();
		int inside_count = 0;
		start = new Point(e.getX(), e.getY());
		for(int i = 0; i < objs.size(); i++) {
			if(objs.elementAt(i).isinside(start)) {
				inside_count += 1;
				Shape currentShape = objs.elementAt(i).gettop(start);
				if(((RectObject)currentShape).getdepth() > top_depth) {
					top_depth = ((RectObject)currentShape).getdepth();
					topShape = currentShape;
					tmpShape = objs.elementAt(i);
				}
			}
		}
		if(inside_count > 0) {
			pressed = false;
			press_on_top = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(pressed) {
			pressed = false;
			end = new Point(e.getX(), e.getY());
			Vector<Shape> objs = getCanvas().getObjects();
			for(int i = 0; i < objs.size(); i++) {
					if(objs.elementAt(i).isinarea(start, end)) {
						objs.elementAt(i).setselect(true);
					}
					else {
						objs.elementAt(i).setselect(false);
					}
			}
		}
		if(press_on_top) {
			press_on_top = false;
			Point mouse = new Point(e.getX(), e.getY());
			tmpShape.setposition(start, ((RectObject)topShape).getposition(), mouse);
			tmpShape.updateport(getCanvas().getGraphics());
			top_depth = -1;
			topShape = null;
			tmpShape = null;
		}
	}

	@Override
	public String getmode() {
		return "Select_mode";
	}

	@Override
	public Shape newobj(Point p) {
		return null;
	}

}
