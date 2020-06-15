package modes;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import GUI.Canvas;
import shapes.Shape;

public abstract class Mode implements MouseListener{
	private Canvas canvas;
	private boolean pressed = false;
	Shape topShape_1 = null, topShape_2 = null;
	Point start, end;
	
	Mode(Canvas c){
		canvas = c;
	}
	
	public Canvas getCanvas() {return canvas;}
	
	public abstract String getmode();

	public void mouseClicked(MouseEvent e) {
		Point mouse = new Point(e.getX(), e.getY());
		getCanvas().addobj(newobj(mouse));
	}

	public void mousePressed(MouseEvent e) {
		pressed = true;
		start = new Point(e.getX(), e.getY());
		Vector<Shape> objs = getCanvas().getObjects();
		int max_depth = Integer.MIN_VALUE;
		for(int i = 0; i < objs.size(); i++) {
			if(objs.elementAt(i).isinside(start)) {
				Shape currentShape = objs.elementAt(i).gettop(start);
				if(currentShape.getdepth() > max_depth) {
					max_depth = currentShape.getdepth();
					topShape_1 = currentShape;
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(pressed) {
			pressed = false;
			if(topShape_1 != null) {
				end = new Point(e.getX(), e.getY());
				Vector<Shape> objs = getCanvas().getObjects();
				int max_depth = Integer.MIN_VALUE;
				for(int i = 0; i < objs.size(); i++) {
					if(objs.elementAt(i).isinside(end)) {
						Shape currentShape = objs.elementAt(i).gettop(end);
						if(currentShape.getdepth() > max_depth) {
							max_depth = currentShape.getdepth();
							topShape_2 = currentShape;
						}
					}
				}
				if(topShape_1 != topShape_2 && topShape_1 != null && topShape_2 != null) {
					getCanvas().addobj(newobj(end));
				}
			}
		}
		topShape_1 = null;
		topShape_2 = null;
	}

	public void mouseEntered(MouseEvent e) {};

	public void mouseExited(MouseEvent e) {};
	
	public abstract Shape newobj(Point p);
}
