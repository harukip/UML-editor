package modes;
import java.awt.Point;
import java.awt.event.MouseEvent;

import GUI.Canvas;
import shapes.Class;
import shapes.Shape;

public class Class_mode extends Mode {

	public Class_mode(Canvas c) {
		super(c);
		c.setMode(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public String getmode() {
		return "Class_mode";
	}

	@Override
	public Shape newobj(Point p) {
		return new Class(p, getCanvas().get_depth());
	}
}
