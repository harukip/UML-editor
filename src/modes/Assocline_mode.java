package modes;
import java.awt.Point;
import java.awt.event.MouseEvent;

import GUI.Canvas;
import shapes.AssocLine;
import shapes.Shape;

public class Assocline_mode extends Mode {

	public Assocline_mode(Canvas c) {
		super(c);
		c.setMode(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public String getmode() {
		return "Assocline_mode";
	}

	@Override
	public Shape newobj(Point p) {
		return new AssocLine(
				topShape_1.getPorts().get(
						topShape_1.closetoport(start)), 
				topShape_2.getPorts().get(
						topShape_2.closetoport(end)));
	}
}
